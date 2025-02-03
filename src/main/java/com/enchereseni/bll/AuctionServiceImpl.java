package com.enchereseni.bll;

import com.enchereseni.bo.Auction;
import com.enchereseni.bo.ItemSold;
import com.enchereseni.dal.AuctionDAO;
import com.enchereseni.dal.AuctionDAOImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuctionServiceImpl implements AuctionService {

    private final AuctionDAO auctionDAO;

    public AuctionServiceImpl(AuctionDAO auctionDAO) {
        this.auctionDAO = auctionDAO;
    }

    @Override
    public void createAuction(Auction auction) {
        auctionDAO.createAuction(auction);
    }

    @Override
    public void updateAuction(Auction auction) {
        auctionDAO.updateAuction(auction);
    }

    @Override
    public void deleteAuction(Auction auction) {

    }

    @Override
    public Auction getAuction(int auctionId) {
        return null;
    }

    @Override
    public List<Auction> getAllAuctions() {
        return auctionDAO.getAuctions();
    }

    @Override
    public List<Auction> getAuctionsByUsername(String username) {
        return getAllAuctions().stream().filter(auction -> {
            return auction.getUser().getUsername().equals(username);
        }).toList();

    }

    @Override
    public List<Auction> getAuctionsByItem(ItemSold itemSold) {
        return getAllAuctions().stream().filter(auction -> {
            return auction.getItemSold().getItemId() == itemSold.getItemId();
        }).toList();
    }

}
