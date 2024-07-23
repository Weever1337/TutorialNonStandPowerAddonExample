package com.yourname.rotp_addon.power.impl.nonstand.type.tutorial;

import com.github.standobyte.jojo.client.particle.custom.CustomParticlesHelper;
import com.github.standobyte.jojo.client.sound.ClientTickingSoundsHelper;
import com.github.standobyte.jojo.init.ModParticles;
import com.github.standobyte.jojo.network.PacketManager;
import com.github.standobyte.jojo.network.packets.fromserver.TrHamonParticlesPacket;
import net.minecraft.entity.Entity;
import net.minecraft.particles.IParticleData;
import net.minecraft.world.World;

public class TutorialUtil {
    public static void createSparkParticlesEmitter(Entity entity, float intensity, float soundVolumeMultiplier, IParticleData article) {
        if (intensity > 0) {
            intensity = Math.min(intensity, 4F);
            World world = entity.level;
            if (!world.isClientSide()) {
                PacketManager.sendToClientsTrackingAndSelf(TrHamonParticlesPacket.emitter(entity.getId(), intensity, soundVolumeMultiplier,
                        article != ModParticles.HAMON_SPARK_SILVER.get() ? article : null), entity);
            }
            else {
                float volume = intensity * 2 * soundVolumeMultiplier;
                for (int i = (int) (intensity * 9.5F); i >= 0; i--) {
                    CustomParticlesHelper.createParticlesEmitter(entity, article, Math.max(1, (int) (intensity * 9.5) - i));
                    if (i % 2 == 0 && i < 4) {
                        ClientTickingSoundsHelper.playHamonSparksSound(entity, Math.min(volume, 1.0F), 1.0F + (world.random.nextFloat() - 0.5F) * 0.15F);
                    }
                }
            }
        }
    }
}
