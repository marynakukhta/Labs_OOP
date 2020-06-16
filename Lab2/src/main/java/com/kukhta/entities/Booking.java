package com.kukhta.entities;

import lombok.Data;
import lombok.AllArgsConstructor;

@Data
@AllArgsConstructor
public class Booking {
    private int id;
    private AutoClass minClass;
    private String depart;
    private String destination;
    private int minSeats;
    private RideStatus status;
}
