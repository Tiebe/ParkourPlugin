package com.tiebe.plugins.parkour.listeners;

import com.tiebe.plugins.parkour.Parkour;
import com.tiebe.plugins.parkour.utils.DirectionHelper;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.block.data.type.WallSign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class signClickListener implements Listener {
    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        Block block = event.getClickedBlock();
        if (block != null) {
            if (block.getState() instanceof WallSign || block.getState() instanceof Sign) {
                Sign sign = (Sign) block.getState();
                player.sendMessage(sign.getLine(0));
                player.sendMessage(sign.getLine(1));
                if (sign.getLine(0).equals("[Parkour]") && sign.getLine(1).equals("Set Checkpoint")) {
                    Location newPlayerLocation;
                    Location location = sign.getLocation();
                    if (DirectionHelper.getCardinalDirection(sign) == DirectionHelper.Direction.NORTH) {
                        newPlayerLocation = sign.getLocation();
                        newPlayerLocation.setZ(sign.getZ() - 1);
                    } else if (DirectionHelper.getCardinalDirection(sign) == DirectionHelper.Direction.EAST) {
                        newPlayerLocation = sign.getLocation();
                        newPlayerLocation.setX(sign.getX() - 1);
                    } else if (DirectionHelper.getCardinalDirection(sign) == DirectionHelper.Direction.SOUTH) {
                        newPlayerLocation = sign.getLocation();
                        newPlayerLocation.setZ(sign.getZ() + 1);
                    } else if (DirectionHelper.getCardinalDirection(sign) == DirectionHelper.Direction.WEST) {
                        newPlayerLocation = sign.getLocation();
                        newPlayerLocation.setZ(sign.getZ() + 1);
                    } else {
                        newPlayerLocation = sign.getLocation();
                    }
                    ItemStack specialItem = new ItemStack(Material.BLAZE_ROD);
                    ItemMeta specialItemMeta = specialItem.getItemMeta();
                    specialItemMeta.setDisplayName("Teleporter");
                    List<String> itemLore = Collections.singletonList("Use this item to teleport back to your checkpoint");
                    specialItemMeta.setLore(itemLore);
                    specialItem.setItemMeta(specialItemMeta);
                    if (!player.getInventory().contains(specialItem)) {
                        player.getInventory().addItem(specialItem);
                    }
                    Parkour.UUIDPlayerArray.remove(player.getUniqueId());
                    Parkour.UUIDPlayerArray.put(player.getUniqueId(), newPlayerLocation);
                }
            }
            else {
                try {
                    if (Objects.requireNonNull(Objects.requireNonNull(event.getItem()).getLore()).get(0).equals("Use this item to teleport back to your checkpoint")) {
                        player.teleport(Parkour.UUIDPlayerArray.get(player.getUniqueId()));
                        event.setCancelled(true);
                    }
                } catch (NullPointerException ignored) {}
            }
        }
        else {
            try {
                if (Objects.requireNonNull(Objects.requireNonNull(event.getItem()).getLore()).get(0).equals("Use this item to teleport back to your checkpoint")) {
                    player.teleport(Parkour.UUIDPlayerArray.get(player.getUniqueId()));
                    event.setCancelled(true);
                }
            } catch (NullPointerException ignored) {}
        }
    }
}
