package com.tiebe.plugins.parkour;

import com.tiebe.plugins.parkour.listeners.craftListener;
import com.tiebe.plugins.parkour.listeners.signClickListener;
import org.bukkit.Location;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public final class Parkour extends JavaPlugin {
    public static Map<UUID, Location> UUIDPlayerArray = new HashMap<UUID, Location>();


    @Override
    public void onEnable() {
        // Plugin startup logic
        getServer().getPluginManager().registerEvents(new signClickListener(), this);
        //doet het nu nog niet
        //getServer().getPluginManager().registerEvents(new craftListener(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
