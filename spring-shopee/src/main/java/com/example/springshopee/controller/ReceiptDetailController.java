package com.example.springshopee.controller;

import com.example.springshopee.entity.Receipt;
import com.example.springshopee.servive.ReceiptDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/receipt")
public class ReceiptDetailController {
    @Autowired
    ReceiptDetailService receiptDetailService;

    @GetMapping("/getAllReceipt")
    public List<Receipt> getAllReceipt(){
        return receiptDetailService.getAllReceipt();
    }

    @GetMapping("/createReceipt")
    public Boolean createReceipt(@RequestBody Receipt receipt){
        return receiptDetailService.createReceipt(receipt);
    }
}
