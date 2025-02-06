package com.enchereseni.dal;

import com.enchereseni.bo.Auction;

import java.util.List;

public interface AuctionDAO {
    void createAuction(Auction auction);
    void updateAuction(Auction auction);
    void deleteAuction(Auction auction);
    List<Auction> getAuctions();
    void deleteAuctionByUserId(int id);



}
