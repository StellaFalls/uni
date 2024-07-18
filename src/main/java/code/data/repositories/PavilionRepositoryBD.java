package code.data.repositories;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.List;

import code.core.Repository;

import code.data.entities.Pavilion;

public class PavilionRepositoryBD implements Repository<Pavilion> {

    @Override
    public boolean insert(Pavilion object) {
        int checker = 0;
        Connection connection;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:8889/exam2", "root", "root");
            Statement statement = connection.createStatement();
            checker = statement.executeUpdate(String.format(
                    "INSERT INTO `pavilion` (`number`, `floorAmount`) VALUES ('%s', '%d')",
                    object.getNumber(), object.getFloorAmount()));
        } catch (ClassNotFoundException var5) {
            System.out.println("[Driver loading failed!]");
        } catch (SQLException var6) {
            System.out.println("[Connection error!]");
        }

        return checker == 0;
    }

    @Override
    public Pavilion getById(int id) {
        Connection connection;
        Pavilion pavilion = new Pavilion();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:8889/exam2", "root", "root");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(String.format("SELECT * FROM `pavilion` WHERE `id`= '%d';", id));
            while (resultSet.next()) {
                pavilion.setId(resultSet.getInt("id"));
                pavilion.setNumber(resultSet.getString("number"));
                pavilion.setFloorAmount(resultSet.getInt("floorAmount"));
            }
            return pavilion;
        } catch (ClassNotFoundException var5) {
            System.out.println("[Driver loading failed!]");
        } catch (SQLException var6) {
            System.out.println("[Connection error!]");
        }
        return null;
    }

    @Override
    public List<Pavilion> selectAll() {
        List<Pavilion> pavilionList = new ArrayList<>();
        Connection connection;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:8889/exam2", "root", "root");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM `pavilion`");
            while (resultSet.next()) {
                Pavilion pavilion = new Pavilion();
                pavilion.setId(resultSet.getInt("id"));
                pavilion.setNumber(resultSet.getString("number"));
                pavilion.setFloorAmount(resultSet.getInt("floorAmount"));
                pavilionList.add(pavilion);
            }
            System.out.println("[Connection established!]");
        } catch (Exception e) {
            System.out.println("[Driver loading failed!]");
        }
        return pavilionList;
    }

    @Override
    public void update(Pavilion objet) {
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public void update1(Pavilion pavilion) {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:8889/exam2", "root", "root");
            Statement statement = connection.createStatement();
            statement.executeUpdate(String.format("UPDATE `pavilion` SET `floorAmount`='%s' WHERE `id`='%d';",
                    pavilion.getFloorAmount(), pavilion.getId()));
        } catch (ClassNotFoundException e) {
            System.out.println("[Driver loading failed!]");
            e.fillInStackTrace();
        } catch (SQLException e) {
            System.out.println("[Connection error!]");
            e.fillInStackTrace();
        } finally {
            try {
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                e.fillInStackTrace();
            }
        }
    }

    @Override
    public void affect(Pavilion objet) {
        throw new UnsupportedOperationException("Unimplemented method 'affect'");
    }

    @Override
    public List<Pavilion> selectBy(int x) {
        throw new UnsupportedOperationException("Unimplemented method 'selectBy'");
    }

}