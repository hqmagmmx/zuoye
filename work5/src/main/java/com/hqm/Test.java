package com.hqm;

import com.hqm.bean.FlightInfo;
import com.hqm.service.FlightService;
import com.hqm.service.impl.FlightServiceImpl;

import java.util.List;

public class Test {
    public static void main(String[] args) {
        FlightService flightService = new FlightServiceImpl();
        List<FlightInfo> res1 = flightService.searchFlightByLeaveAirport("北京首都机场");
        printResult(res1);
        List<FlightInfo> res2 = flightService.searchFlightByArriveTime("10:00");
        printResult(res2);
    }

    public static void printResult(List<FlightInfo> res){
        for (FlightInfo flightInfo:res){
            System.out.println(flightInfo);
        }
    }
}
