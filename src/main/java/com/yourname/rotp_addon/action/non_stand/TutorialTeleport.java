package com.yourname.rotp_addon.action.non_stand;

import com.github.standobyte.jojo.action.ActionTarget;
import com.github.standobyte.jojo.init.ModParticles;
import com.github.standobyte.jojo.power.impl.nonstand.INonStandPower;
import com.github.standobyte.jojo.util.general.GeneralUtil;
import com.github.standobyte.jojo.util.mod.JojoModUtil;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class TutorialTeleport extends TutorialAction {
    public TutorialTeleport(Builder builder) {
        super(builder);
    }

    @Override
    protected void perform(World world, LivingEntity user, INonStandPower power, ActionTarget target) {
        if (!world.isClientSide()) {
            RayTraceResult rayTrace = JojoModUtil.rayTrace(user.getEyePosition(1.0F), user.getLookAngle(), 12,
                    world, user, e -> !e.is(user), 0, 0);
            user.teleportTo(rayTrace.getLocation().x, rayTrace.getLocation().y, rayTrace.getLocation().z);
        }
        if (world.isClientSide()){
            user.playSound(SoundEvents.ENDERMAN_TELEPORT, 2.0F, 1.0F); // Thank you, TiltoZavrium.
            GeneralUtil.doFractionTimes(() -> {
                world.addAlwaysVisibleParticle(
                        ModParticles.HAMON_SPARK_SILVER.get(), true,
                        user.getRandomX(1),
                        user.getRandomY(),
                        user.getRandomZ(1),
                        (user.getRandom().nextDouble() - 0.5D) * 2.0D,
                        -user.getRandom().nextDouble(),
                        (user.getRandom().nextDouble() - 0.5D) * 2.0D
                );
            }, 32);
        }
    }
}
