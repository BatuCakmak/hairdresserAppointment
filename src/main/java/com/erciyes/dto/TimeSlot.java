package com.erciyes.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TimeSlot {
    private LocalTime startTime;  // Başlangıç saati
    private LocalTime endTime;    // Bitiş saati
    private boolean isAvailable;  // Müsaitlik durumu
}