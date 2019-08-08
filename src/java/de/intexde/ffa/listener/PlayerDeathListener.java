package de.intexde.ffa.listener;

import de.intexde.ffa.main.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

/**
 * Created by IntexDE, 00:32 - 07.08.2019
 **/

public class PlayerDeathListener implements Listener {

    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();
        Player killer = event.getEntity().getKiller();

        player.spigot().respawn();
        player.sendMessage(Main.prefix + "§cDu wurdest von §e" + killer.getDisplayName() + "§c getötet!");
        addDeath(player);
        killer.sendMessage(Main.prefix + "§aDu hast §e" + player.getDisplayName() + "§a getötet!");
        addKill(killer);

        ScoreboardListener.updateScoreboard(player);
        ScoreboardListener.updateScoreboard(killer);

        event.setKeepInventory(true);
        event.setDeathMessage(null);
    }

    public void addDeath(Player player) {
        Main.getMySQL().update("UPDATE `ffa` SET `deaths` = `deaths` + 1 WHERE `uuid` = '" + player.getUniqueId() + "'");
    }

    public void addKill(Player player) {
        Main.getMySQL().update("UPDATE `ffa` SET `kills` = `kills` + 1 WHERE `uuid` = '" + player.getUniqueId() + "'");
    }
}
