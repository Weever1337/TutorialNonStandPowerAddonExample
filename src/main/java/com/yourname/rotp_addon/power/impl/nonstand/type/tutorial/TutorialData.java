package com.yourname.rotp_addon.power.impl.nonstand.type.tutorial;

import com.github.standobyte.jojo.power.impl.nonstand.TypeSpecificData;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundNBT;

public class TutorialData extends TypeSpecificData {

    @Override
    public CompoundNBT writeNBT() {
        CompoundNBT nbt = new CompoundNBT();
        return nbt;
    }

    @Override
    public void readNBT(CompoundNBT nbt) {
    }

    @Override
    public void syncWithUserOnly(ServerPlayerEntity user) {
    }

    @Override
    public void syncWithTrackingOrUser(LivingEntity user, ServerPlayerEntity entity) {
    }
}
