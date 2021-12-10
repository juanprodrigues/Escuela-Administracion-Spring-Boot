package juan.Artefacto.Entidades;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.SQLDelete;

import juan.Artefacto.Servicio.UsuarioServicio;
import lombok.Data;

@Entity
@Data
@SQLDelete(sql = "UPDATE materia SET alta = false WHERE id = ?")
public class Materia {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nombre;
    private String descripcion;
    private Boolean alta;
    private Double nota;
   
    @ManyToMany(mappedBy = "materia")
    private List<Usuario> usuario;
//    private Usuario usuario;
  
   
}
