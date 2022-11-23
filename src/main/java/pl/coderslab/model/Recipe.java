package pl.coderslab.model;

public class Recipe {
    private int id;
    private String name;
    private String description;
    private String ingredients;
    private String created;
    private String updated;
    private int preparationTime;
    private String preparation;
    private int adminId;

    public Recipe() {
    }

    public Recipe(String name, String description, String ingredients, int preparationTime, String preparation, int adminId) {
        this.name = name;
        this.description = description;
        this.ingredients = ingredients;
        this.preparationTime = preparationTime;
        this.preparation = preparation;
        this.adminId = adminId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    public int getPreparationTime() {
        return preparationTime;
    }

    public void setPreparationTime(int preparationTime) {
        this.preparationTime = preparationTime;
    }

    public String getPreparation() {
        return preparation;
    }

    public void setPreparation(String preparation) {
        this.preparation = preparation;
    }

    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }

    @Override
    public String toString() {
        return "Recipe[" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", ingredients='" + ingredients + '\'' +
                ", created='" + created + '\'' +
                ", updated='" + updated + '\'' +
                ", preparationTime=" + preparationTime +
                ", preparation='" + preparation + '\'' +
                ", adminId=" + adminId +
                ']';
    }
}