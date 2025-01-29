package com.enchereseni.bll;

import com.enchereseni.bo.Auction;
import com.enchereseni.bo.Category;
import com.enchereseni.bo.ItemSold;

import java.util.List;

public interface ItemService {



    List<ItemSold> getItems();

    ItemSold getItemById(int id);

    void createItem(ItemSold item);


}
