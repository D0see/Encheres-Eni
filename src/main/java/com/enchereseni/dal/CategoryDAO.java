package com.enchereseni.dal;

import com.enchereseni.bo.Category;

import java.util.List;

public interface CategoryDAO {
    List<Category> getAllCategories(); // Recupera todas las categorías
    Category getCategoryById(int noCategorie);

}
