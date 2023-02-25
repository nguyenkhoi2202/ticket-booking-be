package com.example.ticketbooking.service.impl;

import com.example.ticketbooking.controller.VehicleController;
import com.example.ticketbooking.entity.Route;
import com.example.ticketbooking.entity.Vehicle;
import com.example.ticketbooking.model.request.VehicleCreateRequest;
import com.example.ticketbooking.model.request.VehicleUpdateRequest;
import com.example.ticketbooking.model.response.CommonResponse;
import com.example.ticketbooking.repository.VehicleRepository;
import com.example.ticketbooking.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class VehicleServiceImpl implements VehicleService {

    @Autowired
    VehicleRepository vehicleRepository;

    @Override
    public List<Vehicle> getAllVehicle() {
        List<Vehicle> vehicleList = vehicleRepository.getAllVehicle();
        if (vehicleList != null){
            List<Vehicle> responseList = new ArrayList<>();
            for (int i = 0; i < vehicleList.size(); i++){
                responseList.add(vehicleList.get(i));
            }
            return  responseList;
        }else {
            return null;
        }
    }

    @Override
    public CommonResponse deleteVehicle(String vehicleId) {
        CommonResponse response = new CommonResponse();
        try{
            Vehicle  vehicle = vehicleRepository.findById(vehicleId).get();
            if (vehicle != null){
                if (vehicle.getStatus().equals("active")){
                    vehicle.setStatus("inactive");
                    vehicleRepository.save(vehicle);
                    response.setStatus(200);
                    response.setMessage("Đã xóa xe có biển số " + vehicle.getLicensePlates() + " thành công");
                }else {
                    vehicle.setStatus("active");
                    vehicleRepository.save(vehicle);
                    response.setStatus(200);
                    response.setMessage("Xóa thành công");
                }

            }else {
                response.setStatus(417);
                response.setMessage("Không tồn tại vehicle để xóa");
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
    public CommonResponse ụpdateVehicle(VehicleUpdateRequest request) {
        CommonResponse response = new CommonResponse();
        try{
            Vehicle vehicle = vehicleRepository.findById(request.getVehicalId()).get();
            if(vehicle != null){
                vehicle.setLicensePlates(request.getLicensePlates());
                vehicle.setSeat(request.getSeat());
                vehicle.setColor(request.getColor());
                vehicle.setVehicleTypeId(request.getVehicleTypeId());
                vehicle.setStatus(request.getStatus());
                vehicleRepository.save(vehicle);
                response.setStatus(200);
                response.setMessage("Cập nhật thành công");

            }else {
                response.setStatus(417);
                response.setMessage("Không tồn tại vehicle để cập nhật");
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
    public CommonResponse createVehicle(VehicleCreateRequest request) {
        CommonResponse response = new CommonResponse();
        try{
            Random random = new Random();
            Vehicle vehicle = new Vehicle();
            Vehicle checkExist = vehicleRepository.findVehicleByLicensePlates(request.getLicensePlates());
            if (checkExist == null){
                vehicle.setVehicalId("ve" + request.getLicensePlates().substring(4).hashCode() + (Integer.toString(random.nextInt(999))));
                vehicle.setLicensePlates(request.getLicensePlates());
                vehicle.setSeat(request.getSeat());
                vehicle.setColor(request.getColor());
                vehicle.setVehicleTypeId(request.getVehicleTypeId());
                vehicle.setStatus("active");
                vehicleRepository.save(vehicle);
                response.setStatus(200);
                response.setMessage("Tạo mới vehicle thành công");
            }else {
                response.setStatus(417);
                response.setMessage("Đã tồn tại trong cơ sở dữ liệu");
            }

        }catch (Exception e){
            response.setStatus(417);
            response.setMessage("Tạo mới vehicle thất bại");
            e.printStackTrace();
        }finally {
            return response;
        }
    }

    @Override
    public Vehicle getVehicleById(String vehicleId) {
        Vehicle vehicle = vehicleRepository.getVehicleById(vehicleId);
        if(vehicle != null){
            return  vehicle;
        }else {
            return null;
        }

    }
}
