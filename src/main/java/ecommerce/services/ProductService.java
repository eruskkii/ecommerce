package ecommerce.services;

import ecommerce.dao.ProductDao;
import ecommerce.dao.CategoryDao;
import ecommerce.model.Category;
import ecommerce.model.Product;
import ecommerce.config.ConnectionUtils;

import java.util.List;

public class ProductService {
    private ProductDao productDao;
    private CategoryDao categoryDao;

    public ProductService() {
        this.productDao = new ProductDao(ConnectionUtils.getConnection());
        this.categoryDao = new CategoryDao(ConnectionUtils.getConnection());
    }

    // Fetch all categories using CategoryDao
    public List<Category> getAllCategories() {
        return categoryDao.getAllCategories();
    }

    // Add a new product
    public boolean addProduct(Product product) {
        return productDao.addProducts(product);
    }

    public boolean updateProduct(Product product) {
        return productDao.updateProduct(product);
    }

    public boolean deleteProduct(int productId) {
        return productDao.deleteProduct(productId);
    }

    public List<Product> getAllProducts() {
        return productDao.getAllProducts();
    }

    public Product getProductById(int productId) {
        return productDao.getProductById(productId);
    }



}
