package org.exm.smartcontract.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Data
@Table(name = "BC_TRANSACTIONS")
public class Transactions {

    @Id
    @Column(name = "ID")
    private String id;

    @Column(name = "ACCOUNT_SOURCE", nullable = false)
    private int accountSource;

    @Column(name = "ACCOUNT_RECEPIENT", nullable = false)
    private int accountRecepient;

    @Column(name = "AMOUNT", nullable = false)
    private BigDecimal amount;

    @Column(name = "STATUS", nullable = false)
    private String status;

    @Column(name = "CREATED_AT")
    private Timestamp createdAt;

    @Column(name = "UPDATED_AT")
    private Timestamp updatedAt;

    //    @Column(name = "PAYMENT_TYPE")
//    private BigDecimal paymentType;
//
//    @Column(name = "ETH_BALANCE")
//    private BigDecimal ethBalance;
//
//    @Column(name = "IDR_BALANCE")
//    private BigDecimal idrBalance;
}
