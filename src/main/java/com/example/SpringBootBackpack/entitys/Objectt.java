package com.example.SpringBootBackpack.entitys;

import jakarta.persistence.*;

@Entity
@Table(name = "objectt")
public class Objectt {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long objeto_id;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "tipo")
    private String tipo;
    @Column(name = "costo")
    private float costo;

    public Objectt() {
    }

    public Objectt(Long objeto_id, String nombre, String tipo, float costo) {
        this.objeto_id = objeto_id;
        this.nombre = nombre;
        this.tipo = tipo;
        this.costo = costo;
    }

    public Long getObjeto_id() {
        return objeto_id;
    }

    public void setObjeto_id(Long objeto_id) {
        this.objeto_id = objeto_id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public float getCosto() {
        return costo;
    }

    public void setCosto(float costo) {
        this.costo = costo;
    }

    @Override
    public String toString() {
        return "Objectt{" +
                "objeto_id=" + objeto_id +
                ", nombre='" + nombre + '\'' +
                ", tipo='" + tipo + '\'' +
                ", costo=" + costo +
                '}';
    }
}
