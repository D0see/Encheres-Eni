package com.enchereseni.dal;

import com.enchereseni.bo.ItemSold;

import java.util.List;

public interface ItemSoldDAO {
    int createItemSold(ItemSold itemSold);
    ItemSold getItemSoldById(int id);
    List<ItemSold> getAllItemSold();
    void updateItemSold(ItemSold itemSold);
    void removeItemSoldById(int id);
}
