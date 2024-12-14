import React, { useState } from 'react';

function Services() {
  const [isOpen, setIsOpen] = useState(false);
  const [selectedService, setSelectedService] = useState("");

  const toggleDropdown = () => {
    setIsOpen(!isOpen);
  };

  const handleSelect = (service) => {
    setSelectedService(service);
    setIsOpen(false); 
  };

  return (
    <div 
      className={`home-page-center-service ${isOpen ? 'open' : ''}`} 
      onClick={toggleDropdown}
    >
      <div>{selectedService || "Hizmetler"}</div>
      {isOpen && (
        <ul className="dropdown-list">
          <li onClick={() => handleSelect("Herhangi bir şey seçilmedi")}>Herhangi bir şey seçilmedi</li>
          <li onClick={() => handleSelect("Saç Kesimi")}>Saç Kesimi</li>
          <li onClick={() => handleSelect("Saç Boyama")}>Saç Boyama</li>
          <li onClick={() => handleSelect("Saç Bakımı")}>Saç Bakımı</li>
          <li onClick={() => handleSelect("Sakal Kesimi")}>Sakal Kesimi</li>
          <li onClick={() => handleSelect("Saç-Sakal Kesimi")}>Saç-Sakal Kesimi</li>
          <li onClick={() => handleSelect("Yüz Bakımı")}>Yüz Bakımı</li>
        </ul>
      )}
    </div>
  );
}

export default Services;
