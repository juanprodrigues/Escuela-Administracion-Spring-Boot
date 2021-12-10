package juan.Artefacto.Entidades;

import java.util.List;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import juan.Artefacto.enums.Rol;
import lombok.Data;

@Entity
@Data
public class Profesor {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nombre;
	private String apellido;
	private String email;
	 @Enumerated(EnumType.STRING)
	 @Column(nullable = false)
	private Rol rol;
//	@OneToMany
//	private Estudiante estudiante;
	@OneToMany
    private List<Estudiante> estudiante;
	@OneToMany
    private List<Materia> materia;
}