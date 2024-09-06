package com.triocupado.repository;

import com.triocupado.entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Long> {

    @Query(value = "SELECT h.* " +
            "FROM hotel h " +
            "JOIN quarto q ON h.id = q.hotel_id " +
            "LEFT JOIN hospede o ON q.id = o.quarto_id " +
            "WHERE (:localizacao IS NULL OR LOWER(h.localizacao) LIKE LOWER(CONCAT('%', :localizacao, '%'))) " +
            "AND ( " +
            "  :dataCheckIn IS NULL OR :dataCheckOut IS NULL OR " +
            "  NOT EXISTS ( " +
            "    SELECT 1 " +
            "    FROM hospede o2 " +
            "    WHERE o2.quarto_id = q.id " +
            "    AND ( " +
            "      (:dataCheckIn < o2.data_check_out AND :dataCheckOut > o2.data_check_in) " +
            "    ) " +
            "  ) " +
            ") " +
            "AND q.quantidade_hospede >= :numeroHospedes " +
            "GROUP BY h.id", nativeQuery = true)
    List<Hotel> filtrarHoteis(
            @Param("localizacao") String localizacao,
            @Param("dataCheckIn") LocalDate dataCheckIn,
            @Param("dataCheckOut") LocalDate dataCheckOut,
            @Param("numeroHospedes") Integer numeroHospedes
    );

    @Query(value = "SELECT h.* " +
            "FROM hotel h " +
            "WHERE (:localizacao IS NULL OR LOWER(h.localizacao) LIKE LOWER(CONCAT('%', :localizacao, '%')))", nativeQuery = true)
    List<Hotel> buscarHoteisPorLocalizacao(
            @Param("localizacao") String localizacao
    );

}
