// src/pages/LoginPage.jsx

import React, { useState } from "react";
import "bootstrap/dist/css/bootstrap.min.css";
import ReactCardFlip from "react-card-flip";
import "../css/loginPage.css"
import Header from "../components/Header";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import { useDispatch, useSelector } from "react-redux";

import {
    setEmail,
    setFirstName,
    setLastName,
    setPassword,
    setPhoneNumber,
    setUserName,
    setUserOnLogin
} from "../redux/slice/loginSlice";

import EmailVerify from "../components/EmailVerify";
import { jwtDecode } from "jwt-decode";

function LoginPage() {

    const [isFlipped, setIsFlipped] = useState(false);
    const [modalShow, setModalShow] = useState(false);
    const navigate = useNavigate();
    const dispatch = useDispatch();

    const { firstName, lastName, userName, phoneNumber, email, password } = useSelector((state) => state.login);

    function flipCard() {
        setIsFlipped(!isFlipped);
    }

    const handleSignUp = async () => {
        const createUser = {
            firstName, lastName, username: userName, phoneNumber, email, password
        };
        try {
            await axios.post("/api/register", createUser);
            setModalShow(true);
        } catch (error) {
            console.log(error.response);
        }
    }

    // --- EN ÖNEMLİ DEĞİŞİKLİK BURADA ---
    const handleSignIn = async () => {
        const loginUser = {
            username: userName,
            password: password
        };

        try {
            const response = await axios.post("/api/authenticate", loginUser);
            const accessToken = response.data.payload.accessToken;
            localStorage.setItem("accessToken", accessToken);

            // 1. Token'ı çöz
            const decodedToken = jwtDecode(accessToken);
            console.log("Çözülmüş Token İçeriği:", decodedToken);

            // 2. Redux için kullanıcı verisini hazırla (roller dahil)
            const userPayload = {
                id: decodedToken.userId, // Backend'de 'userId' olarak eklemiştik
                userName: decodedToken.sub,
                firstName: decodedToken.firstName || "",
                lastName: decodedToken.lastName || "",
                email: decodedToken.email || "",
                roles: decodedToken.roles || [] // Token'daki rolleri al
            };

            // 3. Redux hafızasını kullanıcı bilgileriyle güncelle
            dispatch(setUserOnLogin(userPayload));

            // --- 4. YÖNLENDİRME MANTIĞI ---
            // Eğer kullanıcının rolleri arasında 'ADMIN' varsa, /admin'e yönlendir.
            if (userPayload.roles.includes('ADMIN')) {
                navigate("/admin");
            } else {
                // Değilse, anasayfaya yönlendir.
                navigate("/");
            }
            // -----------------------------

        } catch (error) {
            alert("Girilen bilgiler yanlış!");
            console.log(error.response?.data || error.message);
        }
    };

    return (
        <div className="main-div-login">
            <Header />
            <ReactCardFlip flipDirection="horizontal" isFlipped={isFlipped}>
                {/* Kayıt Formu */}
                <div className="card">
                    <div className="card-front-left">
                        <input onChange={(e) => dispatch(setFirstName(e.target.value))} value={firstName} className="input-box" type="text" placeholder="Name" />
                        <input onChange={(e) => dispatch(setLastName(e.target.value))} value={lastName} className="input-box" type="text" placeholder="Surname" />
                        <input onChange={(e) => dispatch(setUserName(e.target.value))} value={userName} className="input-box" type="text" placeholder="Username" />
                        <input onChange={(e) => dispatch(setPhoneNumber(e.target.value))} value={phoneNumber} className="input-box" type="number" placeholder="Phone Number" />
                        <input onChange={(e) => dispatch(setEmail(e.target.value))} value={email} className="input-box" type="email" placeholder="Mail" />
                        <input onChange={(e) => dispatch(setPassword(e.target.value))} value={password} className="input-box" type="password" placeholder="Password" />
                        <div>
                            <button onClick={handleSignUp} type="button" className="btn btn-outline-primary">Sign-Up</button>
                        </div>
                    </div>
                    <div className="card-front-right">
                        <h2 onClick={flipCard} className="login-text">Login</h2>
                    </div>
                </div>

                {/* Giriş Formu */}
                <div className="card card-back">
                    <div className="card-back-left">
                        <h2 onClick={flipCard} className="signup-text">Sign-Up</h2>
                    </div>
                    <div className="card-back-right">
                        <input onChange={(e) => dispatch(setUserName(e.target.value))} value={userName} className="input-box" type="text" placeholder="Username" />
                        <input onChange={(e) => dispatch(setPassword(e.target.value))} value={password} className="input-box" type="password" placeholder="Password" />
                        <div>
                            <button onClick={handleSignIn} type="button" className="btn btn-outline-primary">Login</button>
                        </div>
                    </div>
                </div>
            </ReactCardFlip>

            <EmailVerify
                show={modalShow}
                onHide={() => setModalShow(false)}
            />
        </div>
    );
}

export default LoginPage;