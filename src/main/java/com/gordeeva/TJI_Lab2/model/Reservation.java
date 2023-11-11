package com.gordeeva.TJI_Lab2.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reservation {
    private Long id;
    private Long roomId;
    private Long guestId;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
}
