import { createSlice } from "@reduxjs/toolkit";
import axios from "axios";

const initialState = {
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
        }
    }
})

export const { setFirstName, setLastName, setUserName, setPhoneNumber, setEmail, setPassword, setLoginStatus, setEmailCode, handleSignUp, handleSignIn } = loginSlice.actions
export default loginSlice.reducer