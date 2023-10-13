package com.example.demo;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class BD {
    public BD() {
    }

    public Users register(String id, String name, String ally, String status) {

        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/registers";
        String username = "root";
        String password = "";

        try {
            Class.forName(driver);
            Connection connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM register");

            // Sentencia INSERT
            String sql = "INSERT INTO register (id , name, ally, status, creation_date, update_date) VALUES (?, ?, ?, ?, ?, ?)";

            // Preparar la sentencia
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, id);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, ally);
            preparedStatement.setString(4, status);
            preparedStatement.setString(5, String.valueOf(LocalDateTime.now()));
            preparedStatement.setString(6, "");
            // Ejecutar la sentencia
            int files = preparedStatement.executeUpdate();

            if (files > 0) {
                System.out.println("Usuario registrado exitosamente.");
                return new Users(id, name, ally, status);
            } else {
                System.out.println(Errors.error_register);
            }

            preparedStatement.close();
            connection.close();
            statement.close();
            resultSet.close();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return new Users(null,null,null,null);
    }

    public Users Edit(String id, String name, String ally, String status) throws ClassNotFoundException, SQLException {

        String driver2 = "com.mysql.cj.jdbc.Driver";
        String url2 = "jdbc:mysql://localhost:3306/registers";
        String username2 = "root";
        String pass2 = "";

        Class.forName(driver2);
        Connection connection2 = DriverManager.getConnection(url2, username2, pass2);

        Statement statement2 = connection2.createStatement();
        String consulta = "UPDATE register SET status = ?, update_date = ? WHERE id = ?";

        PreparedStatement preparedStatement = connection2.prepareStatement(consulta);
        preparedStatement.setString(1, status);
        preparedStatement.setString(2, String.valueOf(LocalDateTime.now()));
        preparedStatement.setString(3, id);

        int filasActualizadas = preparedStatement.executeUpdate();
        if (filasActualizadas > 0) {
            System.out.println("Usuario actualizado exitosamente");
            return new Users(id, null, null, status);
        } else {
            System.out.println(Errors.error_edit);
        }

        preparedStatement.close();
        connection2.close();
        return new Users(null,null,null,null);
    }

    public List<Users> search_active() throws ClassNotFoundException, SQLException {

        String driver2 = "com.mysql.cj.jdbc.Driver";
        String url2 = "jdbc:mysql://localhost:3306/registers";
        String username2 = "root";
        String pass2 = "";

        Class.forName(driver2);
        Connection connection2 = DriverManager.getConnection(url2, username2, pass2);

        Statement statement2 = connection2.createStatement();

        ResultSet resultSet2 = statement2.executeQuery("SELECT * FROM register");
        List<Users> list = new ArrayList<>();

        while(resultSet2.next()){
            String id = resultSet2.getString("id");
            String name = resultSet2.getString("name");
            String ally = resultSet2.getString("ally");
            String status = resultSet2.getString("status");

            if (status.equalsIgnoreCase("Activo")) {
                Users users = new Users(id, name, ally, status);
                list.add(users);
            }
        }
        return list;
    }

    public List<Users> searc_inactive() throws ClassNotFoundException, SQLException {

        String driver2 = "com.mysql.cj.jdbc.Driver";
        String url2 = "jdbc:mysql://localhost:3306/registers";
        String username2 = "root";
        String pass2 = "";

        Class.forName(driver2);
        Connection connection2 = DriverManager.getConnection(url2, username2, pass2);

        Statement statement2 = connection2.createStatement();

        ResultSet resultSet2 = statement2.executeQuery("SELECT * FROM register");
        List<Users> list = new ArrayList<>();

        while(resultSet2.next()){
            String id = resultSet2.getString("id");
            String name = resultSet2.getString("name");
            String ally = resultSet2.getString("ally");
            String status = resultSet2.getString("status");

            if (status.equalsIgnoreCase("Inactivo")) {
                Users users = new Users(id, name, ally, status);
                list.add(users);
            }
        }
        return list;
    }
    }





