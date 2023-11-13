package net.ausiasmarch.foxforumserver.entity;



import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "alquiler")
public class AlquilerEntity {
    @Id
    
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "cliente_id")
    @NotNull
    private ClienteEntity cliente;
@NotNull
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "pelicula_id")
  
    private PeliculaEntity pelicula;


    public void setPelicula(PeliculaEntity pelicula) {
    this.pelicula = pelicula;
}

    private String fecha_alquiler;
    private String fecha_devolucion;

    public AlquilerEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ClienteEntity getCliente() {
        return cliente;
    }

    public void setCliente(ClienteEntity cliente) {
        this.cliente = cliente;
    }
    public PeliculaEntity getPelicula() {
        return pelicula;
    }

    

    public String getFecha_alquiler() {
        return fecha_alquiler;
    }

    public void setFecha_alquiler(String fechaAlquiler) {
        this.fecha_alquiler = fechaAlquiler;
    }

    public String getFecha_devolucion() {
        return fecha_devolucion;
    }

    public void setFecha_devolucion(String fechaDevolucion) {
        this.fecha_devolucion = fechaDevolucion;
    }
}
