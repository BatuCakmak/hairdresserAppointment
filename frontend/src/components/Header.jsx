import React from 'react'
import "../css/homePage.css"
import { useLocation, useNavigate } from 'react-router'
import { useSelector } from 'react-redux';

function Header() {

    const navigate = useNavigate();

    const { firstName, loginStatus } = useSelector((state) => state.login)

    return (
        <div className='header-main'>
            <div>
                <h3 onClick={() => navigate("/")} className='title-text' >Online Kuaför Sistemi</h3>
            </div>

            <div>
                <div>
                    {
                        loginStatus === 200 ? (
                            <h2>{firstName}</h2>
                        ) : (
                            <button onClick={() => navigate("/login")} type="button" class="btn btn-primary">Sign-In</button>
                        )
                    }
                </div>
            </div>
        </div>
    )
}

export default Header
