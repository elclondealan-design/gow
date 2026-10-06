package com.chaosblades;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {
    public static final Item BLADES_OF_CHAOS = Registry.register(
            Registries.ITEM,
            new Identifier(ChaosBlades.MOD_ID, "blades_of_chaos"),
            new BladesOfChaosItem(new Item.Settings().maxCount(1)));

    public static void register() {
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT)
                .register(entries -> entries.add(BLADES_OF_CHAOS));
    }
}
