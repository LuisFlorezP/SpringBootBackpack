package com.example.SpringBootBackpack.entitys;

import jakarta.persistence.*;

@Entity
@Table(name = "personage")
public class Personage {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long personaje_id;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "salud")
    private float salud;
    @Column(name = "velocidad")
    private float velocidad;
    @Column(name = "id_mochila")
    private int id_mochila;

    public Personage() {
    }

    public Personage(Long personaje_id, String nombre, float salud, float velocidad, int id_mochila) {
        this.personaje_id = personaje_id;
        this.nombre = nombre;
        this.salud = salud;
        this.velocidad = velocidad;
        this.id_mochila = id_mochila;
    }

    public Long getPersonaje_id() {
        return personaje_id;
    }

    public void setPersonaje_id(Long personaje_id) {
        this.personaje_id = personaje_id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public float getSalud() {
        return salud;
    }

    public void setSalud(float salud) {
        this.salud = salud;
    }

    public float getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(float velocidad) {
        this.velocidad = velocidad;
    }

    public int getId_mochila() {
        return id_mochila;
    }

    public void setId_mochila(int id_mochila) {
        this.id_mochila = id_mochila;
    }

    @Override
    public String toString() {
        return "Personage{" +
                "personaje_id=" + personaje_id +
                ", nombre='" + nombre + '\'' +
                ", salud=" + salud +
                ", velocidad=" + velocidad +
                ", id_mochila=" + id_mochila +
                '}';
    }
}
