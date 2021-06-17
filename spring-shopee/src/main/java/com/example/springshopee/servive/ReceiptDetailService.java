package com.example.springshopee.servive;

import com.example.springshopee.entity.Receipt;
import com.example.springshopee.repository.ReceiptDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReceiptDetailService {
    @Autowired
    ReceiptDetailRepository receiptDetailRepository;

    public List<Receipt> getAllReceipt(){
        try{
            return receiptDetailRepository.getAllReceipt();
        }catch (Exception ex){
            return null;
        }
    }

    public Boolean createReceipt(Receipt receipt){
        try {
            return receiptDetailRepository.createReceipt(receipt);
        }catch (Exception ex){
            return false;
        }
    }
}
