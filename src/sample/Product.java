package sample;

abstract class Product implements Item {
    //Fields
    int id;
    ItemType type;
    String name;
    String manufacturer;


    public Product(String name, String manufacturer, ItemType type){
        this.id = id;
        this.type = type;
    }

    public Product(String name, String manufacturer) {
        this.manufacturer = manufacturer;
        this.name = name;
        setManufacturer(manufacturer);
        setName(name);
    }

    public int getId(){
        return id;
    }


    public void setType(ItemType type){this.type = type;}

    public ItemType getType(){
        return this.type;
    }

    @Override
    public void setName(String name){
        this.name = name;
    }

    @Override
    public String getName(){
        return this.name;
    }

    @Override
    public void setManufacturer(String manufacturer){this.manufacturer = manufacturer;}

    @Override
    public String getManufacturer(){return this.manufacturer;}

    @Override
    public String toString(){
        String s = "ID: " + id + "Name: " + name + "\n" +
                "Manufacturer: " + manufacturer + "\n" + "Type: " + type;
        return s;
    }
}



