package com.example.ticketbooking.schedule;


import com.example.ticketbooking.service.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
public class scheduleController {

    @Autowired
    TripService tripService;


     //huỷ vé
     @Scheduled(cron = "0 15 17 * * ?", zone = "Asia/Saigon")
    public void cancelBookingAuto() {
        try{
            tripService.cancelTicket();

        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
