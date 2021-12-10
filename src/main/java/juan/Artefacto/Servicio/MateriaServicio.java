package juan.Artefacto.Servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import juan.Artefacto.Entidades.Materia;
import juan.Artefacto.Entidades.Usuario;
import juan.Artefacto.Repositorio.MateriasRepositorio;
import juan.Artefacto.excepciones.SpringException;

@Service
public class MateriaServicio {
    @Autowired
    private MateriasRepositorio materiasRepositorio;
	
	@Transactional
    public void habilitar(Integer id) {
		materiasRepositorio.habilitar(id);
    }

    @Transactional
    public void eliminar(Integer id) {
    	materiasRepositorio.deleteById(id);
    }
    @Transactional
    public void crear(Materia dto) throws SpringException {
//        if (usuarioRepository.existsByCorreo(dto.getCorreo())) {
//            throw new SpringException("Ya existe un usuario asociado al correo ingresado");
//        }
    	Materia usuario = new Materia();
        usuario.setNombre(dto.getNombre());
        usuario.setDescripcion(dto.getDescripcion());
        usuario.setAlta(true);
      
        materiasRepositorio.save(usuario);
    }
}
