package sample;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**This part of the program.
 * @author Darian Colon
 */
public class ProductionRecord {
    private int productionNumber;
    private int productID;
    private String serialNumber;
    Date dateProduced = new Date();



    public ProductionRecord(int pNum) {
        this.productionNumber = pNum;
        this.productID = 0;
        this.serialNumber = "0";
        this.dateProduced = new Date();
    }

    /**
     * @param pNum
     * @param pID
     * @param sNum
     * @param dateProduced
     */
    public ProductionRecord(int pNum, int pID, String sNum, Date dateProduced) {
        this.productionNumber = pNum;
        this.productID = pID;
        this.serialNumber = sNum;
        this.dateProduced = dateProduced;
    }

    /**
     * @return
     */
    @Override
    public String toString() {
        return "Prod. Num: " + productionNumber
                + " Product ID: " + productID + " Serial Num: "
                + serialNumber + " Date: " + dateProduced;
    }

    public void setProductionNumber(int pNum) {

        this.productionNumber = pNum;
    }

    public void setProductID(int pID) {

        this.productID = pID;
    }

    public void setSerialNumber(String sNum) {

        this.serialNumber = sNum;
    }

    private void setProdDate(Date dateProduced) {

        this.dateProduced = dateProduced;
    }

    public int getProductionNumber() {

        return this.productionNumber;
    }

    public int getProductID() {

        return this.productID;
    }

    public String getSerialNumber() {

        return this.serialNumber;
    }

    public Date getProdDate() {

        return dateProduced;
    }
}