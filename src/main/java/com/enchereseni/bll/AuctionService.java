package com.enchereseni.bll;

import com.enchereseni.bo.Auction;
import com.enchereseni.bo.ItemSold;
import com.enchereseni.bo.User;

import java.util.List;

public interface AuctionService {
    void createAuction(Auction auction);
    void updateAuction(Auction auction);
    void deleteAuction(Auction auction);
    Auction getAuction(int auctionId);
    List<Auction> getAllAuctions();
    List<Auction> getAuctionsByUsername(String username);
    List<Auction> getAuctionsByItem(ItemSold itemSold);
}
