import React, { useState, useEffect } from 'react';
import axios from 'axios';

function ShopList() {
    const [isOpen, setIsOpen] = useState(false);
    const [kuaforler, setKuaforler] = useState([]);
    const [selectedKuafor, setSelectedKuafor] = useState('');

    const toggleDropdown = () => {
        setIsOpen(!isOpen);
    };


    useEffect(() => {

        axios.get('http://localhost:8080/hairdresser/list')
            .then(response => {
                setKuaforler(response.data);
            })
            .catch(error => {
                console.error("Error fetching kuaforler:", error);
            });
    }, []);


    const handleKuaforSelection = (kuafor) => {
        setSelectedKuafor(kuafor);
        setIsOpen(false);
    };

    return (
        <div
            className={`home-page-center-kuafor ${isOpen ? 'open' : ''}`}
            onClick={toggleDropdown}
        >
            <div>{selectedKuafor || "Kuaför Listesi"}</div>
            {isOpen && (
                <ul className="dropdown-list">
                    {kuaforler.length > 0 ? (
                        kuaforler.map((kuafor, index) => (
                            <li key={index} onClick={() => handleKuaforSelection(kuafor.name)}>
                                {kuafor.name}
                            </li>
                        ))
                    ) : (
                        <li>Veri yükleniyor...</li>
                    )}
                </ul>
            )}
        </div>
    );
}

export default ShopList
