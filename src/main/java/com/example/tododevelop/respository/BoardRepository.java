package com.example.tododevelop.repository;

import com.example.tododevelop.entity.ToDo;
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

    default ToDo findByIdOrElseThrow(Long id){
        return findByIdAndDeletedFalse(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "없는 id"));
    }

    @Query("SELECT t FROM ToDo t WHERE (:userName IS NULL OR t.userName = :userName) AND (:title IS NULL OR t.title = :title) " +
            "AND t.deleted = false")
    List<ToDo> findAllByConditions(@Param("userName") String userName, @Param("title") String title);


}
