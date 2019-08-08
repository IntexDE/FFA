package de.intexde.ffa.main;

import de.intexde.ffa.commands.Map;
import de.intexde.ffa.listener.*;
import de.intexde.ffa.utilities.MySQL;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.*;

public class Main extends JavaPlugin {

    private static final MySQL MY_SQL = new MySQL("Host", "Database", "User", "Password");
    public static final String prefix = "§7[§dFFA§7]§r ";

    public static File directory = new File(System.getProperty("user.dir") + "/plugins/FFA");
    public static File config = new File(System.getProperty("user.dir") + "/plugins/FFA/config.yml");

    @Override
    public void onEnable() {
        try {
            MY_SQL.update("CREATE TABLE IF NOT EXISTS ffa(uuid TINYTEXT, kills INT, deaths INT)");
        } catch (NullPointerException exception) {
            this.getLogger().warning("Failed while connecting to database!");
        }
        this.getCommand("map").setExecutor(new Map());
        PluginManager pluginManager = Bukkit.getPluginManager();
        pluginManager.registerEvents(new PlayerDamageListener(), this);
        pluginManager.registerEvents(new PlayerDeathListener(), this);
        pluginManager.registerEvents(new PlayerDropItemListener(), this);
        pluginManager.registerEvents(new PlayerJoinListener(), this);
        pluginManager.registerEvents(new PlayerQuitListener(), this);
        pluginManager.registerEvents(new PlayerFoodLevelChangeListener(), this);

        createFile();
        Map.readFile();
    }


    @Override
    public void onDisable() {

    }

    public static MySQL getMySQL() {
        return MY_SQL;
    }

    private void createFile() {
        try {
            if(directory.mkdirs()) {}
            if (config.createNewFile()) {
                FileWriter fw = new FileWriter(config);
                fw.write("Map\n");
                fw.write("Builder\n");
                fw.write("Date\n");
                fw.write("Radius");
                fw.close();
                this.getLogger().info("File created!");
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}
