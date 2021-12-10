package juan.Artefacto.Controlador;

import juan.Artefacto.Entidades.Estudiante;
import juan.Artefacto.Entidades.Materia;
import juan.Artefacto.Entidades.Usuario;
import juan.Artefacto.Servicio.EstudiantesServicio;
import juan.Artefacto.Servicio.MateriaServicio;
import juan.Artefacto.Servicio.UsuarioServicio;
import juan.Artefacto.excepciones.SpringException;

import java.security.Principal;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.servlet.view.RedirectView;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Map;

@Controller
public class EstudianteControlador {
	@Autowired
	private MateriaServicio materiaServicio;
	@Autowired
	private UsuarioServicio usuarioServicio;
	@Autowired
	private EstudiantesServicio servicio;

	@GetMapping("/login")
	public ModelAndView login(@RequestParam(required = false) String error,
			@RequestParam(required = false) String logout, Principal principal) {
		ModelAndView mav = new ModelAndView("login");

		if (error != null) {
			mav.addObject("error", "Correo o contraseña inválida");
		}

		if (logout != null) {
			mav.addObject("logout", "Ha salido correctamente de la plataforma");
		}

		if (principal != null) {
			mav.setViewName("redirect:/login");
		}

		return mav;
	}

	@GetMapping("/signup")
	public ModelAndView signup(HttpServletRequest request, Principal principal) {
		ModelAndView mav = new ModelAndView("signup");
		Map<String, ?> flashMap = RequestContextUtils.getInputFlashMap(request);

		if (principal != null) {
			mav.setViewName("redirect:/login");
		}

		if (flashMap != null) {
			mav.addObject("error", flashMap.get("error"));
			mav.addObject("usuario", flashMap.get("usuario"));
		} else {
			mav.addObject("usuario", new Usuario());
		}

		return mav;
	}

	@PostMapping("/registro")
	public RedirectView signup(@ModelAttribute Usuario usuario, HttpServletRequest request, RedirectView attributes) {
		RedirectView redirectView = new RedirectView("/login");

		try {
			usuarioServicio.crear(usuario);
			request.login(usuario.getCorreo(), usuario.getClave());
		} catch (SpringException e) {

			attributes.addStaticAttribute("usuario", usuario);
			attributes.addStaticAttribute("error", e.getMessage());
			redirectView.setUrl("/signup");
		} catch (ServletException e) {
			attributes.addStaticAttribute("error", "Error al realizar auto-login");
		}

		return redirectView;
	}

//    
//    @GetMapping({"/estudiantes", "/"})
//    public ModelAndView signup1(HttpServletRequest request, Principal principal) {
//        ModelAndView mav = new ModelAndView("estudiantes2");
//        Map<String, ?> flashMap = RequestContextUtils.getInputFlashMap(request);
//
//       
//
//        if (flashMap != null) {
//            mav.addObject("error", flashMap.get("error"));
//            mav.addObject("usuario", flashMap.get("usuario"));
//        } else {
//            mav.addObject("usuario", new Usuario());
//        }
//
//        return mav;
//    }

	@GetMapping("/materia")
	public ModelAndView mostrarMateria(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("materias/listar-materias");
		Map<String, ?> flashMap = RequestContextUtils.getInputFlashMap(request);

		if (flashMap != null) {
			mav.addObject("exito", flashMap.get("exito"));
			mav.addObject("error", flashMap.get("error"));
		}

		mav.addObject("materias", servicio.listar());
		return mav;
	}
	
	@GetMapping("/misMaterias")
    public ModelAndView misMaterias(HttpServletRequest request,@RequestParam(required = false) Integer idE) {
		ModelAndView mav = new ModelAndView("materias/listar-materias");
		Map<String, ?> flashMap = RequestContextUtils.getInputFlashMap(request);

		if (flashMap != null) {
			mav.addObject("exito", flashMap.get("exito"));
			mav.addObject("error", flashMap.get("error"));
		}
	
		mav.addObject("materias", servicio.listar());
		return mav;
	}

    @PostMapping("/habilitar/{id}")
    public RedirectView habilitar(@PathVariable Integer id,@RequestParam(required = false) Integer idE) {
    	System.out.println("wslmgbspodgmnspgnmbspwdmbs{dmbsw{bnmdsb{nre{ojhnmern{me{nrom"+idE);
    	
    	servicio.habilitar(id,idE);
    	
        return new RedirectView("/materia");
    }

