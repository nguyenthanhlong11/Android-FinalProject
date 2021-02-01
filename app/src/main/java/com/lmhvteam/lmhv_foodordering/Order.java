package com.lmhvteam.lmhv_foodordering;

public class Order {
    private String documentId;
    private String idFood;
    private String idUser;
    private String imageFood;
    private String titleFood;
    private String totalPrice;
    public Order() {
    }

    public Order(String documentId, String idFood, String idUser, String imageFood, String totalPrice) {
        this.documentId = documentId;
        this.idFood = idFood;
        this.idUser = idUser;
        this.imageFood = imageFood;
        this.totalPrice = totalPrice;
    }

    public String getImageFood() {
        return imageFood;
    }

    public void setImageFood(String imageFood) {
        this.imageFood = imageFood;
    }

    public String getTitleFood() {
        return titleFood;
    }

    public void setTitleFood(String titleFood) {
        this.titleFood = titleFood;
    }

    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

    public String getIdFood() {
        return idFood;
    }

    public void setIdFood(String idFood) {
        this.idFood = idFood;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }
}
