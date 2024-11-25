package com.erciyes.exception;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter

public enum MessageType {

    NO_RECORD_EXIST("1004","kayıt bulunamadı"),
    GENERAL_EXCEPTİON("9999","genel bir hata oluştu");

    private String code;

    private String message;

    MessageType(String code, String message){
        this.code=code;
        this.message=message;
    }
}