    @PostMapping("/eliminar/{id}")
    public RedirectView eliminar(@PathVariable Integer id) {
    	servicio.eliminar(id);
        return new RedirectView("/materia");
    }


    @GetMapping("/crear")
    public ModelAndView crear(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("materias/materias-formulario");
        Map<String, ?> flashMap = RequestContextUtils.getInputFlashMap(request);

        if (flashMap != null) {
            mav.addObject("error", flashMap.get("error"));
            mav.addObject("usuario", flashMap.get("usuario"));
        } else {
            mav.addObject("materia", new Materia());
        }

        mav.addObject("title", "Crear Usuario");
        mav.addObject("action", "guardar");
        return mav;
    }
    
    
    @PostMapping("/guardar")
    public RedirectView guardar(@ModelAttribute Materia materia, RedirectAttributes attributes) {
        RedirectView redirectView = new RedirectView("/usuario");

        try {
        	materiaServicio.crear(materia);
            attributes.addFlashAttribute("exito", "La creación ha sido realizada satisfactoriamente");
        } catch (SpringException e) {
            attributes.addFlashAttribute("usuario", materia);
            attributes.addFlashAttribute("error", e.getMessage());
            redirectView.setUrl("/usuario/crear");
        }

        return redirectView;
    }
    
    
    
    
    
    
    
    
    
    
    
    @GetMapping({ "/estudiantes", "/" })
    public ModelAndView mostrar(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("estudiantes");
        Map<String, ?> flashMap = RequestContextUtils.getInputFlashMap(request);

        if (flashMap != null) {
            mav.addObject("exito", flashMap.get("exito"));
            mav.addObject("error", flashMap.get("error"));
        }

        mav.addObject("usuarios", usuarioServicio.buscarTodos());
        return mav;
    }
    
    
//	
//	@GetMapping({ "/estudiantes", "/" })
//	public ModelAndView mostrar(HttpServletRequest request) {
//		ModelAndView mav = new ModelAndView("estudiantes");
//		Map<String, ?> flashMap = RequestContextUtils.getInputFlashMap(request);
//
//		if (flashMap != null) {
//			mav.addObject("exito", flashMap.get("exito"));
//			mav.addObject("error", flashMap.get("error"));
//		}
//
//		mav.addObject("estudiantes", servicio.listarTodosLosEstudiantes());
//		return mav;
//	}

//    
//    @GetMapping({"/estudiantes", "/"})
//    public String listarEstudiantes(Model modelo) {
//        
//        modelo.addAttribute("estudiantes", servicio.listarTodosLosEstudiantes());
//        return "estudiantes"; // nos retorna al archivo estudiantes
//    }

	@GetMapping("/estudiantes/nuevo")
	public String mostrarFormularioDeRegistrtarEstudiante(Model modelo) {
		Estudiante estudiante = new Estudiante();
		modelo.addAttribute("estudiante", estudiante);
		return "crear_estudiante";
	}

	@PostMapping("/estudiantes")
	public String guardarEstudiante(@ModelAttribute("estudiante") Estudiante estudiante) {
		servicio.guardarEstudiantes(estudiante);
		return "redirect:/estudiantes";
	}

	@GetMapping("/estudiantes/editar/{id}")
	public String mostrarFormularioDeEditar(@PathVariable Long id, Model modelo) {
		modelo.addAttribute("estudiante", servicio.obtenerEstudiante(id));
		return "editar_estudiante";
	}

	@PostMapping("/estudiantes/{id}")
	public String actualizarEstudiante(@PathVariable Long id, @ModelAttribute("estudiante") Estudiante estudiante,
			Model modelo) {
		Estudiante estudianteExistente = servicio.obtenerEstudiante(id);
		estudianteExistente.setId(id);
		estudianteExistente.setNombre(estudiante.getNombre());
		estudianteExistente.setApellido(estudiante.getApellido());
		estudianteExistente.setEmail(estudiante.getEmail());

		servicio.actualizarEstudiantes(estudiante);
		return "redirect:/estudiantes";
	}

	@GetMapping("/estudiantes/{id}")
	public String eliminarEstudiante(@PathVariable Long id) {
		servicio.eliminarEstudiantes(id);
		return "redirect:/estudiantes";
	}
}
