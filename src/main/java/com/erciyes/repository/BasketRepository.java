package com.erciyes.repository;

import com.erciyes.model.Basket;
import com.erciyes.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface BasketRepository extends JpaRepository<Basket,Long> {

    Optional<Basket> findByUser(User user);
}
