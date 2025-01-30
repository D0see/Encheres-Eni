package com.enchereseni.bll;

import com.enchereseni.bo.Auction;
import com.enchereseni.bo.Category;
import com.enchereseni.bo.ItemSold;
import com.enchereseni.bo.PickUp;
import com.enchereseni.dal.*;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ItemServiceImpl implements ItemService {

    private CategoryDAO categoryDAO;
    private ItemSoldDAO itemSoldDAO;

    public ItemServiceImpl(ItemSoldDAO itemSoldDAO, CategoryDAO categoryDAO) {
        this.itemSoldDAO = itemSoldDAO;
        this.categoryDAO = categoryDAO;

    }


    @Override
    public List<ItemSold> getItems() {
        return List.of();
    }

    @Override
    public ItemSold getItemById(int id) {
        return null;
    }

    @Override
    public void createItem(ItemSold item) {
        itemSoldDAO.createItemSold(item);

    }

    @Override
    public List<Category> getCategories() {
        return categoryDAO.getAllCategories();
    }

    @Override
    public Category getCategoryById(int id) {
        return categoryDAO.getCategoryById(id);
    }

    @Override
    public void createPickUp(PickUp pickUp) {

    }

    @Override
    public PickUp getPickUpById(int id) {
        return null;
    }

}
