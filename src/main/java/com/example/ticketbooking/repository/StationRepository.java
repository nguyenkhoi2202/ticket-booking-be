package com.example.ticketbooking.repository;

import com.example.ticketbooking.entity.Station;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StationRepository extends JpaRepository<Station, String> {

    @Query(value = "select * from station", nativeQuery = true)
    List<Station> getAllStation();

    @Query(value = "select * from station where station_id = :stationId", nativeQuery = true)
    Station getSationByStationId(@Param("stationId") String stationId);
}