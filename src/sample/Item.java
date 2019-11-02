package sample;

import java.lang.*;

/**
 * @author Darian Colon
 */
public interface Item {
    //a method getId that would return an int
    int getId();

    //A method setName that would have one String parameter
    void setName(String name);

    //A method getName that would return a String
    String getName();

    //A method setManufacturer that would have one String parameter
    void setManufacturer(String manufacturer);

    //A method getManufacturer that would return a String
    String getManufacturer();
}
