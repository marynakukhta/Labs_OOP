package com.kukhta.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data @AllArgsConstructor
public class Ride {
    private int id;
    private int carId;
    private int bookingId;
    private int cost;
}
