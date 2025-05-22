package org.exm.smartcontract.dto.request;

import lombok.Data;

@Data
public class SmartContractRequest {

    private String nik;
    private String nama;
    private String tanggalLahir;
    private String jenisKelamin;
    private String alamat;
    private String agama;
}
