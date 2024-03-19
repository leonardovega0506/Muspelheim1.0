package mx.com.ananda.midgard.repositories;

import mx.com.ananda.midgard.model.entity.ClienteModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IClienteRepository extends JpaRepository<ClienteModel,Long> {
    Page<ClienteModel> findByVendedor_SlpCode(Integer slpCode, Pageable pageable);
    Page<ClienteModel> findByVendedor_IdVendedor(Long idVendedor, Pageable pageable);
    Optional<ClienteModel> findByCardCode(Integer cardCode);
    Page<ClienteModel> findByCardName(String cardName, Pageable pageable);
}
