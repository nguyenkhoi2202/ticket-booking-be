package com.example.ticketbooking.service.impl;

import com.example.ticketbooking.entity.Route;
import com.example.ticketbooking.model.request.RouteCreateRequest;
import com.example.ticketbooking.model.request.RouteUpdateRequest;
import com.example.ticketbooking.model.response.CommonResponse;
import com.example.ticketbooking.model.response.UserLoginResponse;
import com.example.ticketbooking.repository.RouteRepository;
import com.example.ticketbooking.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class RouteServiceImpl implements RouteService {

    @Autowired
    RouteRepository routeRepository;

    @Override
    public List<Route> getAllRoute() {
        List<Route> routeList = routeRepository.getAllRoute();
        if (routeList != null){
            List<Route> responseList = new ArrayList<>();
            for (int i = 0; i < routeList.size(); i++){
               if (routeList.get(i).getStatus().equals("active")){
                   responseList.add(routeList.get(i));
               }
            }
            return  responseList;
        }else {
            return null;
        }

    }

    @Override
    public CommonResponse createRoute(RouteCreateRequest request) {
        CommonResponse response = new CommonResponse();
        try{
            Random random = new Random();
            Route route = new Route();
            route.setRouteId("ro" + request.getFrom().substring(0,3).hashCode() + (Integer.toString(random.nextInt(999))));
            route.setFrom(request.getFrom());
            route.setArrive(request.getArrive());
            route.setTravelTime(request.getTravelTime());
            route.setDistance(request.getDistance());
            route.setImage(request.getImage());
            route.setFare(request.getFare());
            route.setStatus("active"); // active
            routeRepository.save(route);
            response.setStatus(200);
            response.setMessage("Tạo mới route thành công");
        }catch (Exception e){
            response.setStatus(417);
            response.setMessage("Tạo mới route thất bại");
            e.printStackTrace();
        }finally {
            return response;
        }

    }

    @Override
    public CommonResponse updateRoute(RouteUpdateRequest request) {
        CommonResponse response = new CommonResponse();
        try{
            Route route = routeRepository.findById(request.getRouteId()).get();
            if(route != null){
                route.setFrom(request.getFrom());
                route.setArrive(request.getArrive());
                route.setTravelTime(request.getTravelTime());
                route.setDistance(request.getDistance());
                route.setFare(request.getFare());
                route.setImage(request.getImage());
                route.setStatus(request.getStatus());
                routeRepository.save(route);
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
    public CommonResponse deleteRoute(String routeId) {
        CommonResponse response = new CommonResponse();
        try{
            Route route = routeRepository.findById(routeId).get();
            if (route != null){
                route.setStatus("inactive");
                routeRepository.save(route);
                response.setStatus(200);
                response.setMessage("Xóa thành công");
            }else {
                response.setStatus(417);
                response.setMessage("Không tồn tại route để xóa");
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
