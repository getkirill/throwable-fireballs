package com.kraskaska.minecraft.mods.throwablefireballs.mixin;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.FireballEntity;
import net.minecraft.item.FireChargeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.slf4j.Logger;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Item.class)
public class Item_FireChargeItemMixin {
    @Shadow @Final private static Logger LOGGER;

    /**
     * @author
     * @reason
     */
    @Inject(at = @At("HEAD"), method = "use", cancellable = true)
    public void use(World world, PlayerEntity user, Hand hand, CallbackInfoReturnable<TypedActionResult<ItemStack>> cir) {
        LOGGER.info(this.getClass().toString());
        if ((Object)this instanceof FireChargeItem) {
            LOGGER.info("Hooking firecharge use!");
            ItemStack itemStack = user.getStackInHand(hand);
            world.playSound((PlayerEntity) null, user.getX(), user.getY(), user.getZ(), SoundEvents.ENTITY_GHAST_SHOOT, SoundCategory.NEUTRAL, 0.5F, 0.4F / (world.getRandom().nextFloat() * 0.4F + 0.8F));
            if (!world.isClient) {
//            SnowballEntity snowballEntity = new SnowballEntity(world, user);
//            snowballEntity.setItem(itemStack);
//            snowballEntity.setVelocity(user, user.getPitch(), user.getYaw(), 0.0F, 1.5F, 1.0F);
//            world.spawnEntity(snowballEntity);
                Vec3d vec3d = user.getRotationVec(1.0F);
                FireballEntity fireballEntity = new FireballEntity(world, user, 0, 0, 0, 1);
                fireballEntity.setVelocity(user, user.getPitch(), user.getYaw(), 0.0F, 1.5F, 1.0F);
                fireballEntity.setPosition(user.getX() /*+ vec3d.x * 4*/, user.getBodyY(0.5) /*+ 0.5*/, user.getZ());
                world.spawnEntity(fireballEntity);
            }
            user.incrementStat(Stats.USED.getOrCreateStat((FireChargeItem) (Object) this));
            if (!user.getAbilities().creativeMode) {
                itemStack.decrement(1);
            }
            cir.setReturnValue(TypedActionResult.success(itemStack, world.isClient()));
        }
    }
}
