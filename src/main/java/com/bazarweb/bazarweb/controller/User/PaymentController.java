package com.bazarweb.bazarweb.controller.User;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bazarweb.bazarweb.dto.PaymentDto;
import com.bazarweb.bazarweb.model.User.Payment;
import com.bazarweb.bazarweb.service.User.PaymentService;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/payment")
@RequiredArgsConstructor
public class PaymentController {
    
    private final PaymentService paymentService;

    @GetMapping("/{id}")
    public ResponseEntity<PaymentDto> getPayment(@PathVariable int id){
        Payment payment = paymentService.findById(id);
        return ResponseEntity.ok(convertToDto(payment));
    }

    @PostMapping("/create")
    public ResponseEntity<PaymentDto> createPayment(@RequestBody Payment payment) {
        Payment savedPayment = paymentService.paymentCreate(payment);
        return ResponseEntity.status(HttpStatus.CREATED).body(convertToDto(savedPayment));
    }

    @DeleteMapping("/{id}")
    public void paymentDelete(@PathVariable int id){
        paymentService.paymentDelete(id);
    }

    private PaymentDto convertToDto(Payment payment){
        PaymentDto dto = new PaymentDto();
        dto.setCardNumber(payment.getCardNumber());
        dto.setCvvCode(payment.getCvvCode());
        dto.setExpiryDate(payment.getExpiryDate());

        if(payment.getUser() != null){
            dto.setId(payment.getUser().getId());
        }

        return dto;
    }
    
}
