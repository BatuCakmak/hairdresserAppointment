package com.erciyes.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TimeSlot {
    private LocalTime startTime;  // Başlangıç saati
    private LocalTime endTime;    // Bitiş saati

    // --- DEĞİŞİKLİK BURADA ---
    @JsonProperty("isAvailable") // Jackson'a tam olarak bu ismi kullanmasını söyle
    private boolean isAvailable;  // Müsaitlik durumu
}