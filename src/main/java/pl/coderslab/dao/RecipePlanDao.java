package pl.coderslab.dao;

import pl.coderslab.exception.NotFoundException;
import pl.coderslab.model.Admin;
import pl.coderslab.model.RecipePlan;
import pl.coderslab.utils.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class RecipePlanDao {
    private static final String QUERY_LAST_PLAN = "SELECT day_name.name as day_name, meal_name,  recipe.name as recipe_name, recipe.description as recipe_description, recipe_id\n" +
            "FROM `recipe_plan`\n" +
            "         JOIN day_name on day_name.id=day_name_id\n" +
            "         JOIN recipe on recipe.id=recipe_id WHERE\n" +
            "        recipe_plan.plan_id =  (SELECT MAX(id) from plan WHERE admin_id = ?)\n" +
            "ORDER by day_name.display_order, recipe_plan.display_order;";

    private static final String QUERY_LAST_PLAN_NAME = "select name from plan where admin_id = ?  order by id desc limit 1";

    private static final String QUERY_ADD_RECIPE_TO_PLAN = "insert into recipe_plan (recipe_id, meal_name, display_order, day_name_id, plan_id) VALUES (?, ?, ?, ?, ?)";

    private static final String QUERY_FIND_ALL_ADMIN_PLANS = "SELECT day_name.name as day_name, meal_name, recipe.name as recipe_name, recipe.description as recipe_description, recipe_id, recipe_plan.id\n" +
            "FROM `recipe_plan`\n" +
            "         JOIN day_name on day_name.id=day_name_id\n" +
            "         JOIN recipe on recipe.id=recipe_id WHERE plan_id = ?\n" +
            "ORDER by day_name.display_order, recipe_plan.display_order;";

    private static final String QUERY_DELETE_RECIPE_FROM_PLAN = "delete from recipe_plan where id=?";

    public Map<String, List<RecipePlan>> getLastPlan(Admin admin) {

        Map<String, List<RecipePlan>> lastPlan = new LinkedHashMap<>();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(QUERY_LAST_PLAN)) {
            statement.setInt(1, admin.getId());
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    RecipePlan recipePlan = new RecipePlan();
                    String dayName = resultSet.getString("day_name");
                    if (lastPlan.containsKey(dayName)) {
                        List<RecipePlan> addItem = lastPlan.get(dayName);
                        recipePlan.setMealName(resultSet.getString("meal_name"));
                        recipePlan.setRecipeName(resultSet.getString("recipe_name"));
                        recipePlan.setRecipeDescription(resultSet.getString("recipe_description"));
                        recipePlan.setRecipeId((resultSet.getInt("recipe_id")));
                        addItem.add(recipePlan);
                        lastPlan.put(dayName, addItem);
                    } else {
                        List<RecipePlan> addItem = new ArrayList<>();
                        recipePlan.setMealName(resultSet.getString("meal_name"));
                        recipePlan.setRecipeName(resultSet.getString("recipe_name"));
                        recipePlan.setRecipeDescription(resultSet.getString("recipe_description"));
                        recipePlan.setRecipeId((resultSet.getInt("recipe_id")));
                        addItem.add(recipePlan);
                        lastPlan.put(dayName, addItem);
                    }
                }
                return lastPlan;
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Map<String, List<RecipePlan>> getAllPlans(int planId) {

        Map<String, List<RecipePlan>> allPlans = new HashMap<>();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(QUERY_FIND_ALL_ADMIN_PLANS)) {
            statement.setInt(1, planId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    RecipePlan recipePlan = new RecipePlan();
                    String dayName = resultSet.getString("day_name");
                    if (allPlans.containsKey(dayName)) {
                        List<RecipePlan> addItem = allPlans.get(dayName);
                        recipePlan.setMealName(resultSet.getString("meal_name"));
                        recipePlan.setRecipeName(resultSet.getString("recipe_name"));
                        recipePlan.setRecipeDescription(resultSet.getString("recipe_description"));
                        recipePlan.setRecipeId(resultSet.getInt("recipe_id"));
                        recipePlan.setId(resultSet.getInt("id"));
                        addItem.add(recipePlan);
                        allPlans.put(dayName, addItem);
                    } else {
                        List<RecipePlan> addItem = new ArrayList<>();
                        recipePlan.setMealName(resultSet.getString("meal_name"));
                        recipePlan.setRecipeName(resultSet.getString("recipe_name"));
                        recipePlan.setRecipeDescription(resultSet.getString("recipe_description"));
                        recipePlan.setRecipeId((resultSet.getInt("recipe_id")));
                        recipePlan.setId(resultSet.getInt("id"));
                        addItem.add(recipePlan);
                        allPlans.put(dayName, addItem);
                    }
                }
                return allPlans;
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getLastPlanName(Admin admin){
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(QUERY_LAST_PLAN_NAME)) {
            statement.setInt(1, admin.getId());
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                return resultSet.getString("name");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void addRecipeToPlan(int recipeId, String mealName, int displayOrder, int dayId, int planId){
        try(Connection connection = DbUtil.getConnection();
            PreparedStatement statement = connection.prepareStatement(QUERY_ADD_RECIPE_TO_PLAN)){
            statement.setInt(1, recipeId);
            statement.setString(2, mealName);
            statement.setInt(3, displayOrder);
            statement.setInt(4, dayId);
            statement.setInt(5, planId);
            statement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void delete(int recipePlanId) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(QUERY_DELETE_RECIPE_FROM_PLAN)) {
            statement.setInt(1, recipePlanId);
            statement.executeUpdate();

            boolean deleted = statement.execute();
            if (!deleted) {
                throw new NotFoundException("Recipe in plan not found");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
