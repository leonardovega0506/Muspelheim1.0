package mx.com.ananda.midgard.repositories;

import mx.com.ananda.midgard.model.entity.ClienteModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface IClienteRepository extends JpaRepository<ClienteModel,Long> {
    List<ClienteModel> findByCardNameContains(String cardName);


    Page<ClienteModel> findByVendedor_SlpCode(Integer slpCode, Pageable pageable);
    Page<ClienteModel> findByVendedor_IdVendedor(Long idVendedor, Pageable pageable);
    Optional<ClienteModel> findByCardCode(String cardCode);
}
