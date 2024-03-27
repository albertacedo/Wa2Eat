package logic;

import jdbc.QueryFunctions;

import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

public class Util {

    public static boolean isNotNullOrEmpty(String str) {
        return (str != null && !str.isBlank() && !str.isEmpty());
    }

    public static List<Recipe> filterBySeason(List<Recipe> recipes, List<String> filters) {
        List<Recipe> aux = new ArrayList<>();
        for (Recipe recipe : recipes) {
            if (filters.contains(recipe.getSeason())) {
                aux.add(recipe);
            }
        }

        return aux;
    }

    public static List<Recipe> filterByOrigin(List<Recipe> recipes, List<String> filters) {
        List<Recipe> aux = new ArrayList<>();
        for (Recipe recipe : recipes) {
            if (filters.contains(recipe.getOrigin())) {
                aux.add(recipe);
            }
        }

        return aux;
    }

    public static List<Recipe> filterByNutritionalValue(List<Recipe> recipes, int maxValue) {
        List<Recipe> aux = new ArrayList<>();
        for (Recipe recipe : recipes) {
            List<Ingredient> ingredients = QueryFunctions.getRecipeIngredients(recipe.getName());
            float value = 0;

            for (Ingredient ingredient : ingredients) {
                value += ingredient.getCalories();
                value += ingredient.getProteins();
                value += ingredient.getCarbohydrates();
                value *= ingredient.getAmount();
                value /= 100000;
            }

            if (value <= maxValue) {
                aux.add(recipe);
            }
        }

        return aux;
    }

    public static List<Recipe> filterOnlyVegan(List<Recipe> recipes) {
        List<Recipe> aux = new ArrayList<>();
        for (Recipe recipe : recipes) {
            if (recipe.isVegan()) aux.add(recipe);
        }

        return aux;
    }

    public static List<Recipe> filterByBoughtIngredients(List<Recipe> recipes, String user) {
        List<Recipe> aux = new ArrayList<>();
        for (Recipe recipe : recipes) {
            boolean toAdd = false;
            List<Ingredient> ingredients = QueryFunctions.getRecipeIngredients(recipe.getName());
            List<BoughtIngredient> boughtIngredients = QueryFunctions.getBoughtIngredients(user);

            for (Ingredient ingredient : ingredients) {
                for (BoughtIngredient bI : boughtIngredients) {
                    if (ingredient.getName().equals(bI.getName())) {
                        toAdd = true;
                    }
                }
                if (toAdd) break;
            }

            if (toAdd) {
                aux.add(recipe);
            }
        }

        return aux;
    }

    public static List<Recipe> filterByDate(List<Recipe> recipes, String user, Date date) {
        List<Recipe> aux = new ArrayList<>();
        for (Recipe recipe : recipes) {
            boolean toAdd = false;
            List<Ingredient> ingredients = QueryFunctions.getRecipeIngredients(recipe.getName());
            List<BoughtIngredient> boughtIngredients = QueryFunctions.getBoughtIngredients(user);

            for (Ingredient ingredient : ingredients) {
                for (BoughtIngredient bI : boughtIngredients) {
                    Date expDate = Date.valueOf(bI.expiration_date());
                    if (ingredient.getName().equals(bI.getName()) && date.compareTo(expDate) > 0) {
                        toAdd = true;
                    }
                }
                if (toAdd) break;
            }

            if (toAdd) {
                aux.add(recipe);
            }
        }

        return aux;
    }

    public static List<String> processStrings(String[] strings) {
        List<String> list = new ArrayList<>();
        String aux;
        for (String string : strings) {
            String[] temp = string.split("/");
            for (String string2 : temp) {
                list.add(string2);
            }
        }

        return list;
    }

}
