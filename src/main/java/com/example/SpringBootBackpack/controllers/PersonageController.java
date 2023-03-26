package com.example.SpringBootBackpack.controllers;

import com.example.SpringBootBackpack.entitys.Backpack;
import com.example.SpringBootBackpack.entitys.Personage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class PersonageController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/personage")
    public ResponseEntity<List<Personage>> findAll() {
        String sql = "select * from personage";
        List<Personage> personages = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Personage.class));
        System.out.println("List of personages:");
        personages.forEach(System.out :: println);
        return ResponseEntity.ok(personages);
    }

    @GetMapping("/personage/{id}")
    public ResponseEntity<Personage> findAllOneById(@PathVariable Long id) {
        try {
            String sql = "select * from personage where personaje_id = " + id;
            List<Personage> personages = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Personage.class));
            Personage personage = personages.get(0);
            System.out.println("The personage found:\n" +personage);
            return ResponseEntity.ok(personage);
        } catch (Exception exception) {
            System.out.println("The personage was not found.");
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/personage")
    public ResponseEntity<Personage> create(@RequestBody Personage personage) {
        if (personage.getPersonaje_id() != null) {
            System.out.println("The personage is not valid.");
            return ResponseEntity.badRequest().build();
        }
        if (ValidatePersonageFk(personage.getId_mochila())) {
            System.out.println("The id_mochila does not exist.");
            return ResponseEntity.badRequest().build();
        }
        String nombre = personage.getNombre();
        float salud = personage.getSalud();
        float velocidad = personage.getVelocidad();
        int id_mochila = personage.getId_mochila();
        String sql = "insert into personage(nombre, salud, velocidad, id_mochila) values ('" + nombre + "', " + salud + ", " + velocidad + ", " + id_mochila + ")";
        jdbcTemplate.execute(sql);
        System.out.println("Registered personage.");
        return ResponseEntity.ok().build();
    }

    private boolean ValidatePersonageFk(int id_mochila) {
        String sql = "select * from backpack";
        List<Backpack> backpacks = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Backpack.class));
        for (Backpack value : backpacks) {
            if (value.getMochila_id() == id_mochila) {
                return false;
            }
        }
        return true;
    }

    @PutMapping("/personage")
    public ResponseEntity<Personage> update(@RequestBody Personage personage) {
        if (personage.getPersonaje_id() == null) {
            System.out.println("The personaje_id must be different of null.");
            return ResponseEntity.badRequest().build();
        }
        if (ValidatePersonage(personage)) {
            System.out.println("The personage was not found.");
            return ResponseEntity.badRequest().build();
        }
        String nombre = personage.getNombre();
        float salud = personage.getSalud();
        float velocidad = personage.getVelocidad();
        int id_mochila = personage.getId_mochila();
        String sql1 = "update personage set nombre = '" + nombre + "' where personaje_id = " + personage.getPersonaje_id();
        String sql2 = "update personage set salud = " + salud + " where personaje_id = " + personage.getPersonaje_id();
        String sql3 = "update personage set velocidad = " + velocidad + " where personaje_id = " + personage.getPersonaje_id();
        String sql4 = "update personage set id_mochila = " + id_mochila + " where personaje_id = " + personage.getPersonaje_id();
        jdbcTemplate.execute(sql1);
        jdbcTemplate.execute(sql2);
        jdbcTemplate.execute(sql3);
        jdbcTemplate.execute(sql4);
        System.out.println("Updated personage.");
        return ResponseEntity.ok(personage);
    }

    private boolean ValidatePersonage(Personage personage) {
        try {
            String sql = "select * from personage where personaje_id = " + personage.getPersonaje_id();
            List<Personage> personages = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Personage.class));
            personage = personages.get(0);
            return false;
        } catch (Exception exception) {
            return true;
        }
    }

    @DeleteMapping("/personage")
    public ResponseEntity<Personage> deleteAll() {
        String sql = "delete from personage";
        jdbcTemplate.execute(sql);
        System.out.println("Deleted registrations.");
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/personage/{id}")
    public ResponseEntity<Personage> delete(@PathVariable Long id) {
        try {
            if (ValidatePersonage(id)) {
                throw new Exception();
            }
            String sql = "delete from personage where personaje_id = " + id;
            jdbcTemplate.execute(sql);
            System.out.println("Deleted registration.");
            return ResponseEntity.ok().build();
        } catch (Exception exception) {
            System.out.println("The personage was not found.");
            return ResponseEntity.badRequest().build();
        }
    }

    private boolean ValidatePersonage(Long id) {
        try {
            String sql = "select * from personage where personaje_id =" + id;
            List<Personage> personages = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Personage.class));
            Personage personage = personages.get(0);
            return false;
        } catch (Exception exception) {
            return true;
        }
    }
}
