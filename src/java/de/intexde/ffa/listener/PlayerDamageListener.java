package de.intexde.ffa.listener;

import de.intexde.ffa.main.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

/**
 * Created by IntexDE, 00:26 - 07.08.2019
 **/

public class PlayerDamageListener implements Listener {

    public static int radius;

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent event) {
        Player player = (Player) event.getEntity();
        Player damager = (Player) event.getDamager();

        if(player.getLocation().distanceSquared(player.getWorld().getSpawnLocation()) <= 10 ) {
            event.setCancelled(true);
            damager.sendMessage(Main.prefix + "§cDu kannst innerhalb des Spawn-Bereiches keine Gegner angreifen.");
        }

    }
}
