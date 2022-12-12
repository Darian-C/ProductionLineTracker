package sample;

import java.util.List;

public abstract class Product implements Item, Comparable<Product> {

    private int id;
    private String name;
    private ItemType type;
    private String manufacturer;

    public Product(int id, String n, String m, ItemType type) {
        this.id = id;
        name = n;
        this.type = type;
        manufacturer = m;
    }


    public Product(String n, String m, ItemType type) {
        this.id = 0;
        name = n;
        this.type = type;
        manufacturer = m;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public ItemType getType() {
        return type;
    }

    public void setType(ItemType type) {
        this.type = type;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }


    public String toString() {
        return "Name: " + name + "\n"
                + "Manufacturer: " + manufacturer + "\n"
                + "Type: " + type;
    }


    @Override
    public int compareTo(Product o) {
        return name.compareTo(o.getName());
    }


    public static void printType(List<? extends Product> list, Class<?> cls) {
        for (Product listItem : list) {
            if (listItem.getClass().equals(cls)) {
                System.out.println(listItem);
            }
        }
    }
}


class Widget extends Product {


    public Widget(int id, String name, String manufacturer, ItemType type) {
        super(id, name, manufacturer, type);
    }


    public Widget(String name, String manufacturer, ItemType type) {
        super(name, manufacturer, type);
    }
}