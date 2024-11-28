import React from 'react'
import "../css/homePage.css"
import { useLocation, useNavigate } from 'react-router'

function Header() {

    const navigate = useNavigate();


    return (
        <div className='header-main'>
            <div>
                <h3 onClick={() => navigate("/")} className='title-text' >Online Kuaför Sistemi</h3>
            </div>

            <div>
                <div>
                    <button onClick={() => navigate("/login")} type="button" class="btn btn-primary">Sign-In</button>
                </div>
            </div>
        </div>
    )
}

export default Header
