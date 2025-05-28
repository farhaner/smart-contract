package org.exm.smartcontract.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "BC_NASABAH_SOURCE_OF_FUND")
public class NasabahSource {

    @Id
    private String id;

    @Column(name = "NIK")
    private String nik;

    @Column(name = "NAME")
    private String name;

    @Column(name = "ETH_BALANCE")
    private String ethBalance;

    @Column(name = "IDR_BALANCE")
    private String idrBalance;

}
