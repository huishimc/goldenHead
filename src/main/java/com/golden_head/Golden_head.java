package com.golden_head;

import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.plugin.java.JavaPlugin;


public final class Golden_head extends JavaPlugin {
    static Golden_head main;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        main = this;
        Bukkit.getConsoleSender().sendMessage("§f[§bHl§f] 启动成功");
        Bukkit.getPluginManager().registerEvents(new EventHl(), this);
        EnchantedGoldenApple.registerRecipes(this);
        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            Bukkit.getConsoleSender().sendMessage("§f[§bHl§f] 已连线 PlaceholderAPI");
        }




    }

    @Override
    public void onDisable() {
        Bukkit.removeRecipe(new NamespacedKey(this, "enchanted_golden_apple"));
        // Plugin shutdown logic
    }
}
