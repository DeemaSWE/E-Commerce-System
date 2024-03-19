package com.example.ecommerce.Service;

import com.example.ecommerce.Exception.InsufficientBalanceException;
import com.example.ecommerce.Exception.ProductOutOfStockException;
import com.example.ecommerce.Exception.UserNotFoundException;
import com.example.ecommerce.Model.MerchantStock;
import com.example.ecommerce.Model.Product;
import com.example.ecommerce.Model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class UserService {

    ArrayList<User> users = new ArrayList<>();
    private final MerchantStockService merchantStockService;
    private final ProductService productService;

    public ArrayList<User> getAllUsers() {
        return users;
    }

    public void addUser(User user) {
        users.add(user);
    }

    public void updateUser(User updatedUser, String id) {
        User user = getUserById(id);
        users.set(users.indexOf(user), updatedUser);
    }

    public void deleteUser(String id) {
        User user = getUserById(id);
        users.remove(user);
    }

    // Buy a product from a merchant
    public void buyProduct(String userId, String productId, String merchantId) {
        User user = getUserById(userId);
        Product product = productService.getProductById(productId);
        MerchantStock merchantStock = merchantStockService.getMerchantStockByProductIdAndMerchantId(productId, merchantId);

        if (merchantStock.getStock() == 0)
            throw new ProductOutOfStockException("Product is out of stock");

        if (user.getBalance() < product.getPrice())
            throw new InsufficientBalanceException("Insufficient balance");

        user.setBalance(user.getBalance() - product.getPrice());
        merchantStock.setStock(merchantStock.getStock() - 1);
        product.setUnitsSold(product.getUnitsSold() + 1);
    }

    // Send a gift card to another user
    public void sendGiftCard(String senderId, String receiverId, double amount) {
        User sender = getUserById(senderId);
        User receiver = getUserById(receiverId);

        if (sender.getBalance() < amount)
            throw new InsufficientBalanceException("Insufficient balance");

        sender.setBalance(sender.getBalance() - amount);
        receiver.setBalance(receiver.getBalance() + amount);
    }
    public User getUserById(String id) {
        for (User user : users) {
            if (user.getId().equals(id))
                return user;
        }
        throw new UserNotFoundException("User with id " + id + " not found");
    }

}
