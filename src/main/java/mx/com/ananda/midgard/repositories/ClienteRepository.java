package mx.com.ananda.midgard.repositories;

import mx.com.ananda.midgard.model.entity.ClienteModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<ClienteModel,Long> {
}
