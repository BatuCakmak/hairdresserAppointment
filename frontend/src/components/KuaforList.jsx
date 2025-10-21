import React, { useState, useEffect } from 'react';
import axios from 'axios';

// 1. props'tan 'onKuaforSelect' fonksiyonunu al
function KuaforList({ onKuaforSelect }) {
    const [isOpen, setIsOpen] = useState(false);
    const [kuaforler, setKuaforler] = useState([]);
    const [selectedKuafor, setSelectedKuafor] = useState(''); // Ekranda görünecek isim

    const toggleDropdown = () => {
        setIsOpen(!isOpen);
    };

    useEffect(() => {
        const fetchKuaför = async () => {
            try {
                // 2. Vite proxy'yi kullanmak için "http://localhost:8080" kısmını sil
                const response = await axios.get("/api/hairdresser/list"); // '/api/' ile başla
                if (response) {
                    setKuaforler(response.data);
                    // console.log(response.data); // Test için kalabilir
                }
            } catch (error) {
                if (error.response) {
                    alert("Hata: Kuaförler yüklenemedi");
                    console.log(error.response.data);
                }
            }
        }

        fetchKuaför();
    }, []);

    // 3. handleSelectKuafor fonksiyonunu tüm 'kuafor' objesini alacak şekilde güncelle
    const handleSelectKuafor = (kuafor) => {
        setSelectedKuafor(kuafor.name); // Local state'i (görünen ismi) güncelle
        setIsOpen(false);

        // 4. (EN ÖNEMLİ KISIM)
        // Seçilen kuaförün ID'sini 'onKuaforSelect' prop'u ile HomePage'e gönder
        onKuaforSelect(kuafor.id);
    }

    return (
        <div
            className={`home-page-center-kuafor ${isOpen ? 'open' : ''}`}
            onClick={toggleDropdown}
        >
            <div>{selectedKuafor || "Kuaför Listesi"}</div>
            {
                isOpen && (
                    <ul className='dropdown-list'>
                        {
                            kuaforler.length > 0 ? (
                                // 5. 'key' olarak 'index' yerine 'kuafor.id' kullan
                                // 6. onClick'te 'kuafor.name' yerine tüm 'kuafor' objesini gönder
                                kuaforler.map((kuafor) => (
                                    <li key={kuafor.id} onClick={() => handleSelectKuafor(kuafor)}>
                                        {kuafor.name}
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

export default KuaforList;