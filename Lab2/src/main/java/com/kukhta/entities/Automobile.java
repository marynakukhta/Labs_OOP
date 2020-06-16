package com.kukhta.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Date;

@Data @AllArgsConstructor
public class Automobile {
    private int id;
    private String name;
    private int seats;
    private Date lastInspectionDate;
    private AutoClass autoClass;
}
