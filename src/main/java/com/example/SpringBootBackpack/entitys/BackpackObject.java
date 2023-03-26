package com.example.SpringBootBackpack.entitys;

import jakarta.persistence.*;

@Entity
@Table(name = "backpack_object")
public class BackpackObject {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long mochila_objeto_id;
    @Column(name = "id_mochilita")
    private int id_mochilita;
    @Column(name = "id_objeto")
    private int id_objeto;

    public BackpackObject() {
    }

    public BackpackObject(Long mochila_objeto_id, int id_mochilita, int id_objeto) {
        this.mochila_objeto_id = mochila_objeto_id;
        this.id_mochilita = id_mochilita;
        this.id_objeto = id_objeto;
    }

    public Long getMochila_objeto_id() {
        return mochila_objeto_id;
    }

    public void setMochila_objeto_id(Long mochila_objeto_id) {
        this.mochila_objeto_id = mochila_objeto_id;
    }

    public int getId_mochilita() {
        return id_mochilita;
    }

    public void setId_mochilita(int id_mochilita) {
        this.id_mochilita = id_mochilita;
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
                ", id_mochila=" + id_mochilita +
                ", id_objeto=" + id_objeto +
                '}';
    }
}
