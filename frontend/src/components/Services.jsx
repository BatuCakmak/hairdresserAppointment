// src/components/Services.jsx

import React, { useState, useEffect } from 'react';
import axios from 'axios';

// 1. props'tan 'onServiceSelect' fonksiyonunu al
function Services({ onServiceSelect }) {
    const [isOpen, setIsOpen] = useState(false);
    const [services, setServices] = useState([]);
    const [selectedService, setSelectedService] = useState(''); // Ekranda görünecek isim

    const toggleDropdown = () => {
        setIsOpen(!isOpen);
    };

    useEffect(() => {
        const fetchServices = async () => {
            try {
                // 2. Hizmetleri çeken endpoint'i buraya yazın
                // (Proxy'yi kullanmak için '/api/' ile başlıyoruz)
                const response = await axios.get("/api/services/list"); // VEYA SİZİN ENDPOINT'İNİZ
                if (response) {
                    setServices(response.data);
                    // console.log(response.data);
                }
            } catch (error) {
                if (error.response) {
                    alert("Hata: Hizmetler yüklenemedi");
                    console.log(error.response.data);
                }
            }
        }

        fetchServices();
    }, []);

    // 3. handleSelectService fonksiyonunu tüm 'service' objesini alacak şekilde güncelle
    const handleSelectService = (service) => {
        setSelectedService(service.name); // Local state'i (görünen ismi) güncelle
        setIsOpen(false);

        // 4. (EN ÖNEMLİ KISIM)
        // Seçilen hizmetin ID'sini 'onServiceSelect' prop'u ile HomePage'e gönder
        onServiceSelect(service.id);
    }

    return (
        <div
            className={`home-page-center-kuafor ${isOpen ? 'open' : ''}`} // Kendi stilinizi koruyun
            onClick={toggleDropdown}
            style={{ marginRight: "5vh" }} // Orijinal resimdeki gibi bir boşluk vardı sanırım
        >
            <div>{selectedService || "Hizmetler"}</div>
            {
                isOpen && (
                    <ul className='dropdown-list'>
                        {
                            services.length > 0 ? (
                                // 5. 'key' olarak 'index' yerine 'service.id' kullan
                                // 6. onClick'te 'service.name' yerine tüm 'service' objesini gönder
                                services.map((service) => (
                                    <li key={service.id} onClick={() => handleSelectService(service)}>
                                        {service.name} {/* Backend'den gelen isme göre düzeltin */}
                                    </li>
                                ))
                            ) : (
                                <li>
                                    Veri yükleniyor...
                                </li>
                            )
                        }
                    </ul>
                )
            }
        </div>
    );
}

export default Services;