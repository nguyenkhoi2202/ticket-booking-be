package com.example.ticketbooking.repository;

import com.example.ticketbooking.entity.User;
import com.example.ticketbooking.model.response.UserLoginResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    User findByPhoneNumber(String phoneNumber);

    @Query(value = "select * from users", nativeQuery = true)
    List<User> getAllUser();


    @Query(value = "select * from users where phone_number like %:keySearch% ", nativeQuery = true)
    List<User> getAllUserByPhoneNumber(@Param("keySearch") String keySearch);

    @Query(value = "select * from users where verification_code = :code", nativeQuery = true)
    User getUserByVerificationCode(@Param("code") String code);
}