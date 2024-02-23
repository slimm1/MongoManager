package model;

import java.util.List;
import java.util.Objects;
import org.bson.types.ObjectId;

/**
 * @author Martin Ramonda
 */
public class Show {
    
    private ObjectId id;
    private String titulo;
    private double precio;
    private List<User> usuarios;

    public Show(String titulo, double precio, List<User> usuarios) {
        this.titulo = titulo;
        this.precio = precio;
        this.usuarios = usuarios;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public List<User> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<User> usuarios) {
        this.usuarios = usuarios;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 13 * hash + Objects.hashCode(this.titulo);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Show other = (Show) obj;
        return Objects.equals(this.titulo, other.titulo);
    }
    
    
}
