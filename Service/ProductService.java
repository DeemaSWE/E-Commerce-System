package com.example.ecommerce.Service;

import com.example.ecommerce.Exception.InvalidRatingException;
import com.example.ecommerce.Exception.ProductNotFoundException;
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

    public ArrayList<Product> getAllProducts() {
        return products;
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public void updateProduct(Product updatedProduct, String id) {
        Product product = getProductById(id);
        products.set(products.indexOf(product), updatedProduct);
    }

    public void deleteProduct(String id) {
        Product product = getProductById(id);
        products.remove(product);
    }

    // Get top-selling products
    public List<Product> getTopSellingProducts(int limit) {
        products.sort(Comparator.comparingInt(Product::getUnitsSold).reversed());

        if(limit > products.size())
            return products;

        return products.subList(0, limit);
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
