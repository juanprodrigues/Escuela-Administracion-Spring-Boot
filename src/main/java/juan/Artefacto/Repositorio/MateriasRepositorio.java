package juan.Artefacto.Repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import juan.Artefacto.Entidades.Materia;
@Repository
public interface MateriasRepositorio extends JpaRepository<Materia, Integer>{
	@Modifying
    @Query("UPDATE Materia u SET u.alta = true WHERE u.id = :id")
    void habilitar(@Param("id") Integer id);

}
