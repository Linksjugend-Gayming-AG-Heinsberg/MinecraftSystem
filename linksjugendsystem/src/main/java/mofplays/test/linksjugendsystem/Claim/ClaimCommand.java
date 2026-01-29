package mofplays.test.linksjugendsystem.Claim;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

import java.lang.module.Configuration;

public class ClaimCommand implements CommandExecutor {
    private final Plugin plugin;

    public ClaimCommand(Plugin plugin) {
        this.plugin = plugin;
    }
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String @NotNull [] strings) {
        var message = strings[0];
        var config = plugin.getConfig();
        switch (message) {
            case "pos1":
                var player = (Player) commandSender;
                var loc = player.getLocation();
                var x = loc.getX();
                var y = loc.getY();
                var z = loc.getZ();
                var uuid = player.getUniqueId();
                var uuidString = uuid.toString();
                config.set("Claim." + uuidString + ".pos1.x", x);
                config.set("Claim." + uuidString + ".pos1.ganz", loc);
                config.set("Claim." + uuidString + ".pos1.z",z);
                config.set("Claim." + uuidString + ".pos1.y", y);
                plugin.saveConfig();
                break;
            case "pos2":
                var player2 = (Player) commandSender;
                var lo = player2.getLocation();
                var x2 = lo.getX();
                var y2 = lo.getY();
                var z2 = lo.getZ();
                var uuid2 = player2.getUniqueId();
                var uuid2string = uuid2.toString();
                config.set("Claim." + uuid2string + ".pos2.x", x2);
                config.set("Claim." + uuid2string + "pos2.y", y2);
                config.set("Claim." + uuid2string+ "pos2.z", z2);
                config.set("Claim." + uuid2string + "pos2.ganz", lo);
                plugin.saveConfig();
                break;
        }
        return false;
    }
    public boolean BlockIstinArea(Player player) {
        var config = plugin.getConfig();
        var uuid = player.getUniqueId();
        var uuidString = uuid.toString();
        var loc = player.getLocation();
        var x1 = config.getDouble("Claim." + uuidString + ".pos1.x");
        var z1 = config.getDouble("Claim." + uuidString + ".pos1.z");
        var x2 = config.getDouble("Claim." + uuidString + ".pos2.x");
        var z2 = config.getDouble("Claim." + uuidString + ".pos2.z");
        return loc.getBlockX() >= x1 && loc.getBlockX() <= x2 &&
                loc.getBlockZ() >= z1 && loc.getBlockZ() <= z2;
    }
}
