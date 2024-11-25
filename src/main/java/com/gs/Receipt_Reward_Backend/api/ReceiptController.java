package com.gs.Receipt_Reward_Backend.api;

import com.gs.Receipt_Reward_Backend.model.Receipt;
import com.gs.Receipt_Reward_Backend.model.ReceiptIdResponse;
import com.gs.Receipt_Reward_Backend.model.ReceiptPointsResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ReceiptController {

    @PostMapping(
            value = "/receipts/process"
    )
    public ResponseEntity<ReceiptIdResponse> acceptReceipt(@RequestBody Receipt receipt) {
        return ResponseEntity.status(201).body(ReceiptIdResponse.builder().id("AAA-111-BBB-222").build());
    }

    @GetMapping(
            value = "/receipts/{id}/points"
    )
    public ResponseEntity<ReceiptPointsResponse> getPoints(@PathVariable int id) {
        return ResponseEntity.status(200).body(ReceiptPointsResponse.builder().points(12).build());
    }
}
