package src;

public class Product {
    public String name;
    public String brand;
    public double price;
    public String description;
    public String imagePath;

    public Product(String name, String brand, double price, String description, String imagePath) {
        this.name = name;
        this.brand = brand;
        this.price = price;
        this.description = description;
        this.imagePath = imagePath;
    }
}
