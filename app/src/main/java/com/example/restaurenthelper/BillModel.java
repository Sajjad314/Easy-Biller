package com.example.restaurenthelper;

public class BillModel {
    private String tableId, totalProduct, priceList, totalPrice, date;

    public void setTableId(String tableId) {
        this.tableId = tableId;
    }

    public void setTotalProduct(String totalProduct) {
        this.totalProduct = totalProduct;
    }

    public void setPriceList(String priceList) {
        this.priceList = priceList;
    }

    public String getTableId() {
        return tableId;
    }

    public String getTotalProduct() {
        return totalProduct;
    }

    public String getPriceList() {
        return priceList;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public String getDate() {
        return date;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
