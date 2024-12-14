import { createSlice } from "@reduxjs/toolkit";
import axios from "axios";

const initialState = {
    firstName: "",
    lastName: "",
    userName: "",
    phoneNumber: "",
    email: "",
    password: "",
    loginStatus: 0
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

        handleSignUp: async (state, action) => {
            const createUser = {
                firstName: state.firstName,
                lastName: state.lastName,
                username: state.userName,
                phoneNumber: state.phoneNumber,
                email: state.email,
                password: state.password
            }

            const response = await axios.post("http://localhost:8080/register", createUser).catch((error) => {
                if (error.response) {
                    console.log(error.response.data);
                    console.log(error.response.status);
                }
            })
        },

        handleSignIn: async (state, action) => {
            const loginUser = {
                username: state.userName,
                password: state.password
            }

            try {
                const response = await axios.post("http://localhost:8080/authenticate", loginUser)
                state.loginStatus = response.status
                if (response.status === 200) {
                    console.log("status kodu : ", response.status);
                    console.log(response.data)
                    navigate("/");
                }
            }
            catch (error) {
                if (error.response) {
                    console.log(error.response.data);
                    console.log(error.response.status);
                }
            }
        }
    }
})

export const { setFirstName, setLastName, setUserName, setPhoneNumber, setEmail, setPassword, handleSignUp, handleSignIn } = loginSlice.actions
export default loginSlice.reducer