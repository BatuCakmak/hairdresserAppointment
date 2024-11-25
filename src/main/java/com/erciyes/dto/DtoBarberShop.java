package com.erciyes.dto;

import com.erciyes.model.Address;
import com.erciyes.model.Hairdresser;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DtoBarberShop extends DtoBase{
    private String name;
    private String phoneNumber;
    private Address addres;
    private Hairdresser hairdresser;
}
