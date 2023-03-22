package com.example.SpringBootBackpack.entitys;

import jakarta.persistence.*;

@Entity
@Table(name = "backpack")
public class Backpack {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long mochila_id;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "capacidad")
    private int capacidad;
    @Column(name = "color")
    private String color;

    public Backpack() {
    }

    public Backpack(Long mochila_id, String nombre, int capacidad, String color) {
        this.mochila_id = mochila_id;
        this.nombre = nombre;
        this.capacidad = capacidad;
        this.color = color;
    }

    public Long getMochila_id() {
        return mochila_id;
    }

    public void setMochila_id(Long mochila_id) {
        this.mochila_id = mochila_id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Backpack{" +
                "mochila_id=" + mochila_id +
                ", nombre='" + nombre + '\'' +
                ", capacidad=" + capacidad +
                ", color='" + color + '\'' +
                '}';
    }
}
