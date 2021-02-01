package com.lmhvteam.lmhv_foodordering;

public class Food {
    private String documentId, title, image, price, description, idCategory;

    public String getDescription() {
        return description;
    }

    public String getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(String idCategory) {
        this.idCategory = idCategory;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Food(String documentId, String description, String idCategory) {
        this.documentId = documentId;
        this.description = description;
        this.idCategory = idCategory;
    }
    public  Food(String description, String idCategory){

        this.description = description;
        this.idCategory = idCategory;
    }

    public Food(String documentId, String title, String image, String price, String description, String idCategory) {
        this.documentId = documentId;
        this.title = title;
        this.image = image;
        this.price = price;
        this.description = description;
        this.idCategory = idCategory;
    }

    public String getDocumentId() {
        return documentId;
    }

    public String getTitle() {
        return title;
    }

    public String getImage() {
        return image;
    }

    public String getPrice() {
        return price;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setPrice(String price) {
        this.price = price;
    }


}
