package logic;

public class BoughtIngredient {

    private String user;

    private String expiration_date;

    private String name;


    public BoughtIngredient(String expiration_date, String name) {
        this.user = null;
        this.expiration_date = expiration_date;
        this.name = name;
    }


    public String expiration_date() {
        return expiration_date;
    }

    public void setExpiration_date(String expiration_date) {
        this.expiration_date = expiration_date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    @Override
    public String toString() {
        String aux = "Name: " + name +
                ", Expiration_date: " + expiration_date + ".";
        return aux;
    }
}
