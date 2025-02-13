package com.example.tododevelop.repository;

import com.example.tododevelop.entity.ToDo;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface BoardRepository extends JpaRepository<ToDo, Long> {

    Optional<ToDo> findByIdAndDeletedFalse(Long id);

    default ToDo findByIdOrElseThrow(Long id) {
        return findByIdAndDeletedFalse(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Id Not Found"));
    }

    @Query("SELECT t FROM ToDo t WHERE (:userName IS NULL OR t.user.userName = :userName) AND (:title IS NULL OR t.title = :title) " +
            "AND (:date IS NULL OR DATE(t.date) = :date) AND t.deleted = false")
    List<ToDo> findAllByConditions(@Param("userName") String userName, @Param("title") String title, @Param("date") LocalDate date);

    Optional<ToDo> findByIdAndUserIdAndDeletedFalse(Long id, Long userId);

    default ToDo findByIdAndUserIdOrElseThrow(Long id, Long userId) {
        return findByIdAndUserIdAndDeletedFalse(id, userId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Not yout toDo"));
    }

}
