package pl.britenet.campusapiapp.model;

public final class Category {

    public Category() {}

    private int id;
    private String name;
    private String imagePath;

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

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    @Override
    public String toString() {
        return "Category {" +
                " id=" + id +
                ", name='" + name + '\'' +
                ", imagePath='" + imagePath + '\'' +
                " }\n";
    }
}
