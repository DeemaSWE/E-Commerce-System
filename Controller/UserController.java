package com.example.ecommerce.Controller;

import com.example.ecommerce.Api.ApiResponse;
import com.example.ecommerce.Model.User;
import com.example.ecommerce.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/get")
    public ResponseEntity getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @PostMapping("/add")
    public ResponseEntity addUser(@RequestBody @Valid User user, Errors errors) {
        if (errors.hasErrors())
            return ResponseEntity.badRequest().body(errors.getFieldError().getDefaultMessage());

        userService.addUser(user);
        return ResponseEntity.ok(new ApiResponse("User added successfully"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateUser(@PathVariable String id, @RequestBody @Valid User user, Errors errors) {
        if (errors.hasErrors())
            return ResponseEntity.badRequest().body(errors.getFieldError().getDefaultMessage());

        userService.updateUser(user, id);
        return ResponseEntity.ok(new ApiResponse("User updated successfully"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteUser(@PathVariable String id) {
        userService.deleteUser(id);
        return ResponseEntity.ok(new ApiResponse("User deleted successfully"));
    }

    // Endpoint where a user can buy a product from a merchant
    @PutMapping("/buy-product/{userId}/{productId}/{merchantId}")
    public ResponseEntity buyProduct(@PathVariable String userId, @PathVariable String productId, @PathVariable String merchantId) {
        userService.buyProduct(userId, productId, merchantId);
        return ResponseEntity.ok(new ApiResponse("Product bought successfully"));
    }

    // Endpoint where a user can send a gift card to another user
    @PutMapping("/send-gift-card/{senderId}/{receiverId}/{amount}")
    public ResponseEntity sendGiftCard(@PathVariable String senderId, @PathVariable String receiverId, @PathVariable double amount) {
        userService.sendGiftCard(senderId, receiverId, amount);
        return ResponseEntity.ok(new ApiResponse("Gift card sent successfully"));
    }
}
