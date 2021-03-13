package net.tylers1066.ufc;

import net.tylers1066.ufc.listener.ChatListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class UltimateFactionsChat extends JavaPlugin {
    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new ChatListener(), this);
        // Plugin startup logic

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}