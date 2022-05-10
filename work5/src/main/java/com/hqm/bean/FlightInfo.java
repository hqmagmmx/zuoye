package com.hqm.bean;

import lombok.Data;

import java.io.Serializable;

// 此实体类的字段名必须和xml文档中的标签名一致
@Data
public class FlightInfo implements Serializable {
    private String flightCode;
    private String company;
    private String leaveAirport;
    private String arriveAirport;
    private String leaveTime;
    private String arriveTime;
    private String mode;
}
