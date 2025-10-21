// src/pages/HomePage.jsx

import React, { useState } from 'react';
import Header from '../components/Header';
import KuaforList from '../components/KuaforList';
import Services from '../components/Services';
import ShopList from '../components/ShopList';

import axios from 'axios';
import { format } from 'date-fns';
import WeeklyCalendar from '../components/WeeklyCalendar';
import { useSelector } from 'react-redux';

function HomePage() {
    const [selectedShopId, setSelectedShopId] = useState(null);
    const [selectedKuaforId, setSelectedKuaforId] = useState(null);
    const [selectedServiceId, setSelectedServiceId] = useState(null);

    // --- DÜZELTME BURADA ---
    // Konsol çıktınıza göre (state.login objesi kullanıcının kendisi),
    // doğru yol 'state.login.user' DEĞİL, 'state.login' olmalı.
    const loggedInUser = useSelector((state) => state.login);
    // -------------------------

    const [selectedDate, setSelectedDate] = useState(new Date());
    const [weeklyData, setWeeklyData] = useState(null);
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState(null);

    const handleSearch = async () => {
        if (!selectedShopId || !selectedKuaforId || !selectedServiceId) {
            alert("Lütfen Dükkan, Kuaför ve Hizmet seçimi yapın.");
            return;
        }

        setLoading(true);
        setError(null);
        setWeeklyData(null);

        const formattedDate = format(selectedDate, 'yyyy-MM-dd');

        try {
            const response = await axios.get('/api/appointment/weekly-availability', {
                params: {
                    barbershopId: selectedShopId,
                    startDate: formattedDate
                }
            });
            setWeeklyData(response.data);

        } catch (err) {
            setError("Müsait saatler alınamadı. Lütfen tekrar deneyin.");
            console.error(err);
        } finally {
            setLoading(false);
        }
    };

    return (
        <div className="home-page-main">
            <Header />

            <div className="home-page-content">
                <div className='home-page-dropdown' >
                    <div className="home-page-center-box">
                        <ShopList onShopSelect={setSelectedShopId} />
                        <KuaforList onKuaforSelect={setSelectedKuaforId} />
                        <Services onServiceSelect={setSelectedServiceId} />

                        <div className="home-page-center-button">
                            <button onClick={handleSearch} className="btn" disabled={loading}>
                                {loading ? "Aranıyor..." : "Ara"}
                            </button>
                        </div>
                    </div>
                </div>

                {/* Sonuçları Göster */}
                <div className="home-page-results">
                    {loading && <p>Haftalık takvim yükleniyor...</p>}
                    {error && <p className="error-message">{error}</p>}

                    {weeklyData && (
                        <WeeklyCalendar
                            data={weeklyData}
                            barbershopId={selectedShopId}
                            hairdresserId={selectedKuaforId}
                            serviceId={selectedServiceId}
                            user={loggedInUser} // Artık buraya doğru kullanıcı objesi gidiyor
                        />
                    )}
                </div>
            </div>
        </div>
    );
}

export default HomePage;