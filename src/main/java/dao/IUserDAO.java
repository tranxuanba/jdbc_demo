package dao;

import model.User;

import java.sql.SQLException;
import java.util.List;

public interface IUserDAO {
    void insertUser(User user) throws SQLException, ClassNotFoundException;
    User selectUser(int id) throws SQLException, ClassNotFoundException;
    List<User> selectAllUsers() throws SQLException, ClassNotFoundException;
    boolean deleteUser(int id) throws SQLException, ClassNotFoundException;
    boolean updateUser(User user) throws SQLException, ClassNotFoundException;
}
