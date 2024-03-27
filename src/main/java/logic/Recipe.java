package logic;

public class Recipe {

    private String id;
    private String name;
    private String season;
    private String origin;
    private boolean isVegan;
    private String link;
    public Recipe(String id, String name, String season, String origin, boolean isVegan, String link) {
        setId(id);
        setName(name);
        setSeason(season);
        setOrigin(origin);
        setVegan(isVegan);
        setLink(link);
    }

    public String getLink() {return link;}
    public void setLink(String link) {

        this.link=link;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public boolean isVegan() {
        return isVegan;
    }

    public void setVegan(boolean vegan) {
        isVegan = vegan;
    }

    @Override
    public String toString() {
        String aux = "Recipe name: " + getName()
                + ", season: " + getSeason()
                + ", origin: " + getOrigin() + ", ";

        if (isVegan()) {
            aux += "is vegan";
        } else {
            aux += "isn't vegan";
        }

        return aux;
    }


}
