package com.example.SpringBootBackpack.entitys;

import jakarta.persistence.*;

@Entity
@Table(name = "backpack_object")
public class BackpackObject {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long mochila_objeto_id;
    @Column(name = "id_mochila")
    private int id_mochila;
    @Column(name = "id_objeto")
    private int id_objeto;

    public BackpackObject() {
    }

    public BackpackObject(Long mochila_objeto_id, int id_mochila, int id_objeto) {
        this.mochila_objeto_id = mochila_objeto_id;
        this.id_mochila = id_mochila;
        this.id_objeto = id_objeto;
    }

    public Long getMochila_objeto_id() {
        return mochila_objeto_id;
    }

    public void setMochila_objeto_id(Long mochila_objeto_id) {
        this.mochila_objeto_id = mochila_objeto_id;
    }

    public int getId_mochila() {
        return id_mochila;
    }

    public void setId_mochila(int id_mochila) {
        this.id_mochila = id_mochila;
    }

    public int getId_objeto() {
        return id_objeto;
    }

    public void setId_objeto(int id_objeto) {
        this.id_objeto = id_objeto;
    }

    @Override
    public String toString() {
        return "BackpackObject{" +
                "mochila_objeto_id=" + mochila_objeto_id +
                ", id_mochila=" + id_mochila +
                ", id_objeto=" + id_objeto +
                '}';
    }
}
