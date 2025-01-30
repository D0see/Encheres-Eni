package com.enchereseni.dal;

import com.enchereseni.bo.ItemSold;
import com.enchereseni.bo.PickUp;

public interface PickUpDAO {
    void createPickUp(PickUp pickUp);
   void createPickUpWithItem(ItemSold item,PickUp pickUp);
    PickUp getPickUp(int id);
}
