package com.example.SpringBootBackpack.controllers;

import com.example.SpringBootBackpack.entitys.Backpack;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.text.MessageFormat;
import java.util.List;

public class BackpackController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/backpack")
    public ResponseEntity<List<Backpack>> findAll() {
        String sql = "select * from backpack";
        List<Backpack> backpacks = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Backpack.class));
        System.out.println("List of backpacks:");
        backpacks.forEach(System.out :: println);
        return ResponseEntity.ok(backpacks);
    }

    @GetMapping("/backpack/{id}")
    public ResponseEntity<Backpack> finfAllOneById(@PathVariable Long id) {
        try {
            String sql = "select * from backpack where mochila_id = " + id;
            List<Backpack> backpacks = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Backpack.class));
            Backpack backpack = backpacks.get(0);
            System.out.println("The backpack found:\n" + backpack);
            return ResponseEntity.ok(backpack);
        } catch (Exception exception) {
            System.out.println("The backpack was not found.");
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/backpack")
    public ResponseEntity<Backpack> create(@RequestBody Backpack backpack) {
        if (backpack.getMochila_id() != null) {
            System.out.println("The backpack is not valid.");
            return ResponseEntity.badRequest().build();
        }
        String nombre = backpack.getNombre();
        int capacidad = backpack.getCapacidad();
        String color = backpack.getColor();
        String sql = MessageFormat.format("insert into backpack(nombre, capacidad, color) values ('{0}', {1}, '{2}')", nombre, capacidad, color);
        jdbcTemplate.execute(sql);
        System.out.println("Registered backpack.");
        return ResponseEntity.ok().build();
    }

    @PutMapping("/backpack")
    public ResponseEntity<Backpack> update(@RequestBody Backpack backpack) {
        if (backpack.getMochila_id() == null) {
            System.out.println("The mochila_id must be different of null.");
            return ResponseEntity.badRequest().build();
        }
        if (ValidateBackpack(backpack)) {
            System.out.println("The backpack was not found.");
            return ResponseEntity.badRequest().build();
        }
        String nombre = backpack.getNombre();
        int capacidad = backpack.getCapacidad();
        String color = backpack.getColor();
        String sql1 = MessageFormat.format("update backpack set nombre = '{0}' where mochila_id = {1}", nombre, backpack.getMochila_id());
        String sql2 = MessageFormat.format("update backpack set capacidad = {0} where mochila_id = {1}", capacidad, backpack.getMochila_id());
        String sql3 = MessageFormat.format("update backpack set color = '{0}' where mochila_id = {1}", color, backpack.getMochila_id());
        jdbcTemplate.execute(sql1);
        jdbcTemplate.execute(sql2);
        jdbcTemplate.execute(sql3);
        System.out.println("Updated backpack.");
        return ResponseEntity.ok(backpack);
    }

    private boolean ValidateBackpack(Backpack backpack) {
        try {
            String sql = "select * from backpack where mochila_id = " + backpack.getMochila_id();
            List<Backpack> backpacks = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Backpack.class));
            backpack = backpacks.get(0);
            return false;
        } catch (Exception exception) {
            return true;
        }
    }

    @DeleteMapping("/backpack")
    public ResponseEntity<Backpack> deleteAll() {
        String sql = "delete from backpack";
        jdbcTemplate.execute(sql);
        System.out.println("Deleted registrations.");
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/backpack/{id}")
    public ResponseEntity<Backpack> delete(@PathVariable Long id) {
        try {
            if (ValidateBackpack(id)) {
                throw new Exception();
            }
            String sql = "delete from backpack where mochila_id = " + id;
            jdbcTemplate.execute(sql);
            System.out.println("Deleted registration.");
            return ResponseEntity.ok().build();
        } catch (Exception exception) {
            System.out.println("The backpack was not found.");
            return ResponseEntity.badRequest().build();
        }
    }

    private boolean ValidateBackpack(Long id) {
        try {
            String sql = "select * from backpack where mochila_id =" + id;
            List<Backpack> backpacks = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Backpack.class));
            Backpack backpack = backpacks.get(0);
            return false;
        } catch (Exception exception) {
            return true;
        }
    }
}
