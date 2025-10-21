import React, { useState, useEffect } from 'react';
import axios from 'axios';

// 1. props'tan 'onShopSelect' fonksiyonunu al
function ShopList({ onShopSelect }) {
    const [isOpen, setIsOpen] = useState(false);
    const [shops, setShops] = useState([]);
    const [selectedShopName, setSelectedShopName] = useState(''); // İsmi 'selectedShopName' olarak değiştirdim, daha anlaşılır

    const toggleDropdown = () => {
        setIsOpen(!isOpen);
    };

    useEffect(() => {
        const fetchShops = async () => {
            try {
                const response = await axios.get("api/barbershop/list");
                if (response) {
                    setShops(response.data);
                    // console.log(response.data); // Bu kalsın, test için iyi
                }
            } catch (error) {
                if (error.response) {
                    alert("Hata!");
                    console.log(error.response.data);
                    console.log(error.response.status);
                }
            }
        };

        fetchShops();
    }, []);

    // 2. handleSelectShop fonksiyonunu güncelle
    const handleSelectShop = (shop) => { // Artık 'shop.name' yerine tüm 'shop' objesini alıyoruz
        setSelectedShopName(shop.name); // Ekranda göstermek için local state'i güncelle
        setIsOpen(false);

        // 3. (EN ÖNEMLİ KISIM)
        // Seçilen dükkanın ID'sini 'onShopSelect' prop'u ile HomePage'e gönder
        // Backend'deki 'barbershopId' parametresi için bu ID'ye ihtiyacımız var.
        onShopSelect(shop.id);
    };


    return (
        <div
            className={`home-page-center-kuafor ${isOpen ? 'open' : ''}`}
            onClick={toggleDropdown}
            style={{ marginRight: "5vh", marginLeft: "3vh" }}
        >
            {/* Local state'i göster */}
            <div>{selectedShopName || "Dükkan Listesi"}</div>
            {
                isOpen && (
                    <ul className='dropdown-list'>
                        {
                            shops.length > 0 ? (
                                shops.map((shop) => ( // 'index' yerine 'shop.id' kullanmak daha iyidir

                                    // 4. onClick'te 'shop.name' yerine tüm 'shop' objesini gönder
                                    <li key={shop.id} onClick={() => handleSelectShop(shop)}>
                                        {shop.name}
                                    </li>
                                ))
                            ) : (
                                <li>Veri yükleniyor...</li>
                            )
                        }
                    </ul>
                )
            }
        </div>
    );
}

export default ShopList;