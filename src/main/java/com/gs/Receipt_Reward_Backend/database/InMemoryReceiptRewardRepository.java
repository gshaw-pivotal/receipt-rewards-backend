package com.gs.Receipt_Reward_Backend.database;

import com.gs.Receipt_Reward_Backend.exception.NotFoundException;
import com.gs.Receipt_Reward_Backend.model.Receipt;
import com.gs.Receipt_Reward_Backend.model.ReceiptEntity;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class InMemoryReceiptRewardRepository implements ReceiptRewardRepository {

    Map<UUID, ReceiptEntity> receiptRecords = new HashMap<>();

    @Override
    public UUID addReceipt(Receipt receipt) {
        UUID receiptId = UUID.randomUUID();
        receiptRecords.put(receiptId, convertToReceiptEntity(receipt));
        return receiptId;
    }

    @Override
    public void updateReceiptPoints(UUID receiptId, int points) {
        if (receiptRecords.containsKey(receiptId)) {
            receiptRecords.get(receiptId).setPoints(points);
        } else {
            throw new NotFoundException("No record found for receipt id " + receiptId);
        }
    }

    @Override
    public int getPointsFromReceiptId(UUID receiptId) {
        if (receiptRecords.containsKey(receiptId)) {
            return receiptRecords.get(receiptId).getPoints();
        } else {
            throw new NotFoundException("No record found for receipt id " + receiptId);
        }
    }

    private ReceiptEntity convertToReceiptEntity(Receipt receipt) {
        return ReceiptEntity.builder()
                .retailer(receipt.getRetailer())
                .purchaseDate(receipt.getPurchaseDate())
                .purchaseTime(receipt.getPurchaseTime())
                .items(receipt.getItems())
                .total(receipt.getTotal())
                .build();
    }
}
