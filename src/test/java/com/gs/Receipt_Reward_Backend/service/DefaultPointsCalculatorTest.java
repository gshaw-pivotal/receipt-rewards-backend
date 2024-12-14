package com.gs.Receipt_Reward_Backend.service;

import com.gs.Receipt_Reward_Backend.model.Receipt;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DefaultPointsCalculatorTest {

    private final DefaultPointsCalculator calculator = new DefaultPointsCalculator();

    @Test
    void emptyReceiptGivesZeroPoints() {
        int points = calculator.calculateRewardPoints(Receipt.builder().build());

        assertEquals(0, points);
    }

    @Test
    void retailerNamePoints() {
        int points;

        points = calculator.calculateRewardPoints(Receipt.builder().retailer(null).build());
        assertEquals(0, points);

        points = calculator.calculateRewardPoints(Receipt.builder().retailer("").build());
        assertEquals(0, points);

        points = calculator.calculateRewardPoints(Receipt.builder().retailer(" ").build());
        assertEquals(0, points);

        points = calculator.calculateRewardPoints(Receipt.builder().retailer("a").build());
        assertEquals(1, points);

        points = calculator.calculateRewardPoints(Receipt.builder().retailer("A").build());
        assertEquals(1, points);

        points = calculator.calculateRewardPoints(Receipt.builder().retailer("1").build());
        assertEquals(1, points);

        points = calculator.calculateRewardPoints(Receipt.builder().retailer("!@#$%").build());
        assertEquals(0, points);

        points = calculator.calculateRewardPoints(Receipt.builder().retailer(" Target ").build());
        assertEquals(6, points);
    }

    @Test
    void totalAmountPoints() {
        int points;

        points = calculator.calculateRewardPoints(Receipt.builder().total(null).build());
        assertEquals(0, points);

        points = calculator.calculateRewardPoints(Receipt.builder().total("").build());
        assertEquals(0, points);

        points = calculator.calculateRewardPoints(Receipt.builder().total("1.23").build());
        assertEquals(0, points);

        points = calculator.calculateRewardPoints(Receipt.builder().total("10.00").build());
        assertEquals(75, points);

        points = calculator.calculateRewardPoints(Receipt.builder().total("11.50").build());
        assertEquals(25, points);

        points = calculator.calculateRewardPoints(Receipt.builder().total("1.25").build());
        assertEquals(25, points);

        points = calculator.calculateRewardPoints(Receipt.builder().total("1.75").build());
        assertEquals(25, points);
    }
}