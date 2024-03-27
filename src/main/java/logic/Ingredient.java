package logic;

public class Ingredient {

    private String name;
    private int calories;
    private int proteins;
    private int carbohydrates;
    private boolean isSweet;
    private boolean animalOrigin;

    private int amount;

    public Ingredient(String name, int calories, int proteins, int carbohydrates, boolean isSweet, boolean animalOrigin) {
        setName(name);
        setCalories(calories);
        setProteins(proteins);
        setCarbohydrates(carbohydrates);
        setSweet(isSweet);
        setAnimalOrigin(animalOrigin);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public int getProteins() {
        return proteins;
    }

    public void setProteins(int proteins) {
        this.proteins = proteins;
    }

    public int getCarbohydrates() {
        return carbohydrates;
    }

    public void setCarbohydrates(int carbohydrates) {
        this.carbohydrates = carbohydrates;
    }

    public boolean isSweet() {
        return isSweet;
    }

    public void setSweet(boolean sweet) {
        isSweet = sweet;
    }

    public boolean isAnimalOrigin() {
        return animalOrigin;
    }

    public void setAnimalOrigin(boolean animalOrigin) {
        this.animalOrigin = animalOrigin;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        String aux = "Ingredient name: " + name +
                ", calories: " + calories +
                ", proteins: " + proteins +
                ", carbohydrates: " + carbohydrates + ", ";
        if (isSweet()) {
            aux += "is sweet";
        } else {
            aux += "isn't sweet";
        }

        aux += ", ";

        if (isAnimalOrigin()) {
            aux += "isn't vegan";
        } else {
            aux += "is vegan";
        }

        return aux;
    }
}