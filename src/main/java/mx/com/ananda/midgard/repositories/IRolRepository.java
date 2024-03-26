package mx.com.ananda.midgard.repositories;


import mx.com.ananda.midgard.model.auth.RolModel;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Primary
public interface IRolRepository extends JpaRepository<RolModel,Long> {

    RolModel findByNombreRol(String nombreRol);
}
