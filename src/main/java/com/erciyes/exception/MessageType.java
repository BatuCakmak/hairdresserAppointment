package com.erciyes.exception;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter

public enum MessageType {

    NO_RECORD_EXIST("1004","kayıt bulunamadı"),
    TOKEN_IS_EXPIREDI("1005","tokenın süresi bitmiştir"),
    USERNAME_NOT_FOUND("1006","username bulunamadı"),
    USERNAME_OR_PASSWORD_INVALID("1007","kullanıcı adı veya şifre hatalı"),
    GENERAL_EXCEPTİON("9999","genel bir hata oluştu"),
    HAIRDRESSER_ALREADY_EXIST("1006","Kuafor zaten mevcut");


    private String code;

    private String message;

    MessageType(String code, String message){
        this.code=code;
        this.message=message;
    }
}
