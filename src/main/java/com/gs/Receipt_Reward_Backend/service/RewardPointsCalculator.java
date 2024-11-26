package com.gs.Receipt_Reward_Backend.service;

import com.gs.Receipt_Reward_Backend.model.Receipt;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RewardPointsCalculator {

    public int calculateRewardPoints(Receipt receipt) {
        int totalPoints = 0;

        //1 point per alphanumeric character in retailer name
        Pattern retailerCharsForPoints = Pattern.compile("[a-zA-Z0-9]");
        Matcher matchingChars = retailerCharsForPoints.matcher(receipt.getRetailer());

        int matchingCount = 0;
        while (matchingChars.find()) {
            matchingCount++;
        }
        totalPoints = totalPoints + matchingCount;

        //50 points if total is round dollar amount
        if (receipt.getTotal().endsWith(".00")) {
            totalPoints = totalPoints + 50;
        }

        //25 points if total is multiple of 0.25
        if (Float.parseFloat(receipt.getTotal()) % 0.25 == 0) {
            totalPoints = totalPoints + 25;
        }

        //5 points for every 2 items
        totalPoints = totalPoints + (receipt.getItems().size() / 2) * 5;

        //Points for each item whose trimmed description is a multiple of 3,
        //where the points are calculated as the price multiplied by 0.2 rounded up
        for (int i = 0; i < receipt.getItems().size(); i++) {
            if (receipt.getItems().get(i).getShortDescription().trim().length() % 3 == 0) {
                float price = Float.parseFloat(receipt.getItems().get(i).getPrice());
                totalPoints = totalPoints + (int)Math.ceil(price * 0.2);
            }
        }

        //6 points if day is odd
        if (Integer.parseInt(receipt.getPurchaseDate().split("-")[2]) % 2 == 1) {
            totalPoints = totalPoints + 6;
        }

        //10 points for purchase after 2:00pm (14:00) and before 4:00pm (16:00)
        if (receipt.getPurchaseTime().startsWith("15:") ||
                (receipt.getPurchaseTime().startsWith("14:") && (!receipt.getPurchaseTime().endsWith(":00")))) {
            totalPoints = totalPoints + 10;
        }

        return totalPoints;
    }
}
