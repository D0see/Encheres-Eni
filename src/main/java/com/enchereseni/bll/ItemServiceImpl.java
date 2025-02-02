package com.enchereseni.bll;

import com.enchereseni.bo.*;
import com.enchereseni.dal.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ItemServiceImpl implements ItemService {

    private CategoryDAO categoryDAO;
    private ItemSoldDAO itemSoldDAO;
    private PickUpDAO pickUpDAO ;
    private EtatVente etatVente;
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

    @Override
    public void removeItem(ItemSold item) {
    if (item.getBeginningAuctionDate().isBefore(LocalDate.now())) {
        itemSoldDAO.removeItemSoldById(item.getItemId());
        }
    }
}
