import React from 'react'
import { Route, Routes } from 'react-router'
import HomePage from '../pages/HomePage'
import LoginPage from '../pages/LoginPage'
import UserPage from '../pages/UserPage'

function RouterConfig() {
    return (
        <Routes>
            <Route path='/' element={<HomePage />} />
            <Route path='/login' element={<LoginPage />} />
            <Route path='/user' element={<UserPage />} />
        </Routes>
    )
}

export default RouterConfig
