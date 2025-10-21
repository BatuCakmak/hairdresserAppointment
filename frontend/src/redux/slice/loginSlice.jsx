// src/redux/slice/loginSlice.js

import { createSlice } from "@reduxjs/toolkit";

const initialState = {
    id: null,
    firstName: "",
    lastName: "",
    userName: "",
    phoneNumber: "",
    email: "",
    password: "",
    loginStatus: false,
    errorMessage: "",
    emailCode: ""
}

const loginSlice = createSlice({
    name: "login",
    initialState,
    reducers: {
        // ... (setFirstName, setLastName, vb. diğer reducer'larınız aynı kalır) ...
        setFirstName: (state, action) => {
            state.firstName = action.payload;
        },
        setLastName: (state, action) => {
            state.lastName = action.payload;
        },
        setUserName: (state, action) => {
            state.userName = action.payload;
        },
        setPhoneNumber: (state, action) => {
            state.phoneNumber = action.payload;
        },
        setEmail: (state, action) => {
            state.email = action.payload;
        },
        setPassword: (state, action) => {
            state.password = action.payload;
        },
        setLoginStatus: (state, action) => {
            state.loginStatus = action.payload;
        },
        setEmailCode: (state, action) => {
            state.emailCode = action.payload
        },

        // --- DEĞİŞİKLİK BURADA: 'undefined' KORUMASI EKLENDİ ---
        // Token'dan eksik bilgi gelse bile state'in çökmesini engeller.
        setUserOnLogin: (state, action) => {
            const userPayload = action.payload;

            // userPayload.id 'undefined' ise 'null' ata (|| = veya)
            state.id = userPayload.id || null;

            // userPayload.userName 'undefined' ise '""' (boş string) ata
            state.userName = userPayload.userName || "";
            state.firstName = userPayload.firstName || "";
            state.lastName = userPayload.lastName || "";
            state.email = userPayload.email || "";

            state.loginStatus = true;
            state.password = ""; // Şifreyi temizle
        },

        logout: (state) => {
            Object.assign(state, initialState);
        }
    }
})

export const {
    setFirstName,
    setLastName,
    setUserName,
    setPhoneNumber,
    setEmail,
    setPassword,
    setLoginStatus,
    setEmailCode,
    setUserOnLogin,
    logout
} = loginSlice.actions

export default loginSlice.reducer