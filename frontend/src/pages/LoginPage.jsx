import React, { useEffect, useState } from "react";
import "bootstrap/dist/css/bootstrap.min.css";
import ReactCardFlip from "react-card-flip";
import "../css/loginPage.css"
import Header from "../components/Header";

function LoginPage() {

    const [isFlipped, setIsFlipped] = useState(false);

    const [formData, setFormData] = useState({
        name: "",
        surname: "",
        username: "",
        email: "",
        password: "",
    });



    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData({ ...formData, [name]: value });
    };

    function flipCard() {
        setIsFlipped(!isFlipped);
    }


    const handleSignUp = async () => {


    };



    return (
        <div className="main-div-login">

            <Header />

            <ReactCardFlip flipDirection="horizontal" isFlipped={isFlipped}>
                <div className="card">
                    <div className="card-front-left">
                        <input onChange={handleChange} name="name" style={{ textTransform: "capitalize" }} className="input-box" type="text" placeholder="Name" />
                        <input onChange={handleChange} name="surname" style={{ textTransform: "capitalize" }} className="input-box" type="text" placeholder="Surname" />
                        <input onChange={handleChange} name="username" className="input-box" type="text" placeholder="Username" />
                        <input onChange={handleChange} name="email" className="input-box" type="email" placeholder="Mail" />
                        <input onChange={handleChange} name="password" className="input-box" type="password" placeholder="Password" />

                        <div>
                            <button onClick={handleSignUp} type="button" class="btn btn-outline-primary">Sign-Up</button>
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
                        <input className="input-box" type="text" placeholder="Username" />
                        <input className="input-box" type="password" placeholder="Password" />

                        <div>
                            <button type="button" class="btn btn-outline-primary">Login</button>
                        </div>
                    </div>
                </div>
            </ReactCardFlip>
        </div>
    );
}

export default LoginPage;
