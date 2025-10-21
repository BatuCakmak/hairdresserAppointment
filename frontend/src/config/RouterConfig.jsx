// src/config/RouterConfig.jsx

import React from 'react';
import { Routes, Route } from 'react-router-dom';
import HomePage from '../pages/HomePage';
import LoginPage from '../pages/LoginPage';
import UserPage from '../pages/UserPage';
import AdminPage from '../pages/AdminPage'; // 1. Admin sayfasını import et

function RouterConfig() {
    return (
        <Routes>
            <Route path='/' element={<HomePage />} />
            <Route path='/login' element={<LoginPage />} />
            <Route path='/user' element={<UserPage />} />

            {/* --- 2. Admin için yeni yolu (route) ekle --- */}
            <Route path='/admin' element={<AdminPage />} />
            {/* ------------------------------------------- */}
        </Routes>
    );
}

export default RouterConfig;