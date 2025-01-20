package com.payment_service.model;

import java.util.Date;

import lombok.Data;
import lombok.Builder;
import jakarta.persistence.Table;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Table(name="payments")
@Entity
@Builder
@Data
public class Payment {
    @Id
    private Integer id;
    private Integer studentId;
    private Double amount;
    private Date paymentDate;

}
