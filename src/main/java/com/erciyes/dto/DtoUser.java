package com.erciyes.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DtoUser extends DtoBase{
    private String firstName;

    private String lastName;
}
