package com.example.springshopee.repository;

import com.example.springshopee.entity.Receipt;
import com.example.springshopee.helper.ReceiptMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class ReceiptDetailRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;
    public List<Receipt> getAllReceipt(){
        String sql = "Select * from Receipt where deleted = 0;";
        List<Receipt> list = jdbcTemplate.query(sql, new ReceiptMapper());

        String sql1 = "Select * from ReceiptDetail where receiptID = 0;";
        for(int i = 0; i < list.size(); i++){
            String id = list.get(i).getAccountID();
            list.get(i).setReceiptDetaillist(jdbcTemplate.query(sql1, new ReceiptMapper(), new Object[]{id}));
        }
        return list;
    }

    public Boolean createReceipt(Receipt receipt){
        String id = UUID.randomUUID().toString();
        String sql = "Insert into ReceiptDetail(receiptID, productID, count) values (?, ?, ?);";
        for(int i = 0; i < receipt.getReceiptDetaillist().size(); i++){
            Object param[] = new Object[3];
            receipt.getReceiptDetaillist().get(i).setReceiptID(id);
            param[0] = receipt.getReceiptDetaillist().get(i).getReceiptID();
            param[1] = receipt.getReceiptDetaillist().get(i).getProductID();
            param[3] = receipt.getReceiptDetaillist().get(i).getCount();
            jdbcTemplate.update(sql, param);
        }

        String sql1 = "Insert into Receipt(receiptID, accountID, total_money, status) values (?, ?, ?, ?);";
        jdbcTemplate.update(sql1, id, receipt.getReceiptID(), receipt.getAccountID(), receipt.getTotal_money(), receipt.getStatus());
        return true;
    }
}
