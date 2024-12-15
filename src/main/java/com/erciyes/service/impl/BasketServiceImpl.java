package com.erciyes.service.impl;

import com.erciyes.model.Basket;
import com.erciyes.model.Services;
import com.erciyes.model.User;
import com.erciyes.repository.BasketRepository;
import com.erciyes.repository.UserRepository;
import com.erciyes.service.IBasketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class BasketServiceImpl implements IBasketService {

    @Autowired
    private BasketRepository basketRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PaymentServiceImpl paymentService;

    public Basket addToBasket(Long userId, Services service) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Basket basket = basketRepository.findByUser(user).orElseGet(() -> {
            Basket newBasket = new Basket();
            newBasket.setUser(user);
            newBasket.setServices(new ArrayList<>());
            return newBasket;
        });

        basket.getServices().add(service);
        basket.setServices(basket.getServices()); // Total price update
        return basketRepository.save(basket);
    }

    public Basket getBasket(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return basketRepository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("Basket not found"));
    }


}
