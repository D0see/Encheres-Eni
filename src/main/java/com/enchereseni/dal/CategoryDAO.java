package com.enchereseni.dal;

import com.enchereseni.bo.Category;

import java.util.List;

public interface CategoryDAO {
    List<Category> getCategories();
    Category getCategory(int id);
    Category createCategory(Category category);
    Category updateCategory(Category category);
    void deleteCategory(int id);


}
