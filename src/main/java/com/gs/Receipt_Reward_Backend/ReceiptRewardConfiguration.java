package com.gs.Receipt_Reward_Backend;

import com.gs.Receipt_Reward_Backend.database.InMemoryReceiptRewardRepository;
import com.gs.Receipt_Reward_Backend.database.ReceiptRewardRepository;
import com.gs.Receipt_Reward_Backend.service.ReceiptRewardService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ReceiptRewardConfiguration {

    @Bean
    public ReceiptRewardService receiptRewardService(ReceiptRewardRepository receiptRewardRepository) {
        return new ReceiptRewardService(receiptRewardRepository);
    }

    @Bean
    public ReceiptRewardRepository receiptRewardRepository() {
        return new InMemoryReceiptRewardRepository();
    }
}
