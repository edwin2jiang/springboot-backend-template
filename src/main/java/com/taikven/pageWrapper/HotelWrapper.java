package com.taikven.pageWrapper;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
@Data
public class HotelWrapper {
    private int current;
    private int size;
    private String province;
    private String city;
    private String area;
    private LocalDate startDate;
    private LocalDate endDate;
}
