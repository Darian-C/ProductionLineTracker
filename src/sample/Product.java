package sample;

/**
 * @author Darian Colon
 */
abstract class Product implements Item {
    //Fields
    int id;
    ItemType type;
    String name;
    String manufacturer;


    /**
     * @param name
     * @param manufacturer
     * @param type
     */
    public Product(String name, String manufacturer, ItemType type) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.manufacturer = manufacturer;
    }

    public Product(String name, String manufacturer) {
        this.manufacturer = manufacturer;
        this.name = name;

    }

    public int getId() {
        return id;
    }

    public void setType(ItemType type) {
        this.type = type;
    }

    public ItemType getType() {
        return this.type;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    @Override
    public String getManufacturer() {
        return this.manufacturer;
    }


    /**
     * @return
     */
    @Override
    public String toString() {
        String s = "ID: " + id + "Name: " + name + "\n" +
                "Manufacturer: " + manufacturer + "\n" + "Type: " + type;
        return s;
    }
}



