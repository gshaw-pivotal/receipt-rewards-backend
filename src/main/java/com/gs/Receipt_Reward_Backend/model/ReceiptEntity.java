package com.gs.Receipt_Reward_Backend.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Builder
@Data
public class ReceiptEntity {

    private String retailer;

    private String purchaseDate;

    private String purchaseTime;

    private List<ReceiptItem> items;

    private String total;

    private int points;
}
