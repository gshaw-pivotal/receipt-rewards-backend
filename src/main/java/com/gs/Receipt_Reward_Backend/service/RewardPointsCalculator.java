package com.gs.Receipt_Reward_Backend.service;

import com.gs.Receipt_Reward_Backend.model.Receipt;

public interface RewardPointsCalculator {

    int calculateRewardPoints(Receipt receipt);
}
