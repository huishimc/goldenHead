package com.golden_head;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.plugin.java.JavaPlugin;


public class EnchantedGoldenApple {
    public static void registerRecipes(JavaPlugin plugin) {
        registerEnchantedAppleRecipe(plugin);
    }

    public static void registerEnchantedAppleRecipe(JavaPlugin plugin) {
        boolean compound = Golden_head.main.getConfig().getBoolean("death_module.compound");

        if (compound) {
            NamespacedKey key = new NamespacedKey(plugin, "enchanted_golden_apple");
            ShapedRecipe recipe = new ShapedRecipe(key, new ItemStack(Material.ENCHANTED_GOLDEN_APPLE));

            recipe.shape("GGG", "GHG", "GGG");
            recipe.setIngredient('G', Material.GOLD_INGOT);
            recipe.setIngredient('H', Material.PLAYER_HEAD);
            Bukkit.addRecipe(recipe);
        }

    }
}
