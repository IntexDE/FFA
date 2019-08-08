package de.intexde.ffa.commands;

import de.intexde.ffa.listener.PlayerDamageListener;
import de.intexde.ffa.main.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by IntexDE, 00:14 - 07.08.2019
 **/

public class Map implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command command, String string, String[] args) {
        if(command.getName().equalsIgnoreCase("map")) {
            sender.sendMessage(Main.prefix + "§6Derzeit wird auf der Map §e" + getMapName() + " §6von §e" + getMapBuilder() + " §6gespielt. Sie wurde am §e" + getMapReleaseDate() + " §6veröffentlicht.");
        }
        return false;
    }

    private static String map;
    private static String builder;
    private static String releaseDate;

    public static void readFile() {
        try {
            FileReader fileReader = new FileReader(Main.config);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            try {
                map = bufferedReader.readLine();
                builder = bufferedReader.readLine();
                releaseDate = bufferedReader.readLine();
                PlayerDamageListener.radius = Integer.parseInt(bufferedReader.readLine());

                bufferedReader.close();
                fileReader.close();
            } catch (IOException exception) {}

        } catch (FileNotFoundException exception) {}
    }

    public static String getMapName() {
        return map;
    }

    private String getMapBuilder() {
        return builder;
    }

    private String getMapReleaseDate() {
        return releaseDate;
    }
}
