package code.data.repositories;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

import code.core.Repository;

import code.data.entities.Room;
import code.data.entities.Pavilion;
import code.data.enums.RoomType;
import code.data.enums.RoomState;

import java.sql.Statement;

public class RoomRepositoryBD implements Repository<Room> {

    private final Repository<Pavilion> pavilionRepository;

    public RoomRepositoryBD(Repository<Pavilion> pavilionRepository) {
        this.pavilionRepository = pavilionRepository;
    }

    @Override
    public boolean insert(Room object) {
        int checker = 0;
        Connection conn = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:8889/exam2", "root", "root");
            Statement statement = conn.createStatement();
            checker = statement.executeUpdate(String.format(
                    "INSERT INTO `room` (`number`, `floorNumber`,`type`,`state`,`pavilion`) VALUES ('%s', '%d','%s', '%s','%d')",
                    object.getNumber(), object.getFloorNumber(), object.getRoomType().toString(),
                    object.getRoomState().toString(), object.getPavilion().getId()));
        } catch (ClassNotFoundException var5) {
            System.out.println("[Driver loading failed!]");
        } catch (SQLException var6) {
            System.out.println("[Connection failed!]");
        }

        return checker == 0;
    }

    @Override
    public Room getById(int id) {
        Connection connection;
        Room room = new Room();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:8889/exam2", "root", "root");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(String.format("SELECT * FROM `room` WHERE `id`= '%d';", id));
            while (resultSet.next()) {
                room.setId(resultSet.getInt("id"));
                room.setNumber(resultSet.getString("number"));
                room.setFloorNumber(resultSet.getInt("floorNumber"));
                room.setRoomType(RoomType.getValue(resultSet.getString("type")));
                room.setRoomState(RoomState.getValue(resultSet.getString("state")));
                room.setPavilion(pavilionRepository.getById(resultSet.getInt("pavilion")));
            }
            return room;
        } catch (ClassNotFoundException var5) {
            System.out.println("[Driver loading failed!]");
        } catch (SQLException var6) {
            System.out.println("[Connection failed!]");
        }
        return null;
    }

    @Override
    public List<Room> selectAll() {
        List<Room> roomList = new ArrayList<>();
        Connection connection;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:8889/exam2", "root", "root");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM `room`");
            while (resultSet.next()) {
                Room room = new Room();
                room.setId(resultSet.getInt("id"));
                room.setNumber(resultSet.getString("number"));
                room.setFloorNumber(resultSet.getInt("floorNumber"));
                room.setRoomType(RoomType.getValue(resultSet.getString("type")));
                room.setRoomState(RoomState.getValue(resultSet.getString("state")));
                Pavilion pavilion = pavilionRepository.getById(resultSet.getInt("pavilion"));
                room.setPavilion(pavilion);
                roomList.add(room);
            }
            System.out.println("[Connection established!]");
        } catch (Exception e) {
            System.out.println("[Driver loading failed!]");
        }
        return roomList;
    }

    @Override
    public void update1(Room room) {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:8889/exam2", "root", "root");
            Statement statement = connection.createStatement();
            RoomType type = room.getRoomType();

            statement.executeUpdate(String.format("UPDATE `room` SET `floorNumber`='%s' ,`type`='%s' WHERE `id`='%d';",
                            room.getFloorNumber(), type.toString(), room.getId()));

        } catch (ClassNotFoundException e) {
            System.out.println("[Driver loading failed!]");
            e.fillInStackTrace();
        } catch (SQLException e) {
            System.out.println("[Connection failed!]");
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
    public void update(Room room) {
        if (room != null) {

            Connection connection = null;
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection("jdbc:mysql://localhost:8889/exam2", "root", "root");
                Statement statement = connection.createStatement();
                RoomState state = room.getRoomState();

                statement.executeUpdate(String.format("UPDATE `room` SET `state`='%s'  WHERE `id`='%d';",
                        state.toString(), room.getId()));

            } catch (ClassNotFoundException e) {
                System.out.println("[Driver loading failed!]");
                e.fillInStackTrace();
            } catch (SQLException e) {
                System.out.println("[Connection failed!]");
                e.fillInStackTrace();
            } finally {
                try {
                    if (connection != null)
                        connection.close();
                } catch (SQLException e) {
                    e.fillInStackTrace();
                }
            }
        } else {
            System.out.println("[Edits non operated!]");
        }
    }

    @Override
    public void affect(Room room) {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:8889/exam2", "root", "root");
            Statement statement = connection.createStatement();

            statement.executeUpdate(String.format("UPDATE `room` SET `pavilion`='%d' WHERE `id`='%d';", room.getPavilion().getId(), room.getId()));

        } catch (ClassNotFoundException e) {
            System.out.println("[Driver loading failed!]");
            e.fillInStackTrace();
        } catch (SQLException e) {
            System.out.println("[Connection failed!]");
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
    public List<Room> selectBy(int x) {
        List<Room> roomList = new ArrayList<>();
        Connection connection;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:8889/exam2", "root", "root");
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(String.format("SELECT * FROM `room` WHERE `pavilion`='%d';", x));
            while (rs.next()) {
                Room room = new Room();
                room.setId(rs.getInt("id"));
                room.setNumber(rs.getString("number"));
                room.setFloorNumber(rs.getInt("floorNumber"));
                room.setRoomType(RoomType.getValue(rs.getString("type")));
                room.setRoomState(RoomState.getValue(rs.getString("state")));
                Pavilion pavilion = pavilionRepository.getById(rs.getInt("pavilion"));
                room.setPavilion(pavilion);
                roomList.add(room);
            }
        } catch (Exception e) {
            System.out.println("[Driver loading failed!]");
        }
        return roomList;

    }
}