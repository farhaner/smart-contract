package org.exm.smartcontract.repositories;

import jakarta.transaction.Transactional;
import org.exm.smartcontract.models.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface TransactionsRepository extends JpaRepository<Transactions, String> {

    @Modifying
    @Transactional
    @Query(value =
            "INSERT INTO BC_TRANSACTIONS(ID, ACCOUNT_SOURCE, ACCOUNT_RECEPIENT, AMOUNT, CREATED_AT, UPDATED_AT)\n" +
                    "VALUES (SYS_GUID(), \n" +
                    "        :source, \n" +
                    "        :recepient, \n" +
                    "        :amount, \n" +
                    "        SYSTIMESTAMP, \n" +
                    "        SYSTIMESTAMP\n" +
                    "       )"
            , nativeQuery = true)
    int recordOfTransaction(@Param("source") int source, @Param("recepient") int recepient, @Param("amount") BigDecimal amount);

}
