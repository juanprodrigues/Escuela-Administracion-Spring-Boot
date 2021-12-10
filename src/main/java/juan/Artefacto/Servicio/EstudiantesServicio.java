/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juan.Artefacto.Servicio;

import java.util.List;
import java.util.Optional;

import juan.Artefacto.Entidades.Estudiante;
import juan.Artefacto.Entidades.Materia;

/**
 *
 * @author JuanPC
 */
public interface EstudiantesServicio {
	public Materia obtenerMateria(Integer id);
	
	public List<Estudiante> listarTodosLosEstudiantes();

	public Estudiante guardarEstudiantes(Estudiante estudiante);

	public Estudiante obtenerEstudiante(Long id);

	public Estudiante actualizarEstudiantes(Estudiante estudiante);

	public void eliminarEstudiantes(Long id);

	public List<Materia> listar();

	public void eliminar(Integer id);

	public void habilitar(Integer id, Integer idE);

}
