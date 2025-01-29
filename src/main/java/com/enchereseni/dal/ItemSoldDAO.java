package com.enchereseni.dal;

import com.enchereseni.bo.ItemSold;

import java.util.List;

public interface ItemSoldDAO {
    void createItemSold(ItemSold itemSold);
    ItemSold getItemSoldById(int id);
    List<ItemSold> getAllItemSold();
}
