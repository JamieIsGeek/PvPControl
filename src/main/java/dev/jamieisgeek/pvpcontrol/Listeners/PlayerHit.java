package dev.jamieisgeek.pvpcontrol.Listeners;

import dev.jamieisgeek.pvpcontrol.Manager;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class PlayerHit implements Listener {
    @EventHandler
    public void onPlayerHit(EntityDamageByEntityEvent event) {
        Manager manager = Manager.getManager();
        if (event.getDamager() instanceof Player attacker && event.getEntity() instanceof Player attacked) {
            if(!manager.playerPvPStatus(attacker.getUniqueId())) {
                attacker.sendMessage(ChatColor.RED + "You can't hit other players while you have PvP Disabled!");
                event.setCancelled(true);
                return;
            }

            if(!manager.playerPvPStatus(attacked.getUniqueId())) {
                attacker.sendMessage(ChatColor.RED + "You can't hit " + attacked.getName() + " while they have PvP Disabled!");
                event.setCancelled(true);
                return;
            }

            if(!manager.playerInPvP(attacker.getUniqueId())) {
                manager.setPlayerInPvP(attacker.getUniqueId(), true);
                manager.setPlayerInPvP(attacked.getUniqueId(), true);
            }
        }
    }
}
