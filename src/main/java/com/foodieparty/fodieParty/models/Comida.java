package com.foodieparty.fodieParty.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Entity
public class Comida {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;
     private String nombre;
     private String description;
     private TipoComida tipoComida;
     private Double precio;
     private String iamgen;
     private int disponibilidad;
     @OneToMany(mappedBy = "comida",fetch = FetchType.EAGER)
     private Set<ComidaPedido> comidaPedido=new HashSet<>();
     public Comida(){
     }

    public Comida(String nombre, String description, TipoComida tipoComida, Double precio, String iamgen, int disponibilidad) {
        this.nombre = nombre;
        this.description = description;
        this.tipoComida = tipoComida;
        this.precio = precio;
        this.iamgen = iamgen;
        this.disponibilidad = disponibilidad;
    }

    public long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TipoComida getTipoComida() {
        return tipoComida;
    }

    public void setTipoComida(TipoComida tipoComida) {
        this.tipoComida = tipoComida;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public String getIamgen() {
        return iamgen;
    }

    public void setIamgen(String iamgen) {
        this.iamgen = iamgen;
    }

    public int getDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(int disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    public Set<ComidaPedido> getComidaPedido() {
        return comidaPedido;
    }

    public void setComidaPedido(Set<ComidaPedido> comidaPedido) {
        this.comidaPedido = comidaPedido;
    }
    public void agregarComidaPedido(ComidaPedido comidaPedido1){
         comidaPedido1.setComida(this);
         comidaPedido.add(comidaPedido1);
    }
}