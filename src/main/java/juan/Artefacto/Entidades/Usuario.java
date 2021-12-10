package juan.Artefacto.Entidades;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.SQLDelete;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import juan.Artefacto.enums.Rol;
import lombok.Data;


@Entity
@Data
@EntityListeners(AuditingEntityListener.class)
@SQLDelete(sql = "UPDATE usuario SET alta = false WHERE id = ?")

public class Usuario {

    @Id
	@GeneratedValue
    private Integer id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String apellido;

    @Column(nullable = false, unique = true)
    private String correo;

    @Column(nullable = false)
    private String clave;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Rol rol;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime creacion;

    @LastModifiedDate
    private LocalDateTime modificacion;

    private Boolean alta;
   
    @JoinTable(
            name = "rel_usuario_materia",
            joinColumns = @JoinColumn(name = "FK_USUARIO"),
            inverseJoinColumns = @JoinColumn(name="FK_MATERIA")
        )
    @ManyToMany(cascade = CascadeType.ALL)
    private List<Materia> materia;
    
    public void addAuthor(Materia materiaIngresa){
        if(this.materia == null){
            this.materia = new ArrayList<>();
        }
        
        this.materia.add(materiaIngresa);
    }
    
    @Override
    public String toString() {
        return String.format("USUARIO (id: %s, nombre: %s, apellido: %s, correo: %s, clave: %s)", id, nombre, apellido, correo, clave);
    }

    
//	public Usuario( String nombre, String apellido, String correo, String clave, Rol rol,Boolean alta) {
//		
//	
//		this.nombre = nombre;
//		this.apellido = apellido;
//		this.correo = correo;
//		this.clave = clave;
//		this.rol = rol;		
//		this.alta = alta;
//	}
}
