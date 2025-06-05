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
@Table(name = "BC_NASABAH")
public class Nasabah {

    @Id
    @Column(name = "ID")
    private String id;

    @Column(name = "NIK", unique = true, nullable = false)
    private String nik;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "ETH_BALANCE", nullable = false)
    private BigDecimal ethBalance;

    @Column(name = "IDR_BALANCE", nullable = false)
    private BigDecimal idrBalance;

    @Column(name = "ACCOUNT", unique = true, nullable = false)
    private int account;

    @Column(name = "CREATED_AT", nullable = false)
    private Timestamp createdAt;

    @Column(name = "UPDATED_AT", nullable = false)
    private Timestamp updatedAt;

}
