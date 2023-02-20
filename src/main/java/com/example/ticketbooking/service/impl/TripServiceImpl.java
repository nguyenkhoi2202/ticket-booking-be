package com.example.ticketbooking.service.impl;

import com.example.ticketbooking.entity.Route;
import com.example.ticketbooking.entity.Trip;
import com.example.ticketbooking.model.request.TripCreateRequest;
import com.example.ticketbooking.model.request.TripUpdateRequest;
import com.example.ticketbooking.model.response.CommonResponse;
import com.example.ticketbooking.repository.TripRepository;
import com.example.ticketbooking.service.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Service
public class TripServiceImpl implements TripService {

    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
    @Autowired
    TripRepository tripRepository;

    @Override
    public List<Trip> getAllTrip() {
        List<Trip> tripList = tripRepository.getAllTrip();
        if (tripList != null){
            List<Trip> responseList = new ArrayList<>();
            for (int i = 0; i < tripList.size(); i++){
                if (tripList.get(i).getStatus().equals("active")){
                    responseList.add(tripList.get(i));
                }
            }
            return  responseList;
        }else {
            return null;
        }
    }

    @Override
    public CommonResponse createTrip(TripCreateRequest request) {
        CommonResponse response = new CommonResponse();

        try{
            Random random = new Random();
            Trip trip = new Trip();
            trip.setTripId("tr" + request.getVehicalId().substring(0,3).hashCode() + (Integer.toString(random.nextInt(999))));
            trip.setVehicalId(request.getVehicalId());
            trip.setStationId(request.getStationId());
            trip.setTime(request.getTime());
            trip.setDate(format.parse(request.getDate()));
            trip.setStatus("active");
            trip.setRouteId(request.getRouteId());
            tripRepository.save(trip);
            response.setStatus(200);
            response.setMessage("Tạo mới trip thành công");
        }catch (Exception e){
            response.setStatus(417);
            response.setMessage("Tạo mới trip thất bại");
            e.printStackTrace();
        }finally {
            return response;
        }
    }

    @Override
    public CommonResponse updateTrip(TripUpdateRequest request) {
        CommonResponse response = new CommonResponse();
        try{
            Trip trip = tripRepository.findById(request.getTripId()).get();
            if(trip != null){
                trip.setDate(format.parse(request.getDate()));
                trip.setTime(request.getTime());
                trip.setStatus(request.getStatus());
                tripRepository.save(trip);
                response.setStatus(200);
                response.setMessage("Cập nhật thành công");

            }else {
                response.setStatus(417);
                response.setMessage("Không tồn tại route để cập nhật");
            }

        }catch (Exception e){
            response.setStatus(417);
            response.setMessage("Cập nhật thất bại");
            e.printStackTrace();
        }finally {
            return response;
        }
    }

    @Override
    public CommonResponse deleteTrip(String tripId) {
        CommonResponse response = new CommonResponse();
        try{
            Trip trip = tripRepository.findById(tripId).get();
            if (trip != null){
                trip.setStatus("inactive");
                tripRepository.save(trip);
                response.setStatus(200);
                response.setMessage("Xóa thành công");
            }else {
                response.setStatus(417);
                response.setMessage("Không tồn tại trip để xóa");
            }

        }catch (Exception e){
            response.setStatus(417);
            response.setMessage("Xóa thất bại");
            e.printStackTrace();
        }finally {
            return  response;
        }
    }
}
