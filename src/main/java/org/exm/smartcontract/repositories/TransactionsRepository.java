package org.exm.smartcontract.repositories;

import org.exm.smartcontract.models.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionsRepository extends JpaRepository<Transactions, String> {
//    Transactions findByNik(String nasabah);
}
