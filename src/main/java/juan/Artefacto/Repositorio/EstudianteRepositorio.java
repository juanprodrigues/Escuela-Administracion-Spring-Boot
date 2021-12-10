/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juan.Artefacto.Repositorio;

import juan.Artefacto.Entidades.Estudiante;
import juan.Artefacto.Entidades.Usuario;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author JuanPC
 */
@Repository
public interface EstudianteRepositorio extends JpaRepository<Estudiante, Long>{
	
	
//	@Query("SELECT m.fk_materia FROM rel_usuario_materia m WHERE m.fk_usuario= :id")
//	List<Integer> materias(@Param("id") Integer id);
	
	
    
}
