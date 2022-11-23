package pl.coderslab.dao;

import org.mindrot.jbcrypt.BCrypt;
import pl.coderslab.exception.NotFoundException;
import pl.coderslab.model.Admin;
import pl.coderslab.utils.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminDao {
    private static final String CREATE_ADMIN_QUERY = "INSERT INTO admins(first_name, last_name, email, password, " +
            "superadmin) VALUES (?, ?, ?, ?, ?)";
    private static final String DELETE_ADMIN_QUERY = "DELETE FROM admins where id = ?";
    private static final String FIND_ALL_ADMINS_QUERY = "SELECT * FROM admins";
    private static final String READ_ADMIN_QUERY = "SELECT * from admins where id = ?";
    private static final String UPDATE_ADMIN_QUERY = "UPDATE admins SET first_name=?,last_name=?,email=? WHERE id=?";

    private static final String UPDATE_ADMIN_PASSWORD = "update admins set password=? where id=?";

    private static final String LOGIN_ADMIN_QUERY = "select * from admins where email=?";

    public Admin create(Admin admin) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement insertStm = connection.prepareStatement(CREATE_ADMIN_QUERY,
                     PreparedStatement.RETURN_GENERATED_KEYS)) {
            insertStm.setString(1, admin.getFirst_name());
            insertStm.setString(2, admin.getLast_name());
            insertStm.setString(3, admin.getEmail());
            insertStm.setString(4, BCrypt.hashpw(admin.getPassword(), BCrypt.gensalt()));
            insertStm.setInt(5, 0);
            int result = insertStm.executeUpdate();

            if (result != 1) {
                throw new RuntimeException("Execute update returned " + result);
            }

            try (ResultSet generatedKeys = insertStm.getGeneratedKeys()) {
                if (generatedKeys.first()) {
                    admin.setId(generatedKeys.getInt(1));
                    return admin;
                } else {
                    throw new RuntimeException("Generated key was not found");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Admin read(int adminId) {
        Admin admin = new Admin();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(READ_ADMIN_QUERY);
        ) {
            statement.setInt(1, adminId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    admin.setId(resultSet.getInt("id"));
                    admin.setFirst_name(resultSet.getString("first_name"));
                    admin.setLast_name(resultSet.getString("last_name"));
                    admin.setEmail(resultSet.getString("email"));
                    admin.setPassword(resultSet.getString("password"));
                    admin.setSuperAdmin(resultSet.getInt("superadmin"));
                    admin.setEnable(resultSet.getInt("enable"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return admin;
    }

    public void update(Admin admin) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_ADMIN_QUERY)) {
            statement.setString(1, admin.getFirst_name());
            statement.setString(2, admin.getLast_name());
            statement.setString(3, admin.getEmail());
            statement.setInt(4, admin.getId());
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updatePassowrd(Admin admin) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_ADMIN_PASSWORD)) {
            statement.setString(1, BCrypt.hashpw(admin.getPassword(), BCrypt.gensalt()));
            statement.setInt(2, admin.getId());
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete(int adminId) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_ADMIN_QUERY)) {
            statement.setInt(1, adminId);
            statement.executeUpdate();

            boolean deleted = statement.execute();
            if (!deleted) {
                throw new NotFoundException("Admin not found");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Admin> findAll() {
        List<Admin> adminList = new ArrayList<>();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL_ADMINS_QUERY);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Admin adminToAdd = new Admin();
                adminToAdd.setId(resultSet.getInt("id"));
                adminToAdd.setFirst_name(resultSet.getString("first_name"));
                adminToAdd.setLast_name(resultSet.getString("last_name"));
                adminToAdd.setEmail(resultSet.getString("email"));
                adminToAdd.setPassword(resultSet.getString("password"));
                adminToAdd.setSuperAdmin(resultSet.getInt("superadmin"));
                adminToAdd.setEnable(resultSet.getInt("enable"));
                adminList.add(adminToAdd);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return adminList;
    }

    public Admin isAdminExists(Admin admin) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(LOGIN_ADMIN_QUERY);
        ) {
            statement.setString(1, admin.getEmail());
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String hashPass = resultSet.getString("password");
                    if (BCrypt.checkpw(admin.getPassword(), hashPass) && resultSet.getInt("enable") == 1) {
                        admin = read(resultSet.getInt("id"));
                        return admin;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

