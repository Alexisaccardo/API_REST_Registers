package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

@RestController
public class Controlador {

    @PostMapping("/register_users")
    public Users register_users(@RequestBody Users users) throws SQLException, ClassNotFoundException {

        String id = UUID.randomUUID().toString();
        id = id.replaceAll("-", "");
        id = id.substring(0, 5);
        String name = users.getName();
        String ally = users.getAlly();
        String status = users.getStatus();

        if (name == null || name.equals("") || name.length() < 0 || ally == null || ally.equals("") || ally.length() < 0
                || status == null || status.equals("") || status.length() < 0) {

            return new Users(null, null, null, null);
        } else {
            if (status.equalsIgnoreCase("Activo")) {
                BD bd = new BD();
                users = bd.register(id, name, ally, status);
            } else {
                return new Users(null, null, null, Errors.status_active);

            }
            return users;
        }
    }

    @PostMapping("/edit")
    public Users edit(@RequestBody Users users) throws SQLException, ClassNotFoundException {

        String id = users.getId();
        String name = users.getName();
        String ally = users.getAlly();
        String status = users.getStatus();


        if (id == null || id.equals("") || id.length() < 0 || name == null || name.equals("") || name.length() < 0 || ally == null || ally.equals("") || ally.length() < 0
                || status == null || status.equals("") || status.length() < 0) {

            return new Users(null, null, null, null);
        } else {

            if (status.equalsIgnoreCase("Inactivo")) {
                BD bd = new BD();
                users = bd.Edit(id, name, ally, status);
            } else {
                return new Users(null, null, null, Errors.status_inactive);
            }
            return users;
        }

    }

    @GetMapping("/search_active")

    public List<Users> search_active() throws SQLException, ClassNotFoundException {

        BD bd = new BD();
        List<Users> list = bd.search_active();

        return list;
    }

    @GetMapping("/search_inactive")

    public List<Users> search_inactive() throws SQLException, ClassNotFoundException {

        BD bd = new BD();
        List<Users> list = bd.searc_inactive();

        return list;
    }
}