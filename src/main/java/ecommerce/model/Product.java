package ecommerce.model;

public class Product {
    private int product_id;
    private String name;
    private String description;
    private double price;
    private int quantity;
    private int category_id;

//    Constructor to add new product. Doesn't need Product_id as it's set to auto increment on the DB
    public Product(String name, String description, double price, int quantity) {

        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;

    }

//    Constructor to fetch all products. This time we need to fetch the db

    public Product(int product_id, String name, String description, double price, int quantity) {

        this.product_id = product_id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;

    }

    public Product() {
    }

    public int getId() {
        return product_id;
    }

    public void setId(int id) {
        this.product_id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + product_id +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }


}
