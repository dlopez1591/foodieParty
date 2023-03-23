package com.foodieparty.fodieParty.DTO;

import com.foodieparty.fodieParty.models.Comida;
import com.foodieparty.fodieParty.models.ComidaPedido;
import com.foodieparty.fodieParty.models.TipoComida;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toSet;

public class ComidaDTO {
    private long id;
    private String nombre;
    private String description;
    private TipoComida tipoComida;
    private Double precio;
    private String imagen;
    private int disponibilidad;
    private Set<ComidaPedidoDTO> comidaPedido;
    public ComidaDTO(Comida comida){
        this.id=comida.getId();
        this.nombre= comida.getNombre();
        this.description=comida.getDescription();
        this.tipoComida=comida.getTipoComida();
        this.precio= comida.getPrecio();
        this.imagen= comida.getIamgen();
        this.disponibilidad= comida.getDisponibilidad();
        this.comidaPedido=comida.getComidaPedido().stream().map(ComidaPedidoDTO::new).collect(toSet());
    }

    public long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescription() {
        return description;
    }

    public TipoComida getTipoComida() {
        return tipoComida;
    }

    public Double getPrecio() {
        return precio;
    }

    public String getImagen() {
        return imagen;
    }

    public int getDisponibilidad() {
        return disponibilidad;
    }

    public Set<ComidaPedidoDTO> getComidaPedido() {
        return comidaPedido;
    }
}
