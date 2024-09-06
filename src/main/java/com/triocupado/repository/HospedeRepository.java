package com.triocupado.repository;

import com.triocupado.entity.Hospede;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface HospedeRepository extends JpaRepository<Hospede, Long> {

    @Query(value = "SELECT * FROM hospede h where h.data_check_in = :dataCheckIn", nativeQuery = true)
    Hospede buscarHospedePorDataCheckIn(@Param("dataCheckIn") LocalDate dataCheckIn);

}
