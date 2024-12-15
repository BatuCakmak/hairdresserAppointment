import React, { useState, useEffect } from 'react';
import axios from 'axios';

function ShopList() {
    const [isOpen, setIsOpen] = useState(false);
    const [shops, setShops] = useState([]);
    const [selectedShop, setSelectedShop] = useState('');

    const toggleDropdown = () => {
        setIsOpen(!isOpen);
    };

    useEffect(() => {
        const fetchShops = async () => {
            try {
                const response = await axios.get("http://localhost:8080/barbershop/list");
                if (response) {
                    setShops(response.data);
                    console.log(response.data);
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


    return (
        <div
            className={`home-page-center-kuafor ${isOpen ? 'open' : ''}`}
            onClick={toggleDropdown}
            style={{ marginRight: "5vh", marginLeft: "3vh" }}
        >
            <div>{selectedShop || "Dükkan Listesi"}</div>
            {
                isOpen && (
                    <ul className='dropdown-list'>
                        {
                            shops.length > 0 ? (
                                shops.map((shop, index) => (
                                    <li key={index} >
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

export default ShopList
