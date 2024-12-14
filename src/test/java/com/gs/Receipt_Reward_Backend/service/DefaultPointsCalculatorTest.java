package com.gs.Receipt_Reward_Backend.service;

import com.gs.Receipt_Reward_Backend.model.Receipt;
import com.gs.Receipt_Reward_Backend.model.ReceiptItem;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

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

    @Test
    void totalItemCountPoints() {
        int points;

        points = calculator.calculateRewardPoints(Receipt.builder().items(null).build());
        assertEquals(0, points);

        points = calculator.calculateRewardPoints(Receipt.builder().items(Collections.emptyList()).build());
        assertEquals(0, points);

        points = calculator.calculateRewardPoints(Receipt.builder()
                .items(List.of(ReceiptItem.builder().shortDescription("A").build()))
                .build());
        assertEquals(0, points);

        points = calculator.calculateRewardPoints(Receipt.builder()
                .items(List.of(
                        ReceiptItem.builder().shortDescription("A").build(),
                        ReceiptItem.builder().shortDescription("B").build())
                )
                .build());
        assertEquals(5, points);

        points = calculator.calculateRewardPoints(Receipt.builder()
                .items(List.of(
                        ReceiptItem.builder().shortDescription("A").build(),
                        ReceiptItem.builder().shortDescription("B").build(),
                        ReceiptItem.builder().shortDescription("C").build(),
                        ReceiptItem.builder().shortDescription("D").build())
                )
                .build());
        assertEquals(10, points);
    }

    @Test
    void itemDescriptionPoints() {
        int points;

        points = calculator.calculateRewardPoints(Receipt.builder()
                .items(List.of(ReceiptItem.builder().shortDescription("A").price("1.23").build()))
                .build());
        assertEquals(0, points);

        points = calculator.calculateRewardPoints(Receipt.builder()
                .items(List.of(ReceiptItem.builder().shortDescription("ABCD").price("4.56").build()))
                .build());
        assertEquals(0, points);

        points = calculator.calculateRewardPoints(Receipt.builder()
                .items(List.of(ReceiptItem.builder().shortDescription("ABC").price("1.00").build()))
                .build());
        assertEquals(1, points);

        points = calculator.calculateRewardPoints(Receipt.builder()
                .items(List.of(ReceiptItem.builder().shortDescription("ABC").price("2.00").build()))
                .build());
        assertEquals(1, points);

        points = calculator.calculateRewardPoints(Receipt.builder()
                .items(List.of(ReceiptItem.builder().shortDescription("ABC").price("10.00").build()))
                .build());
        assertEquals(2, points);

        points = calculator.calculateRewardPoints(Receipt.builder()
                .items(List.of(ReceiptItem.builder().shortDescription("ABC").price("12.23").build()))
                .build());
        assertEquals(3, points);
    }
}