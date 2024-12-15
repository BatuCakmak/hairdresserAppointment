package com.erciyes.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "basket")
public class Basket extends BaseEntity{

    @OneToOne
    private User user;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "basket")
    private List<Services> services;

    private Double totalPrice;


}
