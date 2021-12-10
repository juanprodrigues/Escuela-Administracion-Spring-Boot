/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juan.Artefacto.Servicio;

import java.util.*;

import juan.Artefacto.Entidades.Estudiante;
import juan.Artefacto.Entidades.Materia;
import juan.Artefacto.Entidades.Usuario;
import juan.Artefacto.Repositorio.EstudianteRepositorio;
import juan.Artefacto.Repositorio.MateriasRepositorio;
import juan.Artefacto.Repositorio.UsuarioRepositorio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author JuanPC
 */
@Service
public class EstudianteServicionImp implements EstudiantesServicio {

	@Autowired
	private EstudianteRepositorio estudianteRepositorio;

	@Autowired
	private MateriasRepositorio materiasRepositorio;

	@Autowired
	private UsuarioRepositorio usuarioRepositorio;
	@Override
	public List<Estudiante> listarTodosLosEstudiantes() {
		return estudianteRepositorio.findAll();
	}

	@Override
	public Estudiante guardarEstudiantes(Estudiante estudiante) {
		return estudianteRepositorio.save(estudiante);
	}

	@Override
	public Estudiante obtenerEstudiante(Long id) {
		return estudianteRepositorio.findById(id).get();
	}

	@Override
	public Estudiante actualizarEstudiantes(Estudiante estudiante) {
		return estudianteRepositorio.save(estudiante);
	}

	@Override
	public void eliminarEstudiantes(Long id) {
		estudianteRepositorio.deleteById(id);
	}

	@Override
	public List<Materia> listar() {
		return materiasRepositorio.findAll();
	}

	@Override
	@Transactional
	public Materia obtenerMateria(Integer id) {
		return materiasRepositorio.findById(id).get();
	}

	@Override
	@Transactional
	public void habilitar(Integer id, Integer idE) {
		System.out.println("scaacs"+idE);
		System.out.println("servicio1");
		
		Usuario es1 =usuarioRepositorio.findById(idE).get();

		System.out.println("servicio2");

		System.out.println(es1.toString());
	
		Materia mat = obtenerMateria(id);
	
		
		es1.addAuthor(mat);
		
		usuarioRepositorio.save(es1);
		List<Materia> materias=es1.getMateria();
	
		for (Materia materia1 : materias) {
			System.out.println("materiasSS: "+materia1);
		}
		
		materiasRepositorio.habilitar(id);
	}
	
	

	
	@Transactional
	public void eliminar(Integer id) {
		materiasRepositorio.deleteById(id);
	}

}
