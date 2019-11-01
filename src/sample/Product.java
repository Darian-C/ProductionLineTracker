package sample;

abstract class Product implements Item {
    //Fields
    int id;
    String type;
    String name;
    String manufacturer;


    public Product(String name, String manufacturer, String type){
        this.id = id;
        this.manufacturer = manufacturer;
        this.name = name;
        this.type = type;
        setManufacturer(manufacturer);
        setName(name);
        setType(type);
    }

    public int getId(){
        return id;
    }


    public void setType(String type){this.type = type;}

    public String getType(){
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



