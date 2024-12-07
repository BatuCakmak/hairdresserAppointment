package com.erciyes.repository;

import com.erciyes.model.Hairdresser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HairdresserRepository extends JpaRepository<Hairdresser,Long> {
    boolean existsHairdresserByNameAndLastName(String name, String lastName);
    void  deleteByBarberShopId(Long barberShop_id);

    void deleteAllByBarberShopId(Long id);
}
