import axios from 'axios';
import React from 'react'
import Button from 'react-bootstrap/Button';
import Modal from 'react-bootstrap/Modal';
import { useDispatch, useSelector } from 'react-redux';
import { setEmailCode } from '../redux/slice/loginSlice';

function EmailVerify(props) {

    const { emailCode } = useSelector((state) => state.login);
    const dispatch = useDispatch();

    const emailVerify = async () => {

        const verify = {
            token: emailCode
        }

        const response = await axios.post("http://localhost:8080/email/verify/check", verify).catch((error) => {
            if (error.response) {
                console.log(error.response.data)
            }
        })
    }


    return (
        <Modal
            {...props}
            aria-labelledby="contained-modal-title-vcenter"
            centered
            style={{ width: "200vh" }}
        >
            <Modal.Header closeButton>
                <Modal.Title id="contained-modal-title-vcenter">
                    Email Doğrulaması
                </Modal.Title>
            </Modal.Header>
            <Modal.Body>
                <h4>Mailinize gelen kodu giriniz</h4>
                <div style={{ display: "flex", justifyContent: "space-between" }} >
                    <input onChange={(e) => dispatch(setEmailCode(e.target.value))} style={{ borderRadius: "2vh", height: "7vh", border: "3px solid lightgray", outline: "none", WebkitAppearance: "none" }} type="number" />
                    <Button style={{ width: "10vh" }} onClick={() => [emailVerify(), props.onHide()]}>Gönder</Button>
                </div>

            </Modal.Body>
            <Modal.Footer>
                <Button style={{ width: "10vh" }} onClick={props.onHide}>Close</Button>
            </Modal.Footer>
        </Modal>
    )
}

export default EmailVerify
