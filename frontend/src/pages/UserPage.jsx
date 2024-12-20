import React, { useEffect, useState } from 'react'
import Header from '../components/Header'
import '../css/userPage.css'
import axios from 'axios'
import { useDispatch, useSelector } from 'react-redux'
import { MdOutlineModeEditOutline } from "react-icons/md";


function UserPage() {

    const { userName } = useSelector((state) => state.login)
    const [user, setUser] = useState([])

    const dispatch = useDispatch();

    useEffect(() => {
        const fetchUser = async () => {
            try {
                const response = await axios.get("http://localhost:8080/user/list");
                const userList = response.data;

                const user = userList.find(user => user.username === userName);

                setUser(user)
                console.log(user)

            } catch (error) {
                console.error("Hata oluştu:", error);
            }
        };

        fetchUser();
    }, [])


    return (
        <div style={{ display: "flex", justifyContent: "center", alignItems: "center", width: "100vw", height: "100vh" }}>
            <Header />

            {
                user && (
                    <div className='userCard-main'>
                        <div className='user-info-area' >
                            <div style={{ display: "flex", flexDirection: "row", alignItems: "center" }} >
                                <h2>İSİM : </h2>
                                <h3 className='user-info' >{user.firstName}</h3>
                            </div>

                            <MdOutlineModeEditOutline size={25} className='edit-icon' />
                        </div>

                        <div className='user-info-area' >
                            <div style={{ display: "flex", flexDirection: "row", alignItems: "center" }} >
                                <h2>SOYİSİM : </h2>
                                <h3 className='user-info' >{user.lastName}</h3>
                            </div>

                            <MdOutlineModeEditOutline size={25} className='edit-icon' />
                        </div>

                        <div className='user-info-area' >
                            <div style={{ display: "flex", flexDirection: "row", alignItems: "center" }} >
                                <h2>KULLANICI ADI : </h2>
                                <h3 className='user-info' >{user.username}</h3>
                            </div>

                            <MdOutlineModeEditOutline size={25} className='edit-icon' />
                        </div>

                        <div className='user-info-area' >
                            <div style={{ display: "flex", flexDirection: "row", alignItems: "center" }} >
                                <h2>TELEFON NUMARASI : </h2>
                                <h3 className='user-info' >{user.phoneNumber}</h3>
                            </div>

                            <MdOutlineModeEditOutline size={25} className='edit-icon' />
                        </div>

                        <div className='user-info-area' >
                            <div style={{ display: "flex", flexDirection: "row", alignItems: "center" }} >
                                <h2>EMAİL : </h2>
                                <h3 className='user-info' >{user.email}</h3>
                            </div>

                            <MdOutlineModeEditOutline size={25} className='edit-icon' />
                        </div>

                        <div className='user-info-area' >
                            <div style={{ display: "flex", flexDirection: "row", alignItems: "center" }} >
                                <h2>DOĞRULAMA : </h2>
                                {user.enable === true ? <h3 className='user-info' >Doğrulandı</h3> : <h3 className='user-info' >Doğrulama Yapılmadı</h3>}
                            </div>

                            <MdOutlineModeEditOutline size={25} className='edit-icon' />
                        </div>
                    </div>
                )
            }

        </div>
    )
}

export default UserPage
