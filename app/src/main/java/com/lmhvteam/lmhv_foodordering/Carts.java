package com.lmhvteam.lmhv_foodordering;

public class Carts {
    private String documentId, titleFood, priceFood, imageFood, idUser, idFood;

    public Carts(String documentId, String titleFood, String priceFood, String imageFood, String idUser, String idFood) {
        this.documentId = documentId;
        this.titleFood = titleFood;
        this.priceFood = priceFood;
        this.imageFood = imageFood;
        this.idUser = idUser;
        this.idFood = idFood;
    }

    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

    public String getTitleFood() {
        return titleFood;
    }

    public void setTitleFood(String titleFood) {
        this.titleFood = titleFood;
    }

    public String getPriceFood() {
        return priceFood;
    }

    public void setPriceFood(String priceFood) {
        this.priceFood = priceFood;
    }

    public String getImageFood() {
        return imageFood;
    }

    public void setImageFood(String imageFood) {
        this.imageFood = imageFood;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getIdFood() {
        return idFood;
    }

    public void setIdFood(String idFood) {
        this.idFood = idFood;
    }
}
