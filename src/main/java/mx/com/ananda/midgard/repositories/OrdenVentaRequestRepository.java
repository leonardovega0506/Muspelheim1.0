package mx.com.ananda.midgard.repositories;

import mx.com.ananda.midgard.model.entity.OrdenVentaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdenVentaRequestRepository extends JpaRepository<OrdenVentaModel,Long> {
}
