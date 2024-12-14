package com.yourname.rotp_addon.action.non_stand;

import com.github.standobyte.jojo.action.ActionConditionResult;
import com.github.standobyte.jojo.action.ActionTarget;
import com.github.standobyte.jojo.power.impl.nonstand.INonStandPower;
import com.github.standobyte.jojo.util.mc.MCUtil;
import com.github.standobyte.jojo.util.mod.JojoModUtil;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.ForgeHooks;

public class TutorialBarrage extends TutorialAction {
    public TutorialBarrage(Builder builder) {
        super(builder.holdType());
    } // Hamon Overdrive Barrage. xd

    @Override
    protected ActionConditionResult checkHeldItems(LivingEntity user, INonStandPower power) {
        if (!(MCUtil.isHandFree(user, Hand.MAIN_HAND) && MCUtil.isHandFree(user, Hand.OFF_HAND))) {
            return conditionMessage("hands");
        }
        return ActionConditionResult.POSITIVE;
    }

    @Override
    protected void holdTick(World world, LivingEntity user, INonStandPower power, int ticksHeld, ActionTarget target, boolean requirementsFulfilled) {
        if (requirementsFulfilled) {
            switch (target.getType()) {
                case BLOCK:
                    BlockPos pos = target.getBlockPos();
                    if (!world.isClientSide() && JojoModUtil.canEntityDestroy((ServerWorld) world, pos, world.getBlockState(pos), user)) {
                        if (!world.isEmptyBlock(pos)) {
                            BlockState blockState = world.getBlockState(pos);
                            float digDuration = blockState.getDestroySpeed(world, pos);
                            boolean dropItem = true;
                            if (user instanceof PlayerEntity) {
                                PlayerEntity player = (PlayerEntity) user;
                                digDuration /= player.getDigSpeed(blockState, pos);
                                if (player.abilities.instabuild) {
                                    digDuration = 0;
                                    dropItem = false;
                                } else if (!ForgeHooks.canHarvestBlock(blockState, player, world, pos)) {
                                    digDuration *= 10F / 3F;
                                }
                            }
                            if (digDuration >= 0 && digDuration <= 2.5F * Math.sqrt(user.getAttributeValue(Attributes.ATTACK_DAMAGE))) {
                                world.destroyBlock(pos, dropItem);
                            } else {
                                SoundType soundType = blockState.getSoundType(world, pos, user);
                                world.playSound(null, pos, soundType.getHitSound(), SoundCategory.BLOCKS, (soundType.getVolume() + 1.0F) / 8.0F, soundType.getPitch() * 0.5F);
                            }
                        }
                    }
                    break;
                case ENTITY:
                    Entity targetEntity = target.getEntity();
                    if (user instanceof PlayerEntity) {
                        int invulTicks = targetEntity.invulnerableTime;
                        ((PlayerEntity) user).attack(targetEntity);
                        targetEntity.invulnerableTime = invulTicks;
                        if (!world.isClientSide()) {
                            targetEntity.hurt(DamageSource.playerAttack((PlayerEntity) user), 1);
                        }
                    }
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    public void onHoldTickClientEffect(LivingEntity user, INonStandPower power, int ticksHeld, boolean requirementsFulfilled, boolean stateRefreshed) {
        if (requirementsFulfilled) {
            if (ticksHeld % 2 == 0) {
                user.swinging = false;
                user.swing(ticksHeld % 4 == 0 ? Hand.MAIN_HAND : Hand.OFF_HAND);
            }
        }
    }
}
