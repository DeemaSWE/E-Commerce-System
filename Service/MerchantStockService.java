package com.example.ecommerce.Service;

import com.example.ecommerce.Exception.MerchantStockNotFoundException;
import com.example.ecommerce.Model.Merchant;
import com.example.ecommerce.Model.MerchantStock;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class MerchantStockService {

    ArrayList<MerchantStock> merchantStocks = new ArrayList<>();
    private final MerchantService merchantService;
    private final ProductService productService;

    public ArrayList<MerchantStock> getAllMerchantStocks() {
        return merchantStocks;
    }

    public void addMerchantStock(MerchantStock merchantStock) {
        merchantService.getMerchantById(merchantStock.getMerchantId());
        productService.getProductById(merchantStock.getProductId());
        merchantStocks.add(merchantStock);
    }

    public void updateMerchantStock(MerchantStock updatedMerchantStock, String id) {
        MerchantStock merchantStock = getMerchantStockById(id);
        merchantService.getMerchantById(updatedMerchantStock.getMerchantId());
        productService.getProductById(updatedMerchantStock.getProductId());
        merchantStocks.set(merchantStocks.indexOf(merchantStock), updatedMerchantStock);
    }
    public void deleteMerchantStock(String id) {
        MerchantStock merchantStock = getMerchantStockById(id);
        merchantStocks.remove(merchantStock);
    }

    // Add more stocks of a product to a merchant Stock
    public void addMoreStocks(String productId, String merchantId, Integer amount) {
        MerchantStock merchantStock = getMerchantStockByProductIdAndMerchantId(productId, merchantId);
        merchantStock.setStock(merchantStock.getStock() + amount);
    }

    public MerchantStock getMerchantStockByProductIdAndMerchantId(String productId, String merchantId) {
        for (MerchantStock merchantStock : merchantStocks) {
            if (merchantStock.getProductId().equals(productId) && merchantStock.getMerchantId().equals(merchantId))
                return merchantStock;
        }
        throw new MerchantStockNotFoundException("Merchant stock for product " + productId + " and merchant " + merchantId + " not found");
    }

    public MerchantStock getMerchantStockById(String id) {
        for (MerchantStock merchantStock : merchantStocks) {
            if (merchantStock.getId().equals(id))
                return merchantStock;
        }
        throw new MerchantStockNotFoundException("Merchant stock with id " + id + " not found");
    }
}
