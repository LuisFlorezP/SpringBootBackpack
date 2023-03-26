package com.example.SpringBootBackpack.controllers;

import com.example.SpringBootBackpack.entitys.Backpack;
import com.example.SpringBootBackpack.entitys.BackpackObject;
import com.example.SpringBootBackpack.entitys.Objectt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class BackpackObjectController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/backpack/object")
    public ResponseEntity<List<BackpackObject>> findAll() {
        String sql = "select * from backpack_object";
        List<BackpackObject> backpackObjects = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(BackpackObject.class));
        System.out.println("List of backpack-object:");
        backpackObjects.forEach(System.out :: println);
        return ResponseEntity.ok(backpackObjects);
    }

    @GetMapping("/backpack/object/{id}")
    public ResponseEntity<BackpackObject> findAllOneById(@PathVariable Long id) {
        try {
            String sql = "select * from backpack_object where mochila_objeto_id = " + id;
            List<BackpackObject> backpackObjects = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(BackpackObject.class));
            BackpackObject backpackObject = backpackObjects.get(0);
            System.out.println("The backpack-object found:\n" +backpackObject);
            return ResponseEntity.ok(backpackObject);
        } catch (Exception exception) {
            System.out.println("The backpack-object was not found.");
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/backpack/object")
    public ResponseEntity<BackpackObject> create(@RequestBody BackpackObject backpackObject) {
        if (backpackObject.getMochila_objeto_id() != null) {
            System.out.println("The backpack-object is not valid.");
            return ResponseEntity.badRequest().build();
        }
        if (ValidateBackpackObjectFkBackpack(backpackObject.getId_mochilita())) {
            System.out.println("The id_mochilita does not exist.");
            return ResponseEntity.badRequest().build();
        }
        if (ValidateBackpackObjectFkObjectt(backpackObject.getId_objeto())) {
            System.out.println("The id_objeto does not exist.");
            return ResponseEntity.badRequest().build();
        }
        int id_mochilita = backpackObject.getId_mochilita();
        int id_objeto = backpackObject.getId_objeto();
        String sql = "insert into backpack_object(id_mochilita, id_objeto) values (" + id_mochilita + ", " + id_objeto + ")";
        jdbcTemplate.execute(sql);
        System.out.println("Registered backpack-object.");
        return ResponseEntity.ok().build();
    }

    private boolean ValidateBackpackObjectFkBackpack(int id) {
        String sql = "select * from backpack";
        List<Backpack> backpacks = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Backpack.class));
        for (Backpack value : backpacks) {
            if (value.getMochila_id() == id) {
                return false;
            }
        }
        return true;
    }

    private boolean ValidateBackpackObjectFkObjectt(int id) {
        String sql = "select * from objectt";
        List<Objectt> objectts = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Objectt.class));
        for (Objectt value : objectts) {
            if (value.getObjeto_id() == id) {
                return false;
            }
        }
        return true;
    }

    @PutMapping("/backpack/object")
    public ResponseEntity<BackpackObject> update(@RequestBody BackpackObject backpackObject) {
        if (backpackObject.getMochila_objeto_id() == null) {
            System.out.println("The mochila_objeto_id must be different of null.");
            return ResponseEntity.badRequest().build();
        }
        if (ValidateBackpackObject(backpackObject)) {
            System.out.println("The backpack-object was not found.");
            return ResponseEntity.badRequest().build();
        }
        int id_mochilita = backpackObject.getId_mochilita();
        int id_objeto = backpackObject.getId_objeto();
        String sql1 = "update backpack_object set id_mochilita = " + id_mochilita + " where mochila_objeto_id = " + backpackObject.getMochila_objeto_id();
        String sql2 = "update backpack_object set id_objeto = " + id_objeto + " where mochila_objeto_id = " + backpackObject.getMochila_objeto_id();
        jdbcTemplate.execute(sql1);
        jdbcTemplate.execute(sql2);
        System.out.println("Updated backpack-object.");
        return ResponseEntity.ok(backpackObject);
    }

    private boolean ValidateBackpackObject(BackpackObject backpackObject) {
        try {
            String sql = "select * from backpack_object where mochila_objeto_id = " + backpackObject.getMochila_objeto_id();
            List<BackpackObject> backpackObjects = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(BackpackObject.class));
            backpackObject = backpackObjects.get(0);
            return false;
        } catch (Exception exception) {
            return true;
        }
    }

    @DeleteMapping("/backpack/object")
    public ResponseEntity<BackpackObject> deleteAll() {
        String sql = "delete from backpack_object";
        jdbcTemplate.execute(sql);
        System.out.println("Deleted registrations.");
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/backpack/object/{id}")
    public ResponseEntity<BackpackObject> delete(@PathVariable Long id) {
        try {
            if (ValidateBackpackObject(id)) {
                throw new Exception();
            }
            String sql = "delete from backpack_object where mochila_objeto_id = " + id;
            jdbcTemplate.execute(sql);
            System.out.println("Deleted registration.");
            return ResponseEntity.ok().build();
        } catch (Exception exception) {
            System.out.println("The backpack-object was not found.");
            return ResponseEntity.badRequest().build();
        }
    }

    private boolean ValidateBackpackObject(Long id) {
        try {
            String sql = "select * from backpack_object where mochila_objeto_id =" + id;
            List<BackpackObject> backpackObjects = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(BackpackObject.class));
            BackpackObject backpackObject = backpackObjects.get(0);
            return false;
        } catch (Exception exception) {
            return true;
        }
    }
}
