package com.example.ecommerce.Service;

import com.example.ecommerce.Exception.InvalidRatingException;
import com.example.ecommerce.Exception.ProductNotFoundException;
import com.example.ecommerce.Model.Category;
import com.example.ecommerce.Model.Product;
import com.example.ecommerce.Model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    ArrayList<Product> products = new ArrayList<>();
    private final CategoryService categoryService;
    public ArrayList<Product> getAllProducts() {
        return products;
    }

    public void addProduct(Product product) {
        categoryService.getCategoryById(product.getCategoryId());
        products.add(product);
    }

    public void updateProduct(Product updatedProduct, String id) {
        Product product = getProductById(id);
        categoryService.getCategoryById(updatedProduct.getCategoryId());
        products.set(products.indexOf(product), updatedProduct);
    }

    public void deleteProduct(String id) {
        Product product = getProductById(id);
        products.remove(product);
    }

    // Get top-selling products for a specified category
    public List<Product> getTopSellingProducts(String categoryName) {
        List<Product> topSellingProducts = new ArrayList<>();

        for (Product product : products) {
            Category category = categoryService.getCategoryById(product.getCategoryId());
            if (category.getName().equalsIgnoreCase(categoryName))
                topSellingProducts.add(product);
        }

        if(topSellingProducts.isEmpty())
            throw new ProductNotFoundException("No products found for category " + categoryName);

        topSellingProducts.sort(Comparator.comparingInt(Product::getUnitsSold).reversed());

        if(topSellingProducts.size() > 20)
            return topSellingProducts.subList(0, 20);

        return topSellingProducts;
    }

    // Rate a product
    public void rateProduct(String productId, double rating) {
        if(rating < 0 || rating > 5)
            throw new InvalidRatingException("Rating must be a number between 0 and 5");

        Product product = getProductById(productId);
        double averageRating = product.getAverageRating();
        int ratingCount = product.getRatingCount();

        double newAverageRating = (averageRating * ratingCount + rating) / (ratingCount + 1);
        product.setAverageRating(newAverageRating);
        product.setRatingCount(ratingCount + 1);
    }
    public Product getProductById(String id) {
        for (Product product : products) {
            if (product.getId().equals(id))
                return product;
        }
        throw new ProductNotFoundException("Product with id " + id + " not found");
    }
}
