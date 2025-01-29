package com.enchereseni.dal;

import com.enchereseni.bo.Category;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class CategoryDAOImpl implements CategoryDAO {
    @Override
    public List<Category> getCategories() {
        return List.of();
    }

    @Override
    public Category getCategory(int id) {
        return null;
    }

    @Override
    public Category createCategory(Category category) {
        return null;
    }

    @Override
    public Category updateCategory(Category category) {
        return null;
    }

    @Override
    public void deleteCategory(int id) {

    }
}
