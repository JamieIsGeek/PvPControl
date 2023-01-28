package dev.jamieisgeek.pvpcontrol;

import org.bukkit.scheduler.BukkitRunnable;

import java.util.UUID;

public class Runnable extends BukkitRunnable {
    private final Manager manager;
    private final UUID player;
    public Runnable(Manager manager, UUID player) {
        this.manager = manager;
        this.player = player;
    }

    @Override
    public void run() {
        if(!manager.playerInPvP(player)) return;

        manager.setPlayerInPvP(player, false);
    }
}
