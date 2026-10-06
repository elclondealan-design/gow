package com.chaosblades;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class BladesOfChaosItem extends SwordItem {
    private static final int COOLDOWN_TICKS = 40; // 2 segundos
    private static final int FIRE_SECONDS = 6;

    public BladesOfChaosItem(Settings settings) {
        super(BladesMaterial.INSTANCE, 5, -2.0f, settings.fireproof());
    }

    /** Cada golpe prende fuego al enemigo. */
    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        target.setOnFireFor(FIRE_SECONDS);
        if (attacker.getWorld() instanceof ServerWorld sw) {
            sw.spawnParticles(ParticleTypes.FLAME,
                    target.getX(), target.getBodyY(0.5), target.getZ(),
                    12, 0.3, 0.4, 0.3, 0.02);
        }
        return super.postHit(stack, target, attacker);
    }

    /** Click derecho: impulso hacia adelante (como lanzarse con las cadenas). */
    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);

        if (user.getItemCooldownManager().isCoolingDown(this)) {
            return TypedActionResult.fail(stack);
        }

        Vec3d look = user.getRotationVec(1.0f);
        user.addVelocity(look.x * 1.4, 0.35, look.z * 1.4);
        user.velocityModified = true;
        user.fallDistance = 0;
        user.getItemCooldownManager().set(this, COOLDOWN_TICKS);

        if (!world.isClient) {
            world.playSound(null, user.getX(), user.getY(), user.getZ(),
                    SoundEvents.ENTITY_BLAZE_SHOOT, SoundCategory.PLAYERS, 1.0f, 0.8f);
            if (world instanceof ServerWorld sw) {
                sw.spawnParticles(ParticleTypes.FLAME,
                        user.getX(), user.getBodyY(0.5), user.getZ(),
                        25, 0.4, 0.3, 0.4, 0.05);
            }
            stack.damage(2, user, p -> p.sendToolBreakStatus(hand));
        }
        return TypedActionResult.success(stack, world.isClient());
    }
}
