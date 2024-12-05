package com.erciyes.dto;

import com.erciyes.model.Address;
import com.erciyes.model.Hairdresser;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DtoBarberShop extends DtoBase{
    private String name;
    private String phoneNumber;
    private Address addres;
//    private List<Hairdresser> hairdresser = new ArrayList<>();
}
