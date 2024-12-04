package com.erciyes.controller.impl;

//import com.erciyes.service.MailService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//public class MailControllerImpl {
//
//    private final MailService mailService;
//
//    @Autowired
//    public MailControllerImpl(MailService mailService) {
//        this.mailService = mailService;
//    }
//
//    @GetMapping("normal/{email}")
//    public ResponseEntity<String> sendMail(@PathVariable(name= "email") String email) {
//        return ResponseEntity.ok(mailService.sendMail(email));
//    }
//
//}
