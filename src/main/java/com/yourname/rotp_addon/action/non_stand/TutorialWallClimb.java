package com.yourname.rotp_addon.action.non_stand;

import com.github.standobyte.jojo.action.ActionConditionResult;
import com.github.standobyte.jojo.action.ActionTarget;
import com.github.standobyte.jojo.power.impl.nonstand.INonStandPower;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;

public class TutorialWallClimb extends TutorialAction {
    public TutorialWallClimb(Builder builder) {
        super(builder.holdType().needsFreeMainHand());
    }

    @Override
    protected ActionConditionResult checkSpecificConditions(LivingEntity user, INonStandPower power, ActionTarget target) {
        if (user.horizontalCollision){
            return ActionConditionResult.POSITIVE;
        }
        return ActionConditionResult.NEGATIVE;
    }

    @Override
    protected void holdTick(World world, LivingEntity user, INonStandPower power, int ticksHeld, ActionTarget target, boolean requirementsFulfilled) {
        if (world.isClientSide() && user.horizontalCollision) {
            Vector3d movement = user.getLookAngle();
            int final_energy = 4; // VAMPIRES WALL CLIMB ADDON REFERENCE??!!!💀💀😭😭😭
            user.setDeltaMovement(movement.x / final_energy, movement.y / final_energy, movement.z / final_energy);
        }
    }

    @Override public boolean isHeldSentToTracking() { return true; }
}
