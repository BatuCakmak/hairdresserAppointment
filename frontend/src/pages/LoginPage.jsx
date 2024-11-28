import React, { useEffect, useState } from "react";
import "bootstrap/dist/css/bootstrap.min.css";
import ReactCardFlip from "react-card-flip";
import "../css/loginPage.css"
import Header from "../components/Header";
import axios from "axios";
import { unstable_ClassNameGenerator } from "@mui/material";

function LoginPage() {

    const [isFlipped, setIsFlipped] = useState(false);

    const [name, setName] = useState("");
    const [surName, setSurName] = useState("");
    const [userName, setUserName] = useState("");
    const [email, setEmail] = useState("");
    const [phoneNumber, setPhoneNumber] = useState("");
    const [password, setPassword] = useState("");


    function flipCard() {
        setIsFlipped(!isFlipped);
    }

    const handleSignup = async () => {

        const createUser = {
            firstName: name,
            lastName: surName,
            email: email,
            phoneNumber: phoneNumber,
            username: userName,
            password: password,
        };

        try {
            const response = await axios.post("http://localhost:8080/register", createUser)
        }
        catch (error) {
            console.error("Kayıt oluşturulurken hata oluştu:", error);
        }
    }

    const handleLogin = async () => {

        const loginUser = {
            username: userName,
            password: password
        }

        try {
            const response = await axios.post("http://localhost:8080/authenticate", loginUser)

            const status = response.status;
            console.log("Status kodu : ", status)
        }
        catch (error) {
            console.error("Girişte hata var", error);
        }
    }

    return (
        <div className="main-div-login">

            <Header />

            <ReactCardFlip flipDirection="horizontal" isFlipped={isFlipped}>
                <div className="card">
                    <div className="card-front-left">
                        <input onChange={(e) => setName(e.target.value)} name="name" style={{ textTransform: "capitalize" }} className="input-box" type="text" placeholder="Name" />
                        <input onChange={(e) => setSurName(e.target.value)} name="surname" style={{ textTransform: "capitalize" }} className="input-box" type="text" placeholder="Surname" />
                        <input onChange={(e) => setUserName(e.target.value)} name="username" className="input-box" type="text" placeholder="Username" />
                        <input onChange={(e) => setPhoneNumber(e.target.value)} name="phoneNumber" style={{ textTransform: "capitalize" }} className="input-box" type="number" placeholder="Phone Number" />
                        <input onChange={(e) => setEmail(e.target.value)} name="email" className="input-box" type="email" placeholder="Mail" />
                        <input onChange={(e) => setPassword(e.target.value)} name="password" className="input-box" type="password" placeholder="Password" />

                        <div>
                            <button onClick={() => handleSignup()} type="button" className="btn btn-outline-primary">Sign-Up</button>
                        </div>
                    </div>

                    <div className="card-front-right">
                        <h2 onClick={flipCard} className="login-text" >Login</h2>
                    </div>
                </div>

                <div className="card card-back">
                    <div className="card-back-left">
                        <h2 onClick={flipCard} className="signup-text" >Sign-Up</h2>
                    </div>

                    <div className="card-back-right">
                        <input onChange={(e) => setUserName(e.target.value)} className="input-box" type="text" placeholder="Username" />
                        <input onChange={(e) => setPassword(e.target.value)} className="input-box" type="password" placeholder="Password" />

                        <div>
                            <button type="button" className="btn btn-outline-primary">Login</button>
                        </div>
                    </div>
                </div>
            </ReactCardFlip>
        </div>
    );
}

export default LoginPage;
