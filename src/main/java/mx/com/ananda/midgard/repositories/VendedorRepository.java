package mx.com.ananda.midgard.repositories;

import mx.com.ananda.midgard.model.entity.VendedorModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VendedorRepository extends JpaRepository<VendedorModel,Long> {
}
