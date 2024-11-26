package com.gs.Receipt_Reward_Backend.service;

import com.gs.Receipt_Reward_Backend.model.Receipt;
import com.gs.Receipt_Reward_Backend.model.ReceiptItem;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RewardPointsCalculator {

    public int calculateRewardPoints(Receipt receipt) {
        int totalPoints = 0;

        //1 point per alphanumeric character in retailer name
        totalPoints = totalPoints + calculateRetailerNamePoint(receipt.getRetailer());

        //50 points if total is round dollar amount
        totalPoints = totalPoints + calculateRoundDollarPoints(receipt.getTotal());

        //25 points if total is multiple of 0.25
        totalPoints = totalPoints + calculateMultipleOf25Points(receipt.getTotal());

        //5 points for every 2 items
        totalPoints = totalPoints + calculateItemListPoints(receipt.getItems());

        //Points for each item whose trimmed description is a multiple of 3,
        //where the points are calculated as the price multiplied by 0.2 rounded up
        totalPoints = totalPoints + calculateItemDescriptionPoints(receipt.getItems());

        //6 points if day is odd
        totalPoints = totalPoints + calculateOddDayPoints(receipt.getPurchaseDate());

        //10 points for purchase after 2:00pm (14:00) and before 4:00pm (16:00)
        totalPoints = totalPoints + calculateTimeOfPurchasePoints(receipt.getPurchaseTime());

        return totalPoints;
    }

    private int calculateRetailerNamePoint(String retailerName) {
        //1 point per alphanumeric character in retailer name
        Pattern retailerCharsForPoints = Pattern.compile("[a-zA-Z0-9]");
        Matcher matchingChars = retailerCharsForPoints.matcher(retailerName);

        int matchingCount = 0;
        while (matchingChars.find()) {
            matchingCount++;
        }

        return matchingCount;
    }

    private int calculateRoundDollarPoints(String total) {
        //50 points if total is round dollar amount
        if (total.endsWith(".00")) {
            return 50;
        }

        return 0;
    }

    private int calculateMultipleOf25Points(String total) {
        //25 points if total is multiple of 0.25
        if (Float.parseFloat(total) % 0.25 == 0) {
            return 25;
        }

        return 0;
    }

    private int calculateItemListPoints(List<ReceiptItem> items) {
        //5 points for every 2 items
        return (items.size() / 2) * 5;
    }

    private int calculateItemDescriptionPoints(List<ReceiptItem> items) {
        //Points for each item whose trimmed description is a multiple of 3,
        //where the points are calculated as the price multiplied by 0.2 rounded up
        int points = 0;
        for (ReceiptItem item : items) {
            if (item.getShortDescription().trim().length() % 3 == 0) {
                float price = Float.parseFloat(item.getPrice());
                points = points + (int) Math.ceil(price * 0.2);
            }
        }

        return points;
    }

    private int calculateOddDayPoints(String purchaseDate) {
        //6 points if day is odd
        if (Integer.parseInt(purchaseDate.split("-")[2]) % 2 == 1) {
            return 6;
        }

        return 0;
    }

    private int calculateTimeOfPurchasePoints(String purchaseTime) {
        //10 points for purchase after 2:00pm (14:00) and before 4:00pm (16:00)
        if (purchaseTime.startsWith("15:") ||
                (purchaseTime.startsWith("14:") && (!purchaseTime.endsWith(":00")))) {
            return 10;
        }

        return 0;
    }
}