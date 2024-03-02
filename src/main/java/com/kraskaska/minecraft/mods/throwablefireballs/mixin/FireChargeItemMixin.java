package com.kraskaska.minecraft.mods.throwablefireballs.mixin;

import net.minecraft.item.FireChargeItem;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.util.ActionResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static com.kraskaska.minecraft.mods.throwablefireballs.Throwable_fireballs.LOGGER;

@Mixin(FireChargeItem.class)
public class FireChargeItemMixin {
    @Inject(at = @At("HEAD"), method = "useOnBlock", cancellable = true)
    public void useOnBlock(ItemUsageContext context, CallbackInfoReturnable<ActionResult> cir) {
        LOGGER.info("Hooking firecharge use on block!");
        cir.setReturnValue(ActionResult.PASS);
    }
}
