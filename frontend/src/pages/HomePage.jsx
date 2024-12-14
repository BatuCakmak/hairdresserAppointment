import React, { useEffect, useState } from 'react';
import Header from '../components/Header';
import KuaforList from '../components/KuaforList';
import Services from '../components/Services';
import { useDispatch, useSelector } from 'react-redux';
import { setFirstName } from '../redux/slice/loginSlice';

function HomePage() {
    const [message, setMessage] = useState("");



    return (
        <div className="home-page-main">
            <Header />

            <div className="home-page-content">
                <div className='home-page-dropdown' >
                    <div className="home-page-center-box">
                        <KuaforList />
                        <Services />
                        <div className="home-page-center-button">
                            <button onClick={() => alert("Ara işlemi başlatıldı...")} className="btn">Ara</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    );
}

export default HomePage;
