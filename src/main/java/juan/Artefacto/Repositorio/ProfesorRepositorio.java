package juan.Artefacto.Repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import juan.Artefacto.Entidades.Profesor;
@Repository
public interface ProfesorRepositorio extends JpaRepository<Profesor, Long>{

}
