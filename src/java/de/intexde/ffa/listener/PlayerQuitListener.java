package de.intexde.ffa.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

/**
 * Created by IntexDE, 00:01 - 07.08.2019
 **/

public class PlayerQuitListener implements Listener {

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        try {
            ScoreboardListener.unregisterScoreboard(player);
        } catch (NullPointerException exception) {}

        event.setQuitMessage(null);
    }
}
