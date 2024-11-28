import React, { useEffect, useState } from 'react'
import Header from '../components/Header'

function HomePage() {

    const [message, setMessage] = useState("");

    return (
        <div className='home-page-main' >
            <Header />
        </div>
    )
}

export default HomePage
