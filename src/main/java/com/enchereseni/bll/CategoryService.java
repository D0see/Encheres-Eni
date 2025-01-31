package com.enchereseni.bll;

import com.enchereseni.bo.Category;
import org.springframework.stereotype.Service;

import java.util.List;


public interface CategoryService {
    List<Category> getAllCategories();
    Category getCategoryById(int id);
}
