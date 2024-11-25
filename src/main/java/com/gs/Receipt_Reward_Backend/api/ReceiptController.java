package com.gs.Receipt_Reward_Backend.api;

import com.gs.Receipt_Reward_Backend.model.Receipt;
import com.gs.Receipt_Reward_Backend.model.ReceiptIdResponse;
import com.gs.Receipt_Reward_Backend.model.ReceiptPointsResponse;
import com.gs.Receipt_Reward_Backend.service.ReceiptRewardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ReceiptController {

    private final ReceiptRewardService receiptRewardService;

    public ReceiptController(ReceiptRewardService receiptRewardService) {
        this.receiptRewardService = receiptRewardService;
    }

    @PostMapping(
            value = "/receipts/process"
    )
    public ResponseEntity<ReceiptIdResponse> acceptReceipt(@RequestBody Receipt receipt) {
        return ResponseEntity
                .status(201)
                .body(ReceiptIdResponse.builder()
                        .id(receiptRewardService.acceptReceipt(receipt))
                        .build())
                ;
    }

    @GetMapping(
            value = "/receipts/{id}/points"
    )
    public ResponseEntity<ReceiptPointsResponse> getPoints(@PathVariable String id) {
        return ResponseEntity
                .status(200)
                .body(ReceiptPointsResponse.builder()
                        .points(receiptRewardService.getReceiptPoints(id))
                        .build()
                );
    }
}
