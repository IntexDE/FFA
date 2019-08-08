package de.intexde.ffa.listener;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

/**
 * Created by IntexDE, 00:47 - 07.08.2019
 **/

public class PlayerDropItemListener implements Listener {

    @EventHandler
    public void onDrop(PlayerDropItemEvent event) {
        Player player = event.getPlayer();

        if(player.getGameMode() != GameMode.CREATIVE) {
            event.setCancelled(true);
        }
    }
}
