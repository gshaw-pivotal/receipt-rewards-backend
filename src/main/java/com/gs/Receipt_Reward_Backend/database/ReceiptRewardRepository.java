package com.gs.Receipt_Reward_Backend.database;

import com.gs.Receipt_Reward_Backend.model.Receipt;

import java.util.UUID;

public interface ReceiptRewardRepository {

    UUID addReceipt(Receipt receipt);

    void updateReceiptPoints(UUID receiptId, int points);

    int getPointsFromReceiptId(UUID receiptId);
}
