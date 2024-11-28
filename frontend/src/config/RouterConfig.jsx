import React from 'react'
import { Route, Routes } from 'react-router'
import HomePage from '../pages/HomePage'
import LoginPage from '../pages/LoginPage'

function RouterConfig() {
    return (
        <Routes>
            <Route path='/' element={<HomePage />} />
            <Route path='/login' element={<LoginPage />} />
        </Routes>
    )
}

export default RouterConfig
