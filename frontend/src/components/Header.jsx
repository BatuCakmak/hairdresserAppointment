import React, { useEffect } from 'react'
import "../css/homePage.css"
import { useLocation, useNavigate } from 'react-router'
import { useSelector } from 'react-redux';

function Header() {

    const navigate = useNavigate();

    const { userName, loginStatus } = useSelector((state) => state.login)

    return (
        <div className='header-main'>
            <div>
                <h3 onClick={() => navigate("/")} className='title-text' >Online Kuaför Sistemi</h3>
            </div>

            <div>
                <div>
                    {
                        loginStatus === 200 ? (
                            <h2>{userName}</h2>
                        ) : (
                            <button onClick={() => navigate("/login")} type="button" className="btn btn-primary">Sign-In</button>
                        )
                    }
                </div>
            </div>
        </div>
    )
}

export default Header
