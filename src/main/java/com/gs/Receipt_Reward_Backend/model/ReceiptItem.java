package com.gs.Receipt_Reward_Backend.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Builder
@Data
public class ReceiptItem {

    private String shortDescription;

    private String price;
}