package org.exm.smartcontract.repositories;

import jakarta.transaction.Transactional;
import org.exm.smartcontract.models.Nasabah;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface NasabahRepository extends JpaRepository<Nasabah, String> {

    Nasabah findByAccount(int account);

    @Modifying
    @Transactional
    @Query(value =
            "UPDATE BC_NASABAH\n" +
                    "SET ETH_BALANCE = :eth, UPDATED_AT = SYSTIMESTAMP\n" +
                    "WHERE ACCOUNT = :account"
            , nativeQuery = true)
    int updateEthBalancesByAccount(@Param("account") int account, @Param("eth") BigDecimal eth);

    @Modifying
    @Transactional
    @Query(value =
            "UPDATE BC_NASABAH\n" +
                    "SET IDR_BALANCE = :idr, UPDATED_AT = SYSTIMESTAMP\n" +
                    "WHERE ACCOUNT = :account"
            , nativeQuery = true)
    int updateIdrBalancesByAccount(@Param("account") int account, @Param("idr") BigDecimal idr);

}

