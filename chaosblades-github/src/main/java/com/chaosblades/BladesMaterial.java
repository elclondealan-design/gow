package com.chaosblades;

import net.minecraft.item.Items;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;

public class BladesMaterial implements ToolMaterial {
    public static final BladesMaterial INSTANCE = new BladesMaterial();

    @Override public int getDurability() { return 2500; }
    @Override public float getMiningSpeedMultiplier() { return 9.0f; }
    // Daño total = 1 (base) + 3 (material) + 5 (item) = 9 corazones/2
    @Override public float getAttackDamage() { return 3.0f; }
    @Override public int getMiningLevel() { return 4; }
    @Override public int getEnchantability() { return 15; }
    @Override public Ingredient getRepairIngredient() {
        return Ingredient.ofItems(Items.NETHERITE_INGOT);
    }
}
