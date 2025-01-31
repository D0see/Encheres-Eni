package com.enchereseni.bll;

import com.enchereseni.bo.Auction;
import com.enchereseni.bo.Category;
import com.enchereseni.bo.ItemSold;
import com.enchereseni.bo.PickUp;

import java.util.List;

public interface ItemService {



    List<ItemSold> getItems();

    ItemSold getItemById(int id);

    void createItem(ItemSold item);

    List<Category> getCategories();
    Category getCategoryById(int id);

    void createPickUp(PickUp pickUp);
    void createPickUpWithItem(ItemSold item, PickUp pickUp);
    PickUp getPickUpById(int id);
}
