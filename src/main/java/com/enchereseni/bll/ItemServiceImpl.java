package com.enchereseni.bll;

import com.enchereseni.bo.Auction;
import com.enchereseni.bo.Category;
import com.enchereseni.bo.ItemSold;
import com.enchereseni.bo.PickUp;
import com.enchereseni.dal.*;

import java.util.List;

public class ItemServiceImpl implements ItemService {


        private ItemSoldDAO itemSoldDAO ;
        private AuctionDAO auctionDAO;
        private CategoryDAO categoryDAO;
        private PickUpDAO pickUpDAO;
        private UserDAO userDAO;

    public ItemServiceImpl(ItemSoldDAO itemSoldDAO, AuctionDAO auctionDAO, CategoryDAO categoryDAO, PickUpDAO pickUpDAO, UserDAO userDAO) {
        this.itemSoldDAO = itemSoldDAO;
        this.auctionDAO = auctionDAO;
        this.categoryDAO = categoryDAO;
        this.pickUpDAO = pickUpDAO;
        this.userDAO = userDAO;
    }

    @Override
    public List<Auction> getAuctions() {
        return List.of();
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

    }

    @Override
    public List<Category> getCategories() {
        return List.of();
    }

    @Override
    public Category getCategory(int category) {
        return null;
    }
}
