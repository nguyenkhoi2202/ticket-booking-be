package com.example.ticketbooking.service.impl;

import com.example.ticketbooking.entity.Station;
import com.example.ticketbooking.model.request.StationCreateRequest;
import com.example.ticketbooking.model.request.StationUpdateRequest;
import com.example.ticketbooking.model.response.CommonResponse;
import com.example.ticketbooking.repository.StationRepository;
import com.example.ticketbooking.service.StationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class StationServiceImpl implements StationService {

    @Autowired
    StationRepository stationRepository;
    @Override
    public List<Station> getAllStation() {
        List<Station> stationList = stationRepository.getAllStation();
        if (stationList != null){
            List<Station> responseList = new ArrayList<>();
            for (int i = 0; i < stationList.size(); i++){
                if (stationList.get(i).getStatus().equals("active")){
                    responseList.add(stationList.get(i));
                }
            }
            return  responseList;
        }else {
            return null;
        }
    }

    @Override
    public CommonResponse createStation(StationCreateRequest request) {
        CommonResponse response = new CommonResponse();
        try{
            Random random = new Random();
            Station station = new Station();
            station.setStationId("st" + request.getStationStart().substring(0,3).hashCode() + (Integer.toString(random.nextInt(999))));
            station.setStationStart(request.getStationStart());
            station.setStationEnd(request.getStationEnd());
            station.setStatus("active");
            stationRepository.save(station);
            response.setStatus(200);
            response.setMessage("Tạo mới station thành công");
        }catch (Exception e){
            response.setStatus(417);
            response.setMessage("Tạo mới station thất bại");
            e.printStackTrace();
        }finally {
            return response;
        }
    }

    @Override
    public CommonResponse updateStation(StationUpdateRequest request) {
        CommonResponse response = new CommonResponse();
        try{
            Station station = stationRepository.findById(request.getStationId()).get();
            if(station != null){
                station.setStationStart(request.getStationStart());
                station.setStationEnd(request.getStationEnd());
                station.setStatus(request.getStatus());
                stationRepository.save(station);
                response.setStatus(200);
                response.setMessage("Cập nhật thành công");

            }else {
                response.setStatus(417);
                response.setMessage("Không tồn tại station để cập nhật");
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
    public CommonResponse deleteStation(String stationId) {
        CommonResponse response = new CommonResponse();
        try{
            Station station = stationRepository.findById(stationId).get();
            if (station != null){
                station.setStatus("inactive");
                stationRepository.save(station);
                response.setStatus(200);
                response.setMessage("Xóa thành công");
            }else {
                response.setStatus(417);
                response.setMessage("Không tồn tại station để xóa");
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
    public Station getStationById(String stationId) {
        Station station = stationRepository.getSationByStationId(stationId);
        if (station != null){
            return  station;
        }else {
            return null;
        }
    }
}
