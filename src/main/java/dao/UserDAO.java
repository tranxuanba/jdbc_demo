package dao;

import model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO implements IUserDAO{
    public static final String INSERT_INTO_USERS = "insert into users (name, email, country) values (?, ?, ?);";
    public static final String SELECT_ID = "select id,name,email,country from users where id =?";
    public static final String SELECT_FROM_USERS = "select * from users";
    public static final String DELETE_FROM_USERS_WHERE_ID = "delete from users where id = ?";
    public static final String UPDATE_USERS_SET_NAME_EMAIL_COUNTRY_WHERE_ID = "update users set name=?, email=?, country=? where id=?";
    private String jdbcURL = "jdbc:mysql://127.0.0.1:3306/demo3";
    private String jdbcUsername = "root";
    private String jdbcPassword = "Thoitran2107";

    public UserDAO() {
    }
    protected Connection getConnection() throws ClassNotFoundException, SQLException {
        Connection connection = null;
        Class.forName("com.mysql.jdbc.Driver");
        connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        return connection;
    }

    @Override
    public void insertUser(User user) throws SQLException, ClassNotFoundException {
        System.out.println(INSERT_INTO_USERS);
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_INTO_USERS);
        preparedStatement.setString(1, user.getName());
        preparedStatement.setString(2, user.getEmail());
        preparedStatement.setString(3, user.getCountry());
        System.out.println(preparedStatement);
        preparedStatement.executeUpdate();
    }

    @Override
    public User selectUser(int id) throws SQLException, ClassNotFoundException {
        User user = null;
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ID);
        preparedStatement.setInt(1, id);
        System.out.println(preparedStatement);
        ResultSet rs = preparedStatement.executeQuery();
        while (rs.next()){
            String name = rs.getString("name");
            String email = rs.getString("email");
            String country = rs.getString("country");
            user = new User(id, name, email, country);
        }
        return user;
    }

//    public static void main(String[] args) throws SQLException, ClassNotFoundException {
//        UserDAO userDAO = new UserDAO();
//        System.out.println(userDAO.selectAllUsers().toString());
//    }

    @Override
    public List<User> selectAllUsers() throws SQLException, ClassNotFoundException {
        List<User> userList = new ArrayList<>();
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_FROM_USERS);
            System.out.println(preparedStatement);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                String country = resultSet.getString("country");
                userList.add(new User(id, name, email, country));
            }
        return userList;
    }


    @Override
    public boolean deleteUser(int id) throws SQLException, ClassNotFoundException {
        boolean rowDeleted;
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(DELETE_FROM_USERS_WHERE_ID);
        preparedStatement.setInt(1, id);
        System.out.println(preparedStatement);
        rowDeleted = preparedStatement.executeUpdate() > 0;
        return rowDeleted;
    }

    @Override
    public boolean updateUser(User user) throws SQLException, ClassNotFoundException {
        boolean rowUpdated;
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USERS_SET_NAME_EMAIL_COUNTRY_WHERE_ID);
        preparedStatement.setString(1,user.getName());
        preparedStatement.setString(2, user.getEmail());
        preparedStatement.setString(3, user.getCountry());
        preparedStatement.setInt(4, user.getId());
        rowUpdated = preparedStatement.executeUpdate()>0;
        return rowUpdated;
    }
}
