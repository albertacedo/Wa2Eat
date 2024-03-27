package jdbc;

import logic.BoughtIngredient;
import logic.Ingredient;
import logic.Recipe;
import logic.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class QueryFunctions {

    public static int registerUser(User user) {
        String insertQuery = "INSERT INTO USERS (USERNAME, PASSWORD, EMAIL) VALUES(?, ?, ?)";

        int result = 0;

        try {
            //Class.forName("org.postgresql.Driver");
            DriverManager.registerDriver(new org.postgresql.Driver());
            Connection connection = DriverManager
                    .getConnection("jdbc:postgresql://localhost:5432/Wa2Eat", "postgres", "password");

            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getEmail());

            result = preparedStatement.executeUpdate();

            preparedStatement.close();
            connection.close();

        } catch (SQLException e) {
            // process sql exception
            printSQLException(e);
        }

        return result;
    }

    public static boolean checkUser(User user) {
        String insertQuery = "SELECT U.USERNAME, U.PASSWORD " +
                "FROM USERS AS U " +
                "WHERE U.USERNAME = ? AND U.PASSWORD = ?";

        int count = 0;

        try {
            //Class.forName("org.postgresql.Driver");
            DriverManager.registerDriver(new org.postgresql.Driver());
            Connection connection = DriverManager
                    .getConnection("jdbc:postgresql://localhost:5432/Wa2Eat", "postgres", "password");

            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());

            ResultSet result = preparedStatement.executeQuery();

            while (result.next()) {
                count++;
            }

            result.close();
            preparedStatement.close();
            connection.close();

        } catch (SQLException e) {
            // process sql exception
            printSQLException(e);
        }

        return count == 1;
    }

    public static Ingredient getIngredientData(String name) {
        String ingredientByNameQuery = "SELECT * " +
                "FROM INGREDIENT AS I " +
                "WHERE I.NAME = ?";

        Ingredient ingredient = null;

        try {
            //Class.forName("org.postgresql.Driver");
            DriverManager.registerDriver(new org.postgresql.Driver());
            Connection connection = DriverManager
                    .getConnection("jdbc:postgresql://localhost:5432/Wa2Eat", "postgres", "password");

            PreparedStatement preparedStatement = connection.prepareStatement(ingredientByNameQuery);
            preparedStatement.setString(1, name);

            ResultSet result = preparedStatement.executeQuery();

            while (result.next()) {
                int calories = result.getInt("CALORIES");
                int proteins = result.getInt("PROTEINS");
                int carbohydrates = result.getInt("CARBOHYDRATES");
                boolean isSweet = result.getBoolean("IS_SWEET");
                boolean animalOrigin = result.getBoolean("ANIMAL_ORIGIN");

                ingredient = new Ingredient(name, calories, proteins, carbohydrates, isSweet, animalOrigin);
            }

            result.close();
            preparedStatement.close();
            connection.close();

        } catch (SQLException e) {
            // process sql exception
            printSQLException(e);
        }

        return ingredient;
    }

    public static List<Recipe> getRecipes() {
        String recipesQuery = "SELECT * " +
                "FROM RECIPE;";

        List<Recipe> recipes = new ArrayList<>();

        try {
            //Class.forName("org.postgresql.Driver");
            DriverManager.registerDriver(new org.postgresql.Driver());
            Connection connection = DriverManager
                    .getConnection("jdbc:postgresql://localhost:5432/Wa2Eat", "postgres", "password");

            PreparedStatement preparedStatement = connection.prepareStatement(recipesQuery);

            ResultSet result = preparedStatement.executeQuery();

            while (result.next()) {
                String id = result.getString("ID");
                String name = result.getString("NAME");
                String season = result.getString("SEASON");
                String origin = result.getString("ORIGIN");
                boolean isVegan = result.getBoolean("IS_VEGAN");
                String link = result.getString("REF_LINK");

                Recipe recipe = new Recipe(id, name, season, origin, isVegan,link);
                recipes.add(recipe);
            }

            result.close();
            preparedStatement.close();
            connection.close();

        } catch (SQLException e) {
            // process sql exception
            printSQLException(e);
        }

        return recipes;
    }

    public static List<Recipe> getRecipes(String iname) {
        String recipesByNameQuery = "SELECT * " +
                "FROM RECIPE AS R " +
                "WHERE R.NAME ILIKE ? OR R.NAME ILIKE ?";

        List<Recipe> recipes = new ArrayList<>();

        try {
            //Class.forName("org.postgresql.Driver");
            DriverManager.registerDriver(new org.postgresql.Driver());
            Connection connection = DriverManager
                    .getConnection("jdbc:postgresql://localhost:5432/Wa2Eat", "postgres", "password");

            PreparedStatement preparedStatement = connection.prepareStatement(recipesByNameQuery);
            preparedStatement.setString(1, iname);
            preparedStatement.setString(2, "%" + iname + "%");

            ResultSet result = preparedStatement.executeQuery();

            while (result.next()) {
                String id = result.getString("ID");
                String name = result.getString("NAME");
                String season = result.getString("SEASON");
                String origin = result.getString("ORIGIN");
                boolean isVegan = result.getBoolean("IS_VEGAN");
                String link = result.getString("REF_LINK");

                Recipe recipe = new Recipe(id, name, season, origin, isVegan,link);
                recipes.add(recipe);
            }

            result.close();
            preparedStatement.close();
            connection.close();

        } catch (SQLException e) {
            // process sql exception
            printSQLException(e);
        }

        return recipes;
    }

    public static List<Recipe> recipesWithIngredient(String iname){

        String recipesAvailable = "SELECT DISTINCT RE.* " +
                "FROM RECIPES_AVAILABLE AS RA, RECIPE AS RE " +
                "WHERE RA.ID = RE.ID AND RA.NAME ILIKE ?";

        List<Recipe> recipes = new ArrayList<>();

        try {

            //Class.forName("org.postgresql.Driver");
            DriverManager.registerDriver(new org.postgresql.Driver());
            Connection connection = DriverManager
                    .getConnection("jdbc:postgresql://localhost:5432/Wa2Eat", "postgres", "password");

            PreparedStatement preparedStatement = connection.prepareStatement(recipesAvailable);
            preparedStatement.setString(1, iname);

            ResultSet result = preparedStatement.executeQuery();

            while (result.next()) {
                String id = result.getString("ID");
                String name= result.getString("NAME");
                String season = result.getString("SEASON");
                String origin = result.getString("ORIGIN");
                boolean isVegan = result.getBoolean("IS_VEGAN");
                String link = result.getString("REF_LINK");

                Recipe recipe = new Recipe(id, name, season, origin, isVegan,link);
                recipes.add(recipe);
            }




            result.close();

            preparedStatement.close();

            connection.close();

        }catch (SQLException e) {

            printSQLException(e);

        }
        return recipes;
    }

    public static List<Ingredient> getRecipeIngredients(String name) {
        String getId = "SELECT R.ID " +
                "FROM RECIPE AS R " +
                "WHERE R.NAME ILIKE ?";

        String getIngredients = "SELECT * " +
                "FROM RECIPES_AVAILABLE AS RA INNER JOIN INGREDIENT AS I ON RA.NAME = I.NAME " +
                "WHERE RA.ID ILIKE ?";

        List<Ingredient> ingredients = new ArrayList<>();

        try {
            //Class.forName("org.postgresql.Driver");
            DriverManager.registerDriver(new org.postgresql.Driver());
            Connection connection = DriverManager
                    .getConnection("jdbc:postgresql://localhost:5432/Wa2Eat", "postgres", "password");

            PreparedStatement preparedStatement = connection.prepareStatement(getId);
            preparedStatement.setString(1, name);

            ResultSet result = preparedStatement.executeQuery();

            String id = "No id";

            while (result.next()) {
                id = result.getString("ID");
            }

            PreparedStatement preparedStatement2 = connection.prepareStatement(getIngredients);
            preparedStatement2.setString(1, id);

            ResultSet result2 = preparedStatement2.executeQuery();

            while (result2.next()) {
                String ingredientName = result2.getString("NAME");
                int calories = result2.getInt("CALORIES");
                int proteins = result2.getInt("PROTEINS");
                int carbohydrates = result2.getInt("CARBOHYDRATES");
                boolean isSweet = result2.getBoolean("IS_SWEET");
                boolean animalOrigin = result2.getBoolean("ANIMAL_ORIGIN");
                int amount = result2.getInt("AMOUNT");

                Ingredient ingredient = new Ingredient(ingredientName, calories, proteins, carbohydrates, isSweet, animalOrigin);
                ingredient.setAmount(amount);

                ingredients.add(ingredient);
            }

            result.close();
            result2.close();
            preparedStatement.close();
            preparedStatement2.close();
            connection.close();

        } catch (SQLException e) {
            // process sql exception
            printSQLException(e);
        }

        return ingredients;
    }

    public static List<BoughtIngredient> getBoughtIngredients(String user) {
        String BoughtIngredientsQuery = "SELECT * " +
                "FROM BOUGHT_INGREDIENTS  " +
                "WHERE USERNAME = ?";

        List<BoughtIngredient> BI = new ArrayList<>();

        try {
            //Class.forName("org.postgresql.Driver");
            DriverManager.registerDriver(new org.postgresql.Driver());
            Connection connection = DriverManager
                    .getConnection("jdbc:postgresql://localhost:5432/Wa2Eat", "postgres", "password");

            PreparedStatement preparedStatement = connection.prepareStatement(BoughtIngredientsQuery);

            preparedStatement.setString(1, user);
            ResultSet result = preparedStatement.executeQuery();




            while (result.next()) {
                String expiration_date = result.getString("EXPIRATION_DATE");
                String name = result.getString("NAME");


                BoughtIngredient ingredient = new BoughtIngredient(expiration_date, name);
                BI.add(ingredient);
            }

            result.close();
            preparedStatement.close();
            connection.close();

        } catch (SQLException e) {
            // process sql exception
            printSQLException(e);
        }

        return BI;
    }



    public static int registerBI(String user, Date expirationDate, String name) {
        String addIngredientsQuery = "INSERT INTO BOUGHT_INGREDIENTS (USERNAME,EXPIRATION_DATE,NAME) VALUES(?, ?, ?)";

        int result = 0;

        try {
            //Class.forName("org.postgresql.Driver");
            DriverManager.registerDriver(new org.postgresql.Driver());
            Connection connection = DriverManager
                    .getConnection("jdbc:postgresql://localhost:5432/Wa2Eat", "postgres", "password");

            PreparedStatement preparedStatement = connection.prepareStatement(addIngredientsQuery);
            preparedStatement.setString(1, user);
            preparedStatement.setDate(2, expirationDate);
            preparedStatement.setString(3, name);

            result = preparedStatement.executeUpdate();


            preparedStatement.close();
            connection.close();

        } catch (SQLException e) {
            // process sql exception
            printSQLException(e);
        }

        return result;
    }

    public static int deleteBI(String user, Date expirationDate, String name) {
        String addIngredientsQuery = "DELETE " +
                "FROM BOUGHT_INGREDIENTS AS BI " +
                "WHERE BI.USERNAME = ? AND BI.EXPIRATION_DATE = ? AND BI.NAME = ?;";

        int result = 0;

        try {
            //Class.forName("org.postgresql.Driver");
            DriverManager.registerDriver(new org.postgresql.Driver());
            Connection connection = DriverManager
                    .getConnection("jdbc:postgresql://localhost:5432/Wa2Eat", "postgres", "password");

            PreparedStatement preparedStatement = connection.prepareStatement(addIngredientsQuery);
            preparedStatement.setString(1, user);
            preparedStatement.setDate(2, expirationDate);
            preparedStatement.setString(3, name);

            result = preparedStatement.executeUpdate();


            preparedStatement.close();
            connection.close();

        } catch (SQLException e) {
            // process sql exception
            printSQLException(e);
        }

        return result;
    }

    private static void printSQLException(SQLException ex) {
        for (Throwable e: ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}