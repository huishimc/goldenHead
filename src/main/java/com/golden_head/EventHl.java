package com.golden_head;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class EventHl implements Listener {
    private static final double golden = Golden_head.main.getConfig().getDouble("death_module.cooling_time");
    private final HashMap<UUID, Long> cooldownMap = new HashMap<>();
    private static final long COOLDOWN = TimeUnit.MINUTES.toMillis((long) golden);

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player victim = event.getEntity();
        Player killer = victim.getKiller();

        if (killer == null || killer == victim) return;

        if (isOnCooldown(victim)) {
            String killerMsg = Golden_head.main.getConfig().getString("tip.killer1");
            if (killerMsg != null) {
                killerMsg = PlaceholderAPI.setPlaceholders(killer, killerMsg);
                killer.sendMessage(killerMsg);
            }
            return;
        }

        ItemStack skull = createPlayerSkull(victim);
        victim.getWorld().dropItemNaturally(victim.getLocation(), skull);
        cooldownMap.put(victim.getUniqueId(), System.currentTimeMillis());
        String killerMsg = Golden_head.main.getConfig().getString("tip.killer2");
        if (killerMsg != null) {
            killerMsg = PlaceholderAPI.setPlaceholders(killer, killerMsg);
            killerMsg = killerMsg.replace("<name>", victim.getName());
            killer.sendMessage(killerMsg);
        }



    }

    private boolean isOnCooldown(Player player) {
        Long lastDrop = cooldownMap.get(player.getUniqueId());
        return lastDrop != null && (System.currentTimeMillis() - lastDrop) < COOLDOWN;
    }

    private ItemStack createPlayerSkull(Player player) {
        ItemStack skull = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta meta = (SkullMeta) skull.getItemMeta();

        if (meta != null) {
            String killerMsg = Golden_head.main.getConfig().getString("headName");
            meta.setOwningPlayer(player); // 设置玩家皮肤
            if (killerMsg != null) {
                killerMsg = PlaceholderAPI.setPlaceholders(player, killerMsg);
                killerMsg = killerMsg.replace("<name>", player.getName());
                meta.setDisplayName(killerMsg);
            }
            skull.setItemMeta(meta);
        }
        return skull;
    }

}
