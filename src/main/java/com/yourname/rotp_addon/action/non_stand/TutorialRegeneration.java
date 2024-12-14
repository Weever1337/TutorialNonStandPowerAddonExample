package com.yourname.rotp_addon.action.non_stand;

import com.github.standobyte.jojo.action.ActionConditionResult;
import com.github.standobyte.jojo.action.ActionTarget;
import com.github.standobyte.jojo.power.impl.nonstand.INonStandPower;
import net.minecraft.entity.LivingEntity;
import net.minecraft.world.World;

public class TutorialRegeneration extends TutorialAction {
    public TutorialRegeneration(Builder builder) {
        super(builder.holdType().needsFreeMainHand());
    }

    @Override
    protected ActionConditionResult checkSpecificConditions(LivingEntity user, INonStandPower power, ActionTarget target) {
        if (user.getHealth() > 0 && power.getEnergy() >= 10 && user.getHealth() < user.getMaxHealth()) {
            return ActionConditionResult.POSITIVE;
        }
        return ActionConditionResult.NEGATIVE;
    }

    @Override
    protected void holdTick(World world, LivingEntity user, INonStandPower power, int ticksHeld, ActionTarget target, boolean requirementsFulfilled) {
        if (!world.isClientSide()) {
            user.setHealth(user.getHealth() + 0.1f);
        }
    }
}
