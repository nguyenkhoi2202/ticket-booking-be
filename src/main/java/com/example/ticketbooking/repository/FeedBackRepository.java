package com.example.ticketbooking.repository;

import com.example.ticketbooking.entity.FeedBack;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeedBackRepository extends JpaRepository<FeedBack, String> {

    @Query(value = "select * from feedback", nativeQuery = true)
    List<FeedBack> getAllFeedBack();

    @Query("SELECT f FROM FeedBack f WHERE f.userId = :userId")
    List<FeedBack> getAllFeedBackByUserId(@Param("userId") String userId);
}