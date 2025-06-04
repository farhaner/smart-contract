package org.exm.smartcontract.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Data
@Table(name = "BC_TRANSACTIONS")
public class Transactions {

    @Id
    private String id;

    @Column(name = "ACCOUNT_RECEPIENT")
    private int accountRecepient;

    @Column(name = "ACCOUNT_SOURCE")
    private int accountSource;

    @Column(name = "PAYMENT_TYPE")
    private BigDecimal paymentType;

    @Column(name = "ETH_BALANCE")
    private BigDecimal ethBalance;

    @Column(name = "IDR_BALANCE")
    private BigDecimal idrBalance;

    @Column(name = "CREATED_AT")
    private Timestamp createdAt;

    @Column(name = "UPDATED_AT")
    private Timestamp updatedAt;
}
