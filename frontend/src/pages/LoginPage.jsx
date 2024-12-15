import React, { useEffect, useState } from "react";
import "bootstrap/dist/css/bootstrap.min.css";
import ReactCardFlip from "react-card-flip";
import "../css/loginPage.css"
import Header from "../components/Header";
import axios from "axios";
import { paperClasses, unstable_ClassNameGenerator } from "@mui/material";
import { useNavigate } from "react-router";
import { useDispatch, useSelector } from "react-redux";
import { setEmail, setFirstName, setLastName, setLoginStatus, setPassword, setPhoneNumber, setUserName } from "../redux/slice/loginSlice";
import Button from 'react-bootstrap/Button';
import Modal from 'react-bootstrap/Modal';
import EmailVerify from "../components/EmailVerify";

function LoginPage() {

    const [isFlipped, setIsFlipped] = useState(false);
    const [modalShow, setModalShow] = useState(false);
    const navigate = useNavigate();

    const { firstName, lastName, userName, phoneNumber, email, password, loginStatus, emailCode } = useSelector((state) => state.login)
    const dispatch = useDispatch();

    function flipCard() {
        setIsFlipped(!isFlipped);
    }

    const handleSignUp = async () => {
        const createUser = {
            firstName: firstName,
            lastName: lastName,
            username: userName,
            phoneNumber: phoneNumber,
            email: email,
            password: password
        }

        const response = await axios.post("http://localhost:8080/register", createUser).catch((error) => {
            if (error.response) {
                console.log(error.response.data);
                console.log(error.response.status);
            }
        })
    }

    const handleSignIn = async () => {
        const loginUser = {
            username: userName,
            password: password
        }

        try {
            const response = await axios.post("http://localhost:8080/authenticate", loginUser)
            console.log(response.status)
            if (response.status === 200) {
                dispatch(setLoginStatus(true))
                console.log("status kodu : ", response.status);
                console.log(response.data);
                navigate("/")
            }
        }
        catch (error) {
            if (error.response) {
                alert("Girilen bilgiler yanlış!")
                console.log(error.response.data);
                console.log(error.response.status);
            }
        }
    }

    return (
        <div className="main-div-login">

            <Header />

            <ReactCardFlip flipDirection="horizontal" isFlipped={isFlipped}>
                <div className="card">
                    <div className="card-front-left">
                        <input onChange={(e) => dispatch(setFirstName(e.target.value))} name="name" style={{ textTransform: "capitalize" }} className="input-box" type="text" placeholder="Name" />
                        <input onChange={(e) => dispatch(setLastName(e.target.value))} name="surname" style={{ textTransform: "capitalize" }} className="input-box" type="text" placeholder="Surname" />
                        <input onChange={(e) => dispatch(setUserName(e.target.value))} name="username" className="input-box" type="text" placeholder="Username" />
                        <input onChange={(e) => dispatch(setPhoneNumber(e.target.value))} name="phoneNumber" className="input-box" type="number" placeholder="Phone Number" />
                        <input onChange={(e) => dispatch(setEmail(e.target.value))} name="email" className="input-box" type="email" placeholder="Mail" />
                        <input onChange={(e) => dispatch(setPassword(e.target.value))} name="password" className="input-box" type="password" placeholder="Password" />

                        <div>
                            <button onClick={() => [handleSignUp(), setModalShow(true)]} type="button" className="btn btn-outline-primary">Sign-Up</button>
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
                        <input onChange={(e) => dispatch(setUserName(e.target.value))} className="input-box" type="text" placeholder="Username" />
                        <input onChange={(e) => dispatch(setPassword(e.target.value))} className="input-box" type="password" placeholder="Password" />

                        <div>
                            <button onClick={() => handleSignIn()} type="button" className="btn btn-outline-primary">Login</button>
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
