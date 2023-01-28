package dev.jamieisgeek.pvpcontrol;

import dev.jamieisgeek.pvpcontrol.Commands.PvPCommand;
import dev.jamieisgeek.pvpcontrol.Listeners.PlayerHit;
import org.bukkit.plugin.java.JavaPlugin;

public final class PvPControl extends JavaPlugin {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new PlayerHit(), this);
        this.getCommand("pvp").setExecutor(new PvPCommand());

        new Manager();

        this.getLogger().info("PvPControl has been enabled! (v" + this.getDescription().getVersion() + ")");
    }

    @Override
    public void onDisable() {
        this.getLogger().info("PvPControl has been disabled! (v" + this.getDescription().getVersion() + ")");
    }
}
