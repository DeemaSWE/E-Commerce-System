package com.example.ecommerce.Service;

import com.example.ecommerce.Exception.MerchantNotFoundException;
import com.example.ecommerce.Model.Merchant;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class MerchantService {

    ArrayList<Merchant> merchants = new ArrayList<>();

    public ArrayList<Merchant> getAllMerchants() {
        return merchants;
    }

    public void addMerchant(Merchant merchant) {
        merchants.add(merchant);
    }

    public void updateMerchant(Merchant updatedMerchant, String id) {
        Merchant merchant = getMerchantById(id);
        merchants.set(merchants.indexOf(merchant), updatedMerchant);
    }

    public void deleteMerchant(String id) {
        Merchant merchant = getMerchantById(id);
        merchants.remove(merchant);
    }

    public Merchant getMerchantById(String id) {
        for (Merchant merchant : merchants) {
            if (merchant.getId().equals(id))
                return merchant;
        }
        throw new MerchantNotFoundException("Merchant with id " + id + " not found");
    }
}
