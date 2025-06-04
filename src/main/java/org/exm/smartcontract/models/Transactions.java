package org.exm.smartcontract.models;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Data
@Table(name = "BC_TRANSACTIONS")
public class Transactions {

    @Id
    @Column(name = "ID")
    private String id;

    @Column(name = "ACCOUNT_SOURCE")
    private int accountSource;

    @Column(name = "ACCOUNT_RECEPIENT")
    private int accountRecepient;
//    @Column(name = "PAYMENT_TYPE")
//    private BigDecimal paymentType;
//
//    @Column(name = "ETH_BALANCE")
//    private BigDecimal ethBalance;
//
//    @Column(name = "IDR_BALANCE")
//    private BigDecimal idrBalance;

    @Column(name = "AMOUNT")
    private BigDecimal amount;

    @Column(name = "CREATED_AT")
    private Timestamp createdAt;

    @Column(name = "UPDATED_AT")
    private Timestamp updatedAt;
}
