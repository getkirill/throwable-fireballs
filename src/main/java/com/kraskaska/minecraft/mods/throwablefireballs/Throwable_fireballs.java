package com.kraskaska.minecraft.mods.throwablefireballs;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Throwable_fireballs implements ModInitializer {
    public static Logger LOGGER = LoggerFactory.getLogger(Throwable_fireballs.class);
    @Override
    public void onInitialize() {
        LOGGER.info("ThrowableFireballs initializing!!!!");
    }
}
