package com.enchereseni.configuration.converter;

import com.enchereseni.bo.Category;
import com.enchereseni.dal.CategoryDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToCategoryConverter implements Converter<String, Category> {
    @Autowired
    private CategoryDAO categoryDAO;
    @Override
    public Category convert(String id) {
        try {
            int categoryId = Integer.parseInt(id);
            Category category = categoryDAO.getCategoryById(categoryId);
            if (category == null) {
                throw new IllegalArgumentException("Categoría no encontrada");
            }
            return category;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("ID inválido: " + id);
        }
    }

}
