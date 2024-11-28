import React, { useEffect, useState } from 'react'
import Header from '../components/Header'

function HomePage() {

    const [message, setMessage] = useState("");

    useEffect(() => {
        // Spring Boot backend API'ye istek gönder
        fetch("http://localhost:8080/api/message")
            .then((response) => response.text()) // Text formatında yanıt al
            .then((data) => setMessage(data)) // Mesajı state'e kaydet
            .catch((error) => console.error("API çağrısı başarısız:", error));
    }, []);

    return (
        <div className='home-page-main' >
            <Header />
        </div>
    )
}

export default HomePage
