package com.enchereseni.dal;

import com.enchereseni.bo.PickUp;

public interface PickUpDAO {
    void createPickUp(PickUp pickUp);
    PickUp getPickUp(int id);
}
