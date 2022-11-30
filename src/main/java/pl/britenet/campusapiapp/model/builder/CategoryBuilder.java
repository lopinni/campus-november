package pl.britenet.campusapiapp.model.builder;

import pl.britenet.campusapiapp.model.Category;

public class CategoryBuilder {

    private final Category category;

    public CategoryBuilder(Category category) {
        this.category = category;
    }

    public CategoryBuilder setId(int id) {
        this.category.setId(id);
        return this;
    }

    public CategoryBuilder setName(String name) {
        this.category.setName(name);
        return this;
    }

    public CategoryBuilder setImagePath(String imagePath) {
        this.category.setImagePath(imagePath);
        return this;
    }

    public Category getCategory() {
        return this.category;
    }
}
