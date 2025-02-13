package com.example.tododevelop.repository;

import com.example.tododevelop.entity.Comments;
import com.example.tododevelop.entity.ToDo;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comments, Long> {

    Optional<Comments> findByIdAndDeletedFalse(Long id);

    default Comments findByIdOrElseThrow(Long id) {
        return findByIdAndDeletedFalse(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Id Not Found"));
    }

    Optional<Comments> findByIdAndUserIdAndDeletedFalse(Long id, Long userId);

    default Comments findByIdAndUserIdOrElseThrow(long id, long userId) {
        return findByIdAndUserIdAndDeletedFalse(id, userId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Not yout toDo"));
    }

    ;
}
