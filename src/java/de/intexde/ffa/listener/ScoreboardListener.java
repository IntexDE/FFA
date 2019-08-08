package de.intexde.ffa.listener;

import de.intexde.ffa.commands.Map;
import de.intexde.ffa.main.Main;
import netherboard.Netherboard;
import netherboard.bukkit.BPlayerBoard;
import org.bukkit.entity.Player;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by IntexDE, 00:36 - 07.08.2019
 **/

public class ScoreboardListener {

    private static BPlayerBoard board;
    private static int result;

    public static void createScoreboard(Player player) {
        board = Netherboard.instance().createBoard(player, "FFA");
        board.setName("§f§lFFA HARDCORE");
        board.set("§a", 8);
        board.set("§fKills: ", 7);
        board.set("§e" + getKills(player), 6);
        board.set("§b", 5);
        board.set("§fMap: ", 4);
        board.set("§d" + Map.getMapName(), 3);
        board.set("§c", 2);
        board.set("§fDeaths ", 1);
        board.set("§c" + getDeaths(player), 0);

        player.setScoreboard(board.getScoreboard());
    }

    public static void unregisterScoreboard(Player player) {
        board = Netherboard.instance().getBoard(player);
        board.delete();
    }

    public static void updateScoreboard(Player player) {
        board = Netherboard.instance().getBoard(player);
        board.set("§a", 8);
        board.set("§fKills: ", 7);
        board.set("§e" + getKills(player), 6);
        board.set("§b", 5);
        board.set("§fMap: ", 4);
        board.set("§d" + Map.getMapName(), 3);
        board.set("§c", 2);
        board.set("§fDeaths ", 1);
        board.set("§c" + getDeaths(player), 0);

        player.setScoreboard(board.getScoreboard());
    }

    public static int getKills(Player player) {
        ResultSet resultSet = Main.getMySQL().query("SELECT `kills` FROM `ffa` WHERE `uuid` = '" + player.getUniqueId() + "'");
        try {
            while(resultSet.next()) {
                result = resultSet.getInt("kills");
                return result;
            }
        } catch(SQLException exception) {

        }
        return result;
    }

    public static int getDeaths(Player player) {
        ResultSet resultSet = Main.getMySQL().query("SELECT `deaths` FROM `ffa` WHERE `uuid` = '" + player.getUniqueId() + "'");
        try {
            while(resultSet.next()) {
                result = resultSet.getInt("deaths");
                return result;
            }
        } catch(SQLException exception) {

        }
        return result;
    }
}
