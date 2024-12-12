package com.gs.Receipt_Reward_Backend;

import com.gs.Receipt_Reward_Backend.database.InMemoryReceiptRewardRepository;
import com.gs.Receipt_Reward_Backend.database.ReceiptRewardRepository;
import com.gs.Receipt_Reward_Backend.service.ReceiptRewardService;
import com.gs.Receipt_Reward_Backend.service.DefaultPointsCalculator;
import com.gs.Receipt_Reward_Backend.service.RewardPointsCalculator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ReceiptRewardConfiguration {

    @Bean
    public ReceiptRewardService receiptRewardService(
            ReceiptRewardRepository receiptRewardRepository,
            RewardPointsCalculator rewardPointsCalculator
    ) {
        return new ReceiptRewardService(rewardPointsCalculator, receiptRewardRepository);
    }

    @Bean
    public RewardPointsCalculator rewardPointsCalculator() {
        return new DefaultPointsCalculator();
    }

    @Bean
    public ReceiptRewardRepository receiptRewardRepository() {
        return new InMemoryReceiptRewardRepository();
    }
}
