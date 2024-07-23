package com.yourname.rotp_addon.action.non_stand;

import com.github.standobyte.jojo.action.Action;
import com.github.standobyte.jojo.action.ActionTarget;
import com.github.standobyte.jojo.entity.HamonSendoOverdriveEntity;
import com.github.standobyte.jojo.init.ModSounds;
import com.github.standobyte.jojo.power.impl.nonstand.INonStandPower;
import com.github.standobyte.jojo.util.general.ObjectWrapper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;

public class TutorialSendoOverdrive extends TutorialAction {
    public TutorialSendoOverdrive(Builder builder) {
        super(builder);
    }

    public void overrideVanillaMouseTarget(ObjectWrapper<ActionTarget> targetContainer, World world, LivingEntity user, INonStandPower power) {
        if (targetContainer.get().getType() == ActionTarget.TargetType.BLOCK) {
            Vector3d pos1 = user.getEyePosition(1.0F);
            Vector3d pos2 = pos1.add(user.getViewVector(1.0F).scale(Math.sqrt(this.getMaxRangeSqBlockTarget())));
            RayTraceResult targetCollisionBlocks = user.level.clip(new RayTraceContext(pos1, pos2, RayTraceContext.BlockMode.COLLIDER, RayTraceContext.FluidMode.NONE, user));
            targetContainer.set(ActionTarget.fromRayTraceResult(targetCollisionBlocks));
        }
    }

    protected void perform(World world, LivingEntity user, INonStandPower power, ActionTarget target) {
        if (!world.isClientSide()) {
            float energyCost = this.getEnergyCost(power, target);
            BlockPos blockPos = target.getBlockPos();
            HamonSendoOverdriveEntity sendoOverdrive = (new HamonSendoOverdriveEntity(world, user, target.getFace().getAxis()))
                    .setRadius(2)
                    .setWaveDamage(0.5F)
                    .setWavesCount(3)
                    .setStatPoints(Math.min(energyCost, power.getEnergy()));
            sendoOverdrive.moveTo(Vector3d.atCenterOf(blockPos).subtract(0.0, (double)sendoOverdrive.getDimensions(null).height * 0.5, 0.0));
            sendoOverdrive.setBlockTarget(target.getBlockPos(), target.getFace());
            world.addFreshEntity(sendoOverdrive);
        }
        if (world.isClientSide ()){
            user.playSound(ModSounds.HAMON_SPARKS_LONG.get(), 2.0F, 1.0F);
        }
    }

    public Action.TargetRequirement getTargetRequirement() {
        return Action.TargetRequirement.BLOCK;
    }
}
