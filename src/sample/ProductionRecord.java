package sample;

import java.util.Date;

public class ProductionRecord {
    private int productionNumber;
    private int productID;
    private String serialNumber;
    Date dateProduced = new Date();

    ProductionRecord(int pNum){
        this.productionNumber = pNum;
        this.productID = 0;
        this.serialNumber = "0";
        this.dateProduced = new Date();
    }

    ProductionRecord(int pNum, int pID, String sNum, Date dateProduced){
        this.productionNumber = pNum;
        this.productID = pID;
        this.serialNumber = sNum;
        this.dateProduced = dateProduced;
    }
    @Override
    public String toString() {
        return "Prod. Num: " + productionNumber
                + " Product ID: " + productID + " Serial Num: "
                + serialNumber + " Date: " + dateProduced;
    }


    public void setProductionNumber(int pNum){

        this.productionNumber = pNum;
    }
    public void setProductID(int pID){

        this.productID = pID;
    }
    public void setSerialNumber(String sNum){

        this.serialNumber = sNum;
    }
    public void setProdDate(Date dateProduced){

        this.dateProduced = dateProduced;
    }

    public int getProductionNumber(){

        return this.productionNumber;
    }
    public int getProductID(){

        return this.productID;
    }
    public String getSerialNumber(){

        return this.serialNumber;
    }
    public Date getProdDate(){

        return dateProduced;
    }
}