package mx.com.ananda.midgard.repositories;

import mx.com.ananda.midgard.model.entity.ClienteModel;
import mx.com.ananda.midgard.model.entity.OrdenVentaModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface IOrdenVentaRepository extends JpaRepository<OrdenVentaModel,Long> {
    List<OrdenVentaModel> deleteByCliente(ClienteModel cliente);
    Page<OrdenVentaModel> findBySlpCode(Integer slpCode, Pageable pageable);
    Optional<OrdenVentaModel> findByRef1(String ref1);
    Optional<OrdenVentaModel> findByDocNum(Long docNum);
    Page<OrdenVentaModel> findByDocDate(LocalDateTime docDate, Pageable pageable);
    Page<OrdenVentaModel> findByCliente_IdCliente(Long idCliente, Pageable pageable);
}
