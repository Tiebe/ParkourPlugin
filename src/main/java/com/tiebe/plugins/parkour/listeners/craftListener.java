package com.tiebe.plugins.parkour.listeners;

import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.inventory.ItemStack;

public class craftListener implements Listener {
    @EventHandler
    public void onPlayerCraft(CraftItemEvent event) {
        for (HumanEntity entity : event.getViewers()) {
            if (entity instanceof Player) {
                Player player = (Player) entity;
                ItemStack[] item = event.getInventory().getMatrix();

                for(int i =0; i < 8; i++)
                {
                    //werkt nu nog niet, nullpointerexception
                    if(item[i].getItemMeta() != null) {
                        if (item[i].getItemMeta().getLore().contains("Use this item to teleport back to your checkpoint")) {
                            event.setCancelled(true);
                            player.sendMessage("Hey! You don't just get free blaze powder!");
                        }
                    }
                }
            }
        }
    }
}
