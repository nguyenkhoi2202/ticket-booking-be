package com.example.ticketbooking.repository;

import com.example.ticketbooking.entity.FeedBack;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeedBackRepository extends JpaRepository<FeedBack, String> {

    @Query(value = "select * from feedback", nativeQuery = true)
    List<FeedBack> getAllFeedBack();
}