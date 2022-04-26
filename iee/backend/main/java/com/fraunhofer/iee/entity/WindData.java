package com.fraunhofer.iee.entity;

import lombok.Data;

@Data
public class WindData {
    private Integer id;
    private String date;//2022-02-01
    private String time;//00:00:00
    private Float inPower;//1234.567 kW
    private Float outPower;//1234.567 kW
    private Float speed;//12.345 m/s
}
