package com.chaosblades;

import net.fabricmc.api.ModInitializer;

public class ChaosBlades implements ModInitializer {
    public static final String MOD_ID = "chaosblades";

    @Override
    public void onInitialize() {
        ModItems.register();
    }
}
