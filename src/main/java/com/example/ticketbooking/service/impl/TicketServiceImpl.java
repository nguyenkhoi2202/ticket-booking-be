package com.example.ticketbooking.service.impl;

import com.example.ticketbooking.controller.TicketDataResponse;
import com.example.ticketbooking.entity.*;
import com.example.ticketbooking.mail.EmailSenderService;
import com.example.ticketbooking.model.request.TicketCreateRequest;
import com.example.ticketbooking.model.response.CommonResponse;
import com.example.ticketbooking.model.response.CreateTicketResultResponse;
import com.example.ticketbooking.model.response.TicketGetByTicketIdResponse;
import com.example.ticketbooking.repository.*;
import com.example.ticketbooking.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Service
public class TicketServiceImpl implements TicketService {

    @Autowired
    TicketRepository ticketRepository;

    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    @Autowired
    private TripRepository tripRepository;

    @Autowired
    EmailSenderService emailSenderService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private VehicleRepository vehicleRepository;
    @Autowired
    private RouteRepository routeRepository;

    /**
     * The function is used to create a ticket.
     *
     * @param request The request object contains the following parameters:
     * @return A ticket object
     */
    @Override
    public CommonResponse createTicket(TicketCreateRequest request) {
        CommonResponse response = new CommonResponse();
        try{
            Random random = new Random();
            Ticket ticket = new Ticket();
            int numberTicket = ticketRepository.getNumberTicketByTripId(request.getTripId());

            ticket.setTicketId("ti" + request.getUserId().substring(0,3).hashCode() + (Integer.toString(random.nextInt(999))));
            ticket.setBookingDate(format.parse(format.format(new Date())));
            ticket.setPrice(Float.parseFloat(request.getPrice()));
            ticket.setUserId(request.getUserId());
            ticket.setTripId(request.getTripId());
            ticket.setSeatNo("sea" + String.valueOf(numberTicket + 1));
            ticket.setStatus("active");
            ticketRepository.save(ticket);
            Trip trip = tripRepository.getTripByTripId(request.getTripId());
            Route route = routeRepository.getRouteByRouteId(trip.getRouteId());

            String body = "<p>Dear " + userRepository.getFullNameById(request.getUserId()) + ",</p>";
            body += "<p> Cảm ơn bạn đã đặt vé. Thông tin vé bạn như sau: </p>";
//            String link = "https://dt-booking-ticket.vercel.app/register/verify";
//            body += "<h3> <a href=\"" + link + "\">Link</a> </h3>";
            body += "<h4>Ngày đặt vé: " + LocalDate.now() + " </h4>";
            body += "<h4>Số ghế: " + "sea" + String.valueOf(numberTicket + 1) + "  </h5>";
            body += "<h4>Từ: " + route.getFrom().toUpperCase() + " </h4>" ;
            body += "<h4>Đến: " + route.getArrive().toUpperCase() + " </h4>" ;
            body += "<h4>Giá vé: " + request.getPrice() + " </h4>" ;
            body += "<h4>Ngày đi: " + trip.getDate() + " " + trip.getTime() + " </h4>" ; // yyyy-MM-dd hh:ss
            body += "<h4>Tổng quãng đường ước chừng: " + route.getDistance() + " </h4>" ;
            body += "<p>Chúc bạn có chuyến đi thượng lộ nằm ngang !!!  </p>";
            String email = userRepository.getEmailById(request.getUserId()).trim();
            emailSenderService.sendSimpleEmail(email, "Thông tin vé đặt thành công",
                    body);
            response.setStatus(200);
            response.setMessage("Đặt vé thành công");
            response.setData(new CreateTicketResultResponse(request.getTripId(),"sea" + String.valueOf(numberTicket + 1)));

        }catch (Exception e){
            response.setStatus(417);
            response.setMessage("Đặt vé thất bại");
            e.printStackTrace();
        }finally {
            return response;
        }

    }

    /**
     * It returns a list of tickets by user id.
     *
     * @param userId The userId of the user who is logged in.
     * @return List of Ticket
     */
    @Override
    public List<Ticket> getListTicketByUserId(String userId) {
        List<Ticket> ticketList = new ArrayList<>();
        try{
            ticketList = ticketRepository.getListTicketByUserId(userId);

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            return ticketList;
        }
    }

    @Override
    public TicketGetByTicketIdResponse getTicketByTicketId(String ticketId) {
        TicketGetByTicketIdResponse response = new TicketGetByTicketIdResponse();
        try{
            Ticket ticket = ticketRepository.getByTicketId(ticketId);
            if (ticket != null){
                Trip trip = tripRepository.findById(ticket.getTripId()).get();
                User user = userRepository.findById(ticket.getUserId()).get();
                    response.setFullName(user.getFullname());
                    response.setPhoneNumber(user.getPhoneNumber());
                    response.setEmail(user.getEmail());
                    response.setDate(String.valueOf(trip.getDate()));
                    response.setTime(trip.getTime());
                    response.setVehicleId(trip.getVehicalId());
                    response.setStationId(trip.getStationId());
                    response.setRouteId(trip.getRouteId());
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            return response;
        }
    }

    @Override
    public CommonResponse deleteTicket(String ticketId) {
        CommonResponse response = new CommonResponse();
        try{
            Ticket ticket = ticketRepository.findById(ticketId).get();
            if (ticket != null){
                ticket.setStatus("inactive");
                ticketRepository.save(ticket);
                response.setStatus(200);
                response.setMessage("Xóa thành công");
            }else {
                response.setStatus(417);
                response.setMessage("Không tồn tại ticket để xóa");
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
    public List<TicketDataResponse> getAllTicket() {
        List<Ticket> ticketList = ticketRepository.getAllTicket();
        if (ticketList != null){
            List<TicketDataResponse> responses = new ArrayList<>();
            for (int i = 0; i < ticketList.size(); i++){
                TicketDataResponse data = new TicketDataResponse();
                User user = userRepository.findById(ticketList.get(i).getUserId()).get();
                data.setFullName(user.getFullname());
                data.setPhoneNumber(user.getPhoneNumber());
                data.setTicketId(ticketList.get(i).getTicketId());
                data.setTripId(ticketList.get(i).getTripId());
                data.setBooking_date(String.valueOf(ticketList.get(i).getBookingDate()));
                data.setPrice(String.valueOf(ticketList.get(i).getPrice()));
                data.setSeatNo(ticketList.get(i).getSeatNo());
                data.setStatus(ticketList.get(i).getStatus());
                data.setUserId(ticketList.get(i).getUserId());
                responses.add(data);
            }
            return responses;
        }else {
            return null;
        }

    }
}
