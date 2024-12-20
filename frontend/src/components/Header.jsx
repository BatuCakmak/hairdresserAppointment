import React, { useEffect } from 'react'
import "../css/homePage.css"
import "../css/headerIcons.css"
import { FaUser } from "react-icons/fa6";
import { MdLogout } from "react-icons/md";
import { useLocation, useNavigate } from 'react-router'
import { useDispatch, useSelector } from 'react-redux';
import { setLoginStatus } from '../redux/slice/loginSlice';

function Header() {

    const dispatch = useDispatch();

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
                        loginStatus === true ? (
                            <div className='header-user-div' style={{ display: "flex", flexDirection: "row", alignItems: "center", cursor: "pointer" }}>
                                <div className='userIcon' onClick={() => navigate("/user")} style={{ display: "flex", flexDirection: "row", alignItems: "center", cursor: "pointer" }} >
                                    <FaUser size={30} /> <h2 style={{ margin: "0 1.5vh" }} >{userName}</h2>
                                </div>
                                <div className='logoutIcon' onClick={() => [dispatch(setLoginStatus(false)), navigate("/")]} >
                                    <MdLogout size={30} />
                                </div>
                            </div>

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
