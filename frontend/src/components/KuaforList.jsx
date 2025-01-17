import React, { useState, useEffect } from 'react';
import axios from 'axios';

function KuaforList() {
  const [isOpen, setIsOpen] = useState(false);
  const [kuaforler, setKuaforler] = useState([]);
  const [selectedKuafor, setSelectedKuafor] = useState('');

  const toggleDropdown = () => {
    setIsOpen(!isOpen);
  };


  useEffect(() => {
    const fetchKuaför = async () => {
      try {
        const response = await axios.get("http://localhost:8080/hairdresser/list")
        if (response) {
          setKuaforler(response.data);
          console.log(response.data)
        }
      } catch (error) {
        if (error.response) {
          alert("Hata");
          console.log(error.response.data);
        }
      }
    }

    fetchKuaför();
  }, []);

  const handleSelectKuafor = (kuafor) => {
    setSelectedKuafor(kuafor)
    setIsOpen(false)
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
                kuaforler.map((kuafor, index) => (
                  <li key={index} onClick={() => handleSelectKuafor(kuafor.name)}>
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
