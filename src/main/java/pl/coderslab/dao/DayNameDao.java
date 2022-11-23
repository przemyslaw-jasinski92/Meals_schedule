package pl.coderslab.dao;

import pl.coderslab.model.DayName;
import pl.coderslab.utils.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DayNameDao {
    private static final String FIND_ALL_DAY_NAMES_QUERY = "SELECT * FROM day_name";
    private static final String FIND_DAY_ID_BY_NAME = "select id from day_name where name=?";

    public List<DayName> findAll() {
        List<DayName> dayNamesList = new ArrayList<>();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL_DAY_NAMES_QUERY);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                DayName dayNameToAdd = new DayName();
                dayNameToAdd.setName(resultSet.getString("name"));
                dayNameToAdd.setId(resultSet.getInt("id"));
                dayNameToAdd.setDisplayOrder(resultSet.getInt("display_order"));
                dayNamesList.add(dayNameToAdd);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dayNamesList;
    }

    public int findDayId(String dayName){
        int dayId = 0;
        try(Connection connection = DbUtil.getConnection();
            PreparedStatement statement = connection.prepareStatement(FIND_DAY_ID_BY_NAME)){
            statement.setString(1, dayName);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            dayId = resultSet.getInt("id");

        }catch (SQLException e){
            e.printStackTrace();
        }
        return dayId;
    }
}


