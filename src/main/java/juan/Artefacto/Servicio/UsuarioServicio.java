package juan.Artefacto.Servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


import juan.Artefacto.Entidades.Usuario;
import juan.Artefacto.Repositorio.UsuarioRepositorio;
import juan.Artefacto.enums.Rol;
import juan.Artefacto.excepciones.SpringException;

import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.List;

@Service
public class UsuarioServicio implements UserDetailsService {

    @Autowired
    private UsuarioRepositorio usuarioRepository;

    @Autowired
    private EmailServicio emailService;

    @Autowired
    private BCryptPasswordEncoder encoder;

    private String mensaje = "No existe ningún usuario asociado con el ID %s";

    @Transactional
    public void crear(Usuario dto) throws SpringException {
        if (usuarioRepository.existsByCorreo(dto.getCorreo())) {
            throw new SpringException("Ya existe un usuario asociado al correo ingresado");
        }
        Usuario usuario = new Usuario();
        usuario.setNombre(dto.getNombre());
        usuario.setApellido(dto.getApellido());
        usuario.setCorreo(dto.getCorreo());
        usuario.setClave(encoder.encode(dto.getClave()));
//        if (usuarioRepository.findAll().isEmpty()) {
//			usuario.setRol(Rol.ADMIN);
//        } else if (dto.getRol() == null) {
//            usuario.setRol(Rol.USER);
//        } else {
//            usuario.setRol(dto.getRol());
//        }
        usuario.setRol(dto.getRol());
        usuario.setAlta(true);
        emailService.enviarThread(dto.getCorreo());
        usuarioRepository.save(usuario);
    }

    @Transactional
    public void modificar(Usuario dto) throws SpringException {
        Usuario usuario = usuarioRepository.findById(dto.getId()).orElseThrow(() -> new SpringException(String.format(mensaje, dto.getId())));
        usuario.setNombre(dto.getNombre());
        usuario.setApellido(dto.getApellido());
        usuario.setCorreo(dto.getCorreo());
        usuario.setRol(dto.getRol());
        usuarioRepository.save(usuario);
    }

    @Transactional(readOnly = true)
    public List<Usuario> buscarTodos() {
        return usuarioRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Usuario buscarPorId(Integer id) throws SpringException {
        return usuarioRepository.findById(id).orElseThrow(() -> new SpringException(String.format(mensaje, id)));
    }

    @Transactional
    public void habilitar(Integer id) {
        usuarioRepository.habilitar(id);
    }

    @Transactional
    public void eliminar(Integer id) {
        usuarioRepository.deleteById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String correo) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByCorreo(correo)
                .orElseThrow(() -> new UsernameNotFoundException("No existe un usuario asociado al correo ingresado"));
        GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + usuario.getRol().name());
		System.out.println("Usuario getrol:"+usuario.getRol());
		System.out.println("Usuario get name rol:"+usuario.getRol().name());
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = attributes.getRequest().getSession(true);

        session.setAttribute("id", usuario.getId());
        session.setAttribute("nombre", usuario.getNombre());
        session.setAttribute("apellido", usuario.getApellido());
        session.setAttribute("rol", usuario.getRol().name());
        session.setAttribute("usuariosession", usuario); // llave + valor
		return new User(usuario.getCorreo(), usuario.getClave(), Collections.singletonList(authority));
    }
}
