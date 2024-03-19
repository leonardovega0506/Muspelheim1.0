package mx.com.ananda.midgard.repositories;

import mx.com.ananda.midgard.model.entity.OrdenVentaModel;
import mx.com.ananda.midgard.model.entity.OrdenVentaRequestModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface IOrdenVentaRequestRepository extends JpaRepository<OrdenVentaRequestModel,Long> {
    Page<OrdenVentaRequestModel> findByDocDate(LocalDate docDate, Pageable pageable);
}
