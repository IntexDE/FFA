package de.intexde.ffa.listener;

import de.intexde.ffa.main.Main;
import de.intexde.ffa.utilities.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *  Created by IntexDE, 23:59 - 06.08.2019
 **/

public class PlayerJoinListener implements Listener {

    private String uuid;

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        try {
            checkData(player);
        } catch (NullPointerException exception) {}
        ScoreboardListener.createScoreboard(player);
        equipPlayer(player);

        player.sendMessage(Main.prefix + "§aTeams in beliebiger Größe sind auf diesem Server §eERLAUBT§a!");
        player.teleport(player.getWorld().getSpawnLocation());

        event.setJoinMessage(null);
    }

    private void checkData(Player player) {
        try {
            ResultSet rs = Main.getMySQL().query("SELECT `uuid` FROM `ffa` WHERE `uuid` = '" + player.getUniqueId() + "'");
            while (rs.next()) {
                uuid = rs.getString("uuid");
            }
        } catch (SQLException exception) {

        }
        if(uuid == null) {
            Main.getMySQL().update("INSERT INTO `ffa`(`uuid`, `kills`, `deaths`) VALUES ('" + player.getUniqueId() + "',0,0)");
        }
    }

    private void equipPlayer(Player player) {
        Inventory inventory = player.getInventory();
        inventory.clear();

        player.setSaturation(20);
        player.setHealth(20);

        inventory.setItem(0, new ItemBuilder(Material.DIAMOND_SWORD, 1).setUnbreakable(false).build());
        inventory.setItem(8, new ItemBuilder(Material.GOLDEN_APPLE, 10).build());
        inventory.setItem(39, new ItemBuilder(Material.DIAMOND_HELMET, 1).setUnbreakable(false).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3).build());
        inventory.setItem(38, new ItemBuilder(Material.DIAMOND_CHESTPLATE, 1).setUnbreakable(false).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3).build());
        inventory.setItem(37, new ItemBuilder(Material.DIAMOND_LEGGINGS, 1).setUnbreakable(false).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3).build());
        inventory.setItem(36, new ItemBuilder(Material.DIAMOND_BOOTS, 1).setUnbreakable(false).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3).build());
    }

}
