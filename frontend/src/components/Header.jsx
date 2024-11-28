import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router';
import "../css/homePage.css"; 

function Header() {
  const [isDropdownOpen1, setIsDropdownOpen1] = useState(false);
  const [isDropdownOpen2, setIsDropdownOpen2] = useState(false);
  const [services, setServices] = useState([]); 
  const [hairdressers, setHairdressers] = useState([]); 

  const navigate = useNavigate();

  
  const toggleDropdown1 = () => setIsDropdownOpen1(!isDropdownOpen1);

  
  const toggleDropdown2 = () => setIsDropdownOpen2(!isDropdownOpen2);

  
  useEffect(() => {
    
    fetch('/api/services') 
      .then(response => response.json())
      .then(data => setServices(data))
      .catch(error => console.error('Error fetching services:', error));

    
    fetch('/api/hairdressers') 
      .then(response => response.json())
      .then(data => setHairdressers(data))
      .catch(error => console.error('Error fetching hairdressers:', error));
  }, []); 

  return (
    <div className="home-page-main">
      <div className='header-main'>
        <h3 onClick={() => navigate("/")} className='title-text'>Online Kuaför Sistemi</h3>
        <button onClick={() => navigate("/login")} type="button" className="btn btn-primary">Sign-In</button>
      </div>

      
      <div className="center-dropdown">
        <div className="dropdown-container">
         
          <div className={`dropdown ${isDropdownOpen1 ? 'open' : ''}`}>
            <button onClick={toggleDropdown1} className="dropdown-btn">
              Aradığınız Hizmet
            </button>
            {isDropdownOpen1 && (
              <div className="dropdown-list">
                <ul>
                  {services.map(service => (
                    <li key={service.id} onClick={() => navigate(`/service/${service.id}`)}>
                      {service.name}
                    </li>
                  ))}
                </ul>
              </div>
            )}
          </div>

          
          <div className={`dropdown ${isDropdownOpen2 ? 'open' : ''}`}>
            <button onClick={toggleDropdown2} className="dropdown-btn">
              Kuaförler
            </button>
            {isDropdownOpen2 && (
              <div className="dropdown-list">
                <ul>
                  {hairdressers.map(hairdresser => (
                    <li key={hairdresser.id} onClick={() => navigate(`/hairdresser/${hairdresser.id}`)}>
                      {hairdresser.name}
                    </li>
                  ))}
                </ul>
              </div>
            )}
          </div>

          
          <button onClick={() => navigate("/search")} className="search-btn">
            Ara
          </button>
        </div>
      </div>
    </div>
  );
}

export default Header;
