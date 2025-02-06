package com.enchereseni.bll;

import com.enchereseni.bo.*;
import com.enchereseni.dal.*;
import com.enchereseni.dal.exceptions.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.enchereseni.dal.exceptions.BusinessCode;

import java.time.LocalDate;
import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    private CategoryDAO categoryDAO;
    private ItemSoldDAO itemSoldDAO;
    private PickUpDAO pickUpDAO ;
    private EtatVente etatVente;
@Autowired
    public ItemServiceImpl(ItemSoldDAO itemSoldDAO, CategoryDAO categoryDAO, PickUpDAO pickUpDAO) {
        this.itemSoldDAO = itemSoldDAO;
        this.categoryDAO = categoryDAO;
        this.pickUpDAO = pickUpDAO;
    }


    @Override
    public List<ItemSold> getItems() {
        return itemSoldDAO.getAllItemSold();
    }

    @Override
    public ItemSold getItemById(int id) {

        return itemSoldDAO.getItemSoldById(id);
    }

    @Override
    public void createItem(ItemSold item) {
        itemSoldDAO.createItemSold(item);

    }

    @Override
    public void updateItem(ItemSold item) {
        itemSoldDAO.updateItemSold(item);
    }

    @Override
    public List<Category> getCategories() {
        return categoryDAO.getAllCategories();
    }

    @Override
    public Category getCategoryById(int id) {
        return categoryDAO.getCategoryById(id);
    }

    @Override
    public void createPickUp(PickUp pickUp) {
        pickUpDAO.createPickUp(pickUp);

    }
    @Override
    public void createPickUpWithItem(ItemSold itemSold, PickUp pickUp) {

        pickUpDAO.createPickUpWithItem(itemSold, pickUp);

    }


    @Override
    public PickUp getPickUpById(int id) {
        return null;
    }

    @Override
    public void removeItem(ItemSold item) {
        itemSoldDAO.removeItemSoldById(item.getItemId());
    }

//    @Transactional
//    public void creationItemSold(ItemSold itemSold,PickUp pickUp ) {
//        BusinessException be = new BusinessException();
//        boolean isValid = true;
//        isValid &= validerItemName(itemSold.getName(), be);
//        isValid &= validerItemDescription(itemSold.getDescription(), be);
//        isValid &= validerItemBeginningAuctionDate(itemSold.getBeginningAuctionDate(), be);
//        isValid &= validerItemEndingAuctionDate(itemSold.getEndingAuctionDate(), be);
//        isValid &= validerItemCategory(itemSold.getCategory(), be);
//        isValid &= validerItemAddress(pickUp.getAddress(), be);
//        isValid &= validerItemZipCode(pickUp.getZipCode(), be);
//        isValid &= validerItemCity(pickUp.getCity(), be);
//
//        if (isValid) {
//            try{
//                itemSoldDAO.createItemSold(itemSold);
//            }catch (DataAccessException e) {
//                be.add(BusinessCode.BLL_ITEM_CREER_ERREUR);
//                throw be;
//            }
//            } else {
//                throw be;
//        }
//    }
//
//    //NAME
//    private boolean validerItemName(String itemName, BusinessException be){
//    if (itemName.equals("")) {
//        be.add(BusinessCode.VALIDATION_ITEM_NAME_EMPTY);
//        return false;
//    }
//    if (itemName.length() > 30) {
//        be.add(BusinessCode.VALIDATION_ITEM_NAME_SIZE);
//        return false;
//    }
//    return true;
//    }
//
//    //DESCRIPTION
//    private boolean validerItemDescription(String itemDescription, BusinessException be){
//    if (itemDescription.equals("")) {
//        be.add(BusinessCode.VALIDATION_ITEM_DESCRIPTION_ITEM);
//        return false;
//    }
//    if (itemDescription.length() > 300) {
//        be.add(BusinessCode.VALIDATION_ITEM_DESCRIPTION_SIZE);
//    }
//    return true;
//    }
//
//    //BEGINNING ACTION DATE
//    private boolean validerItemBeginningAuctionDate(LocalDate date, BusinessException be){
//        if (date == null){
//            be.add(BusinessCode.VALIDATION_ITEM_NULL);
//            return false;
//        }
//
//    if (date.isBefore(LocalDate.now())) {
//        be.add(BusinessCode.VALIDATION_ITEM_TOOEARLY);
//    }
//
//    return true;
//    }
//
//    //ENDING AUCTION DATE
//    private boolean validerItemEndingAuctionDate(LocalDate beginningAuctionDate, LocalDate endingAuctionDate, BusinessException be){
//    //TO DO validation pour que la fin ne soit pas avant le debut enchere
//    if (endingAuctionDate.isBefore(beginningAuctionDate)) {
//        be.add(BusinessCode.VALIDATION_ITEM_TOO_TOOEARLY);
//    }
//
//    }
//
//    //CATEGORY
//    private boolean validerItemCategory(Category category, BusinessException be){
//    if (category == null) {
//        be.add(BusinessCode.VALIDATION_ITEM_CATEGORY_NULL);
//        return false;
//    }
//    return true;
//    }


}
