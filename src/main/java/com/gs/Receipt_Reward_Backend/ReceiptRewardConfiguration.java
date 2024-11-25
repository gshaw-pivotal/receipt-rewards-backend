package com.gs.Receipt_Reward_Backend;

import com.gs.Receipt_Reward_Backend.service.ReceiptRewardService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ReceiptRewardConfiguration {

    @Bean
    public ReceiptRewardService receiptRewardService() {
        return new ReceiptRewardService();
    }
}
