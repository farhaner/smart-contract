package org.exm.smartcontract.repositories;

import org.exm.smartcontract.models.NasabahRecipient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NasabahRecipientRepository extends JpaRepository<NasabahRecipient, String> {

}

