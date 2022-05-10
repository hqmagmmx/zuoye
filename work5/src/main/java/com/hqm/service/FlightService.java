package com.hqm.service;


import com.hqm.bean.FlightInfo;

import java.util.List;

public interface FlightService {
    // 根据出发的机场查询航班
    List<FlightInfo> searchFlightByLeaveAirport(String airport);

    // 根据抵达的时间查找航班
    List<FlightInfo> searchFlightByArriveTime(String time);
}
