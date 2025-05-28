package org.exm.smartcontract.repositories;

import org.exm.smartcontract.models.NasabahSource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NasabahSourceRepository extends JpaRepository<NasabahSource, String> {
}
