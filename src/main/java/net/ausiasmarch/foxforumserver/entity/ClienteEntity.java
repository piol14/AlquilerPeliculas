package net.ausiasmarch.foxforumserver.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Pattern;


@Entity
@Table(name = "cliente")
public class ClienteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String direccion;
    private String telefono;
    private boolean rol;
    private String username;
   

    @Pattern(regexp = "^[a-fA-F0-9]+$", message = "Password must be hexadecimal")
    private String password = "e2cac5c5f7e52ab03441bb70e89726ddbd1f6e5b683dde05fb65e0720290179e";  

    
    public ClienteEntity(String username,
             String password) {
        this.username = username;
        this.password = password;
    }
    public ClienteEntity(Long id, String nombre, String direccion, String telefono, boolean rol, String username,
           String password,
            List<AlquilerEntity> alquileres) {
        this.id = id;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.rol = rol;
        this.username = username;
        this.password = password;
        this.alquileres = alquileres;
    }
    public ClienteEntity(String nombre, String direccion, String telefono, boolean rol, String username,
            String password,
            List<AlquilerEntity> alquileres) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.rol = rol;
        this.username = username;
        this.password = password;
        this.alquileres = alquileres;
    }

    @OneToMany(mappedBy = "cliente", fetch = jakarta.persistence.FetchType.LAZY)
    private List<AlquilerEntity> alquileres;

    public ClienteEntity() {
        alquileres = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

     public boolean getRol() {
        return rol;
    }

    public void setRol(boolean rol) {
        this.rol = rol;
    }
    public int getAlquileres() {
        return alquileres.size();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
