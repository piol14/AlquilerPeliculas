package net.ausiasmarch.foxforumserver.entity;



import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "alquiler")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")

public class AlquilerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cliente_id")
    @NotNull
    private ClienteEntity cliente;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "pelicula_id")
    private PeliculaEntity pelicula;
    
    

    public void setPelicula(PeliculaEntity pelicula) {
    this.pelicula = pelicula;
}

@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
private LocalDate fecha_alquiler;

@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
 private LocalDate fecha_devolucion;

    

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

public LocalDate getFecha_alquiler() {
    return fecha_alquiler;
}

public void setFecha_alquiler(LocalDate fecha_alquiler) {
    this.fecha_alquiler = fecha_alquiler;
}
    
public LocalDate getFecha_devolucion() {
    return fecha_devolucion;
}

public void setFecha_devolucion(LocalDate fecha_devolucion) {
    this.fecha_devolucion = fecha_devolucion;
}
   
}
