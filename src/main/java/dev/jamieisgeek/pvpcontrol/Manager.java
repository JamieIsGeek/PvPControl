package dev.jamieisgeek.pvpcontrol;

import org.bukkit.scheduler.BukkitTask;

import java.util.*;

public class Manager {
    private static Manager manager;
    private Map<UUID, Boolean> playerPvPStatus = new HashMap<>();
    private Map<UUID, BukkitTask> playerTimers = new HashMap<>();
    private List<UUID> playerInPvP = new ArrayList<>();

    public Manager() {
        manager = this;
    }

    public static Manager getManager() {
        return manager;
    }

    public boolean playerPvPStatus(UUID uuid) {
        return playerPvPStatus.getOrDefault(uuid, false);
    }

    public void setPlayerPvPStatus(UUID uuid, boolean status) {
        if(playerPvPStatus.containsKey(uuid)) {
            playerPvPStatus.remove(uuid);
        }

        playerPvPStatus.put(uuid, status);
    }

    public boolean playerInPvP(UUID uuid) {
        return playerInPvP.contains(uuid);
    }

    public void setPlayerInPvP(UUID uuid, boolean status) {
        if(status) {
            if(!playerInPvP.contains(uuid)) {
                playerInPvP.add(uuid);
            }

            if(!playerTimers.containsKey(uuid)) {
                playerTimers.put(uuid, new Runnable(this, uuid).runTaskLater(PvPControl.getPlugin(PvPControl.class), 20 * 10));
            } else {
                playerTimers.get(uuid).cancel();
                playerTimers.remove(uuid);
                playerTimers.put(uuid, new Runnable(this, uuid).runTaskLater(PvPControl.getPlugin(PvPControl.class), 20 * 10));
            }
        } else {
            playerInPvP.remove(uuid);

            if(playerTimers.containsKey(uuid)) {
                playerTimers.get(uuid).cancel();
                playerTimers.remove(uuid);
            }
        }
    }
}
