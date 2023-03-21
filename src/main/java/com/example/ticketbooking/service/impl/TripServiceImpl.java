package com.example.ticketbooking.service.impl;

import com.example.ticketbooking.entity.*;
import com.example.ticketbooking.model.request.TripCreateRequest;
import com.example.ticketbooking.model.request.TripDataRequest;
import com.example.ticketbooking.model.request.TripUpdateRequest;
import com.example.ticketbooking.model.response.CommonResponse;
import com.example.ticketbooking.model.response.TripDataResponse;
import com.example.ticketbooking.repository.*;
import com.example.ticketbooking.service.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

@Service
public class TripServiceImpl implements TripService {

    SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
    @Autowired
    TripRepository tripRepository;

    @Autowired
    RouteRepository routeRepository;

    @Autowired
    StationRepository stationRepository;
    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    TicketRepository ticketRepository;


    @Override
    public List<TripDataResponse> getAllTrip() {

        List<Trip> tripList = tripRepository.getAllTrip();
        if (tripList != null){
            List<TripDataResponse> responseList = new ArrayList<>();
            for (int i = 0; i < tripList.size(); i++){
                TripDataResponse data = new TripDataResponse();
                data.setTripId(tripList.get(i).getTripId());
                data.setDate(String.valueOf(tripList.get(i).getDate()));
                data.setTime(tripList.get(i).getTime());
                data.setRouteId(tripList.get(i).getRouteId());
                data.setStationId(tripList.get(i).getStationId());
                data.setVehicleId(tripList.get(i).getVehicalId());
                Vehicle vehicle = vehicleRepository.getVehicleById(tripList.get(i).getVehicalId());
                if (vehicle != null){
                    data.setVehicleId(vehicle.getVehicalId());
                    data.setLiencePlate(vehicle.getLicensePlates());
                }
                Route route = routeRepository.getRouteByRouteId(tripList.get(i).getRouteId());
                if(route != null){
                    data.setRouteId(route.getRouteId());
                    data.setFrom(route.getFrom());
                    data.setArrival(route.getArrive());
                    data.setFare(route.getFare());
                }
                Station station = stationRepository.getSationByStationId(tripList.get(i).getStationId());
                if(station != null){
                    data.setStationId(station.getStationId());
                    data.setStationStart(station.getStationStart());
                    data.setStationEnd(station.getStationEnd());
                }
                int numberTicket = ticketRepository.getNumberTicketByTripId(tripList.get(i).getTripId());
                int seatOpen = Integer.parseInt(vehicle.getSeat()) - numberTicket;
                data.setTotalSeat(String.valueOf(seatOpen));
                responseList.add(data);
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
            trip.setDate(LocalDate.parse(request.getDate()));
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
                trip.setDate(LocalDate.parse(request.getDate()));
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

    @Override
    public List<TripDataResponse> getAllTripByDate(TripDataRequest request) {
        try {
            Date date = formater.parse(request.getDate());
            List<Trip> tripList = tripRepository.getListTripByRouteIdAndDate(request.getRouteId(), date);
            if (tripList != null){
                List<TripDataResponse> responseList = new ArrayList<>();
                for (int i = 0; i < tripList.size(); i++){
                    TripDataResponse data = new TripDataResponse();
                    data.setTripId(tripList.get(i).getTripId());
                    data.setDate(String.valueOf(tripList.get(i).getDate()));
                    data.setTime(tripList.get(i).getTime());
                    Vehicle vehicle = vehicleRepository.getVehicleById(tripList.get(i).getVehicalId());
                    if (vehicle != null){
                        data.setVehicleId(vehicle.getVehicalId());
                        data.setLiencePlate(vehicle.getLicensePlates());
                    }
                    Route route = routeRepository.getRouteByRouteId(tripList.get(i).getRouteId());
                    if(route != null){
                        data.setRouteId(route.getRouteId());
                        data.setFrom(route.getFrom());
                        data.setArrival(route.getArrive());
                        data.setFare(route.getFare());
                    }
                    Station station = stationRepository.getSationByStationId(tripList.get(i).getStationId());
                    if(station != null){
                        data.setStationId(station.getStationId());
                        data.setStationStart(station.getStationStart());
                        data.setStationEnd(station.getStationEnd());
                    }
                    int numberTicket = ticketRepository.getNumberTicketByTripId(tripList.get(i).getTripId());
                    int seatOpen = Integer.parseInt(vehicle.getSeat()) - numberTicket;
                    data.setTotalSeat(String.valueOf(seatOpen));
                    responseList.add(data);
                }
                return  responseList;
            }else {
                return null;
            }
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }


    }

    // This method is used to cancel the ticket.
    @Override
    public void cancelTicket() throws ParseException {
        // TODO document why this method is empty
        Date today = new Date();
        String date = formater.format(today);
        List<String> tripIdList = tripRepository.getAllDate(LocalDate.parse(date));

        for (int i = 0; i < tripIdList.size(); i ++){
            List<Ticket> ticketList = ticketRepository.getAllTicketByTripId(tripIdList.get(i));
            if (ticketList != null){
                for (int j = 0; j < ticketList.size(); j++){
                    ticketList.get(j).setStatus("expired");
                    // Saving the ticket to the database.
                    ticketRepository.save(ticketList.get(j));
                }
            }
        }


    }

    @Override
    public Trip getTripByTripId(String tripId) {
            Trip trip = null;
            try{
                trip = tripRepository.findById(tripId).get();
            }catch (Exception e){
                e.printStackTrace();
            }
            return  trip;
    }


}
