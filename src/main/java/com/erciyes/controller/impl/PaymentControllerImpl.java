package com.erciyes.controller.impl;

import com.erciyes.controller.IPaymentController;
import com.erciyes.dto.DtoPayment;
import com.erciyes.model.Payment;
import com.erciyes.service.IPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/payment")
public class PaymentControllerImpl implements IPaymentController {

    @Autowired
    IPaymentService paymentService;

    @PostMapping("/create")
    @Override
    public DtoPayment createPayment(@RequestBody Payment payment) {
        return paymentService.createPayment(payment);
    }

    @GetMapping("/list")
    @Override
    public List<DtoPayment> getAllPayments() {
        return paymentService.getAllPayments();
    }

    @GetMapping("/list/{id}")
    @Override
    public DtoPayment getPaymentById(@PathVariable(name = "id") Long id) {
        return paymentService.getPaymentById(id);
    }

    @DeleteMapping("/delete/{id}")
    @Override
    public void deletePayment(@PathVariable(name = "id") Long id) {
        paymentService.deletePayment(id);
    }

    @PutMapping("/update/{id}")
    @Override
    public DtoPayment updatePayment(@PathVariable(name = "id") @RequestBody Long id, Payment payment) {
        return paymentService.updatePayment(id, payment);
    }
}
