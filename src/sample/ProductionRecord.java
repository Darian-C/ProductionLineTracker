package sample;

import javafx.scene.control.ListView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ProductionRecord {

    private int productionNum;
    private int productID;
    private String serialNum;
    private Date prodDate;

    public Date getProdDate() {
        return new Date(prodDate.getTime());
    }

    public void setProdDate(Date prodDate) {
        this.prodDate = new Date(prodDate.getTime());
    }

    public int getProductionNum() {
        return productionNum;
    }

    public void setProductionNum(int productionNum) {
        this.productionNum = productionNum;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public String getSerialNum() {
        return serialNum;
    }

    public void setSerialNum(String serialNum) {
        this.serialNum = serialNum;
    }


    public ProductionRecord(int productID) {
        this.productionNum = 0;
        this.productID = productID;
        serialNum = "0";
        prodDate = new Date();
    }


    public ProductionRecord(Product product, int typeCount) {
        this.productionNum = 0;
        this.productID = product.getId();

        serialNum = product.getManufacturer().substring(0, 3) + product.getType().code
                + String.format("%05d", typeCount);
        prodDate = new Date();
    }


    public ProductionRecord(int prodNum, int productID, String sn, Date date) {
        this.productionNum = prodNum;
        this.productID = productID;
        serialNum = sn;
        prodDate = new Date(date.getTime());
    }

    public String toString() {
        return "Prod. Num: " + productionNum + " Product ID: " + productID + " Serial Num: "
                + serialNum + " Date: " + prodDate;
    }
}