package com.gs.Receipt_Reward_Backend.service;

import com.gs.Receipt_Reward_Backend.database.ReceiptRewardRepository;
import com.gs.Receipt_Reward_Backend.model.Receipt;

import java.util.UUID;

public class ReceiptRewardService {

    private final ReceiptRewardRepository receiptRewardRepository;

    public ReceiptRewardService(ReceiptRewardRepository receiptRewardRepository) {
        this.receiptRewardRepository = receiptRewardRepository;
    }

    public String acceptReceipt(Receipt receipt) {
        UUID receiptId = receiptRewardRepository.addReceipt(receipt);
        receiptRewardRepository.updateReceiptPoints(receiptId, 0);
        return receiptId.toString();
    }

    public int getReceiptPoints(String receiptId) {
        return receiptRewardRepository.getPointsFromReceiptId(UUID.fromString(receiptId));
    }
}