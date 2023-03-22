package com.example.SpringBootBackpack.controllers;

import com.example.SpringBootBackpack.entitys.Backpack;
import com.example.SpringBootBackpack.entitys.Objectt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ObjecttController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/objectt")
    public ResponseEntity<List<Objectt>> findAll() {
        String sql = "select * from objectt";
        List<Objectt> objectts = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Objectt.class));
        System.out.println("List of objects:");
        objectts.forEach(System.out :: println);
        return ResponseEntity.ok(objectts);
    }

    @GetMapping("/objectt/{id}")
    public ResponseEntity<Objectt> findAllOneById(@PathVariable Long id) {
        try {
            String sql = "select * from objectt where objeto_id = " + id;
            List<Objectt> objectts = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Objectt.class));
            Objectt objectt = objectts.get(0);
            System.out.println("The object found:\n" + objectt);
            return ResponseEntity.ok(objectt);
        } catch (Exception exception) {
            System.out.println("The object was not found.");
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/objectt")
    public ResponseEntity<Objectt> create(@RequestBody Objectt objectt) {
        if (objectt.getObjeto_id() != null) {
            System.out.println("The object is not valid.");
            return ResponseEntity.badRequest().build();
        }
        String nombre = objectt.getNombre();
        String tipo = objectt.getTipo();
        float costo = objectt.getCosto();
        String sql = "insert into objectt(nombre, tipo, costo) values ('" + nombre + "', '" + tipo + "', " + costo + ")";
        jdbcTemplate.execute(sql);
        System.out.println("Registered object.");
        return ResponseEntity.ok().build();
    }

    @PutMapping("/objectt")
    public ResponseEntity<Objectt> update(@RequestBody Objectt objectt) {
        if (objectt.getObjeto_id() == null) {
            System.out.println("The objeto_id must be different of null.");
            return ResponseEntity.badRequest().build();
        }
        if (ValidateObjectt(objectt)) {
            System.out.println("The object was not found.");
            return ResponseEntity.badRequest().build();
        }
        String nombre = objectt.getNombre();
        String tipo = objectt.getTipo();
        float costo = objectt.getCosto();
        String sql1 = "update objectt set nombre = '" + nombre + "' where objeto_id = " + objectt.getObjeto_id();
        String sql2 = "update objectt set tipo = " + tipo + " where objeto_id = " + objectt.getObjeto_id();
        String sql3 = "update objectt set costo = '" + costo + "' where objeto_id = " + objectt.getObjeto_id();
        jdbcTemplate.execute(sql1);
        jdbcTemplate.execute(sql2);
        jdbcTemplate.execute(sql3);
        System.out.println("Updated object.");
        return ResponseEntity.ok(objectt);
    }

    private boolean ValidateObjectt(Objectt objectt) {
        try {
            String sql = "select * from objectt where objeto_id = " + objectt.getObjeto_id();
            List<Objectt> objectts = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Objectt.class));
            objectt = objectts.get(0);
            return false;
        } catch (Exception exception) {
            return true;
        }
    }

    @DeleteMapping("/objectt")
    public ResponseEntity<Objectt> deleteAll() {
        String sql = "delete from objectt";
        jdbcTemplate.execute(sql);
        System.out.println("Deleted registrations.");
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/objectt/{id}")
    public ResponseEntity<Objectt> delete(@PathVariable Long id) {
        try {
            if (ValidateObjectt(id)) {
                throw new Exception();
            }
            String sql = "delete from objectt where objeto_id = " + id;
            jdbcTemplate.execute(sql);
            System.out.println("Deleted registration.");
            return ResponseEntity.ok().build();
        } catch (Exception exception) {
            System.out.println("The object was not found.");
            return ResponseEntity.badRequest().build();
        }
    }

    private boolean ValidateObjectt(Long id) {
        try {
            String sql = "select * from objectt where objeto_id =" + id;
            List<Objectt> objectts = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Objectt.class));
            Objectt objectt = objectts.get(0);
            return false;
        } catch (Exception exception) {
            return true;
        }
    }
}
