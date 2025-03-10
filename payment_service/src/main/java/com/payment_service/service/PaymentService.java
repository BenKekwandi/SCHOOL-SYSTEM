package com.payment_service.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.payment_service.dto.PaymentEvent;
import com.payment_service.dto.PaymentRequest;
import com.payment_service.dto.PaymentResponse;
import com.payment_service.model.Payment;
import com.payment_service.repository.PaymentRepository;
import com.payment_service.producer.PaymentProducer;

import lombok.extern.slf4j.Slf4j;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Slf4j
@Service
public class PaymentService{

    private final PaymentRepository paymentRepository;
    private final PaymentProducer paymentProducer;

    public List<PaymentResponse> getAllPayments(){

        List<Payment> payments = paymentRepository.findAll();
        return payments.stream().map(this::mapToPaymentResponse).toList();
        
    }

    public void getPayment(){

    }


    public Payment createPayment(PaymentRequest request){
        Payment payment = Payment.builder()
            .studentId(request.getStudentId())
            .amount(request.getAmount())
            .paymentDate(request.getPaymentDate())
            .build();
        paymentRepository.save(payment);
         PaymentEvent event = PaymentEvent.builder()
                .studentId(payment.getStudentId())
                .amount(payment.getAmount())
                .paymentId(String.valueOf(payment.getId()))
                .paymentDate(payment.getPaymentDate())
                .build();
        paymentProducer.publishPaymentEvent(event);
        return payment;
    }

    public void editPayment(PaymentRequest request){

    }

    public void deletePayment(){
        
    }

    public PaymentResponse mapToPaymentResponse(Payment Payment){
        return PaymentResponse.builder()
        .id(Payment.getId())
        .studentId(Payment.getStudentId())
        .amount(Payment.getAmount())
        .paymentDate(Payment.getPaymentDate())
        .build();
    }
}