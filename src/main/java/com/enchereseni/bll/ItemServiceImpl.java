package com.enchereseni.bll;

import com.enchereseni.bo.Auction;
import com.enchereseni.bo.Category;
import com.enchereseni.bo.ItemSold;
import com.enchereseni.bo.PickUp;
import com.enchereseni.dal.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ItemServiceImpl implements ItemService {

    private CategoryDAO categoryDAO;
    private ItemSoldDAO itemSoldDAO;
    private PickUpDAO pickUpDAO ;
@Autowired
    public ItemServiceImpl(ItemSoldDAO itemSoldDAO, CategoryDAO categoryDAO, PickUpDAO pickUpDAO) {
        this.itemSoldDAO = itemSoldDAO;
        this.categoryDAO = categoryDAO;
        this.pickUpDAO = pickUpDAO;
    }


    @Override
    public List<ItemSold> getItems() {
        return itemSoldDAO.getAllItemSold();
    }

    @Override
    public ItemSold getItemById(int id) {

        return itemSoldDAO.getItemSoldById(id);
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
        pickUpDAO.createPickUp(pickUp);

    }
    @Override
    public void createPickUpWithItem(ItemSold itemSold, PickUp pickUp) {

        pickUpDAO.createPickUpWithItem(itemSold, pickUp);

    }


    @Override
    public PickUp getPickUpById(int id) {
        return null;
    }

}
