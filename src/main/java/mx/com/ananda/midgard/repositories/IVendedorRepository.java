package mx.com.ananda.midgard.repositories;

import mx.com.ananda.midgard.model.entity.VendedorModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IVendedorRepository extends JpaRepository<VendedorModel,Long> {
    Page<VendedorModel> findByGroupCode(Integer groupCode, Pageable pageable);
    Page<VendedorModel> findBySlpNameLike(String slpName, Pageable pageable);
    Optional<VendedorModel> findBySlpCode(Integer slpCode);
}
