package com.erciyes.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DtoHairdresser extends DtoBase{
    private String name;

    private String lastName;
}
