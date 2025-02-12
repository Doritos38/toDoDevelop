package com.example.tododevelop.repository;


import com.example.tododevelop.entity.User;
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
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByIdAndDeletedFalse(Long id);

    default User findByIdOrElseThrow(Long id){
        return findByIdAndDeletedFalse(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "없는 id"));
    }

    @Query("SELECT u FROM User u WHERE (:userName IS NULL OR u.userName = :userName) AND (:email IS NULL OR u.email = :email) " +
            "AND (:date IS NULL OR DATE(u.date) = :date) AND u.deleted = false")
    List<User> findAllByConditions(@Param("userName") String userName, @Param("email") String email, @Param("date") LocalDate date);


    List<User> findByEmailAndPasswordAndDeletedFalse(String email, String password);
}
