package mofplays.test.linksjugendsystem.Claim;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

import javax.sound.midi.MidiFileFormat;
import java.lang.module.Configuration;
import java.util.UUID;

public class ClaimCommand implements CommandExecutor {
    private Plugin plugin;
    FileConfiguration config = plugin.getConfig();

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String @NotNull [] strings) {
        Player player = (Player) commandSender;
        UUID playerUUID = player.getUniqueId();
        String playerUUIDString = playerUUID.toString();
        String message = strings[0];
        boolean bereichbesetzt = false;
        switch (message) {
            case "pos1":
                Location neueLocation = player.getLocation();
                for (String fremdeUUID : config.getKeys(false)) {
                    double fremde1X = config.getDouble("Claim." + fremdeUUID + ".pos1.x");
                    double fremde1Y = config.getDouble("Claim." + fremdeUUID + ".pos1.y");
                    double fremde1Z = config.getDouble("Claim." + fremdeUUID + ".pos1.z");
                    double fremde2X = config.getDouble("Claim." + fremdeUUID + ".pos2.x");
                    double fremde2Y = config.getDouble("Claim." + fremdeUUID + ".pos2.y");
                    double fremde2Z = config.getDouble("Claim." + fremdeUUID + ".pos2.z");
                    if (neueLocation.getX() >= fremde1X && neueLocation.getX() <= fremde2X
                            && neueLocation.getZ() >= fremde1Z && neueLocation.getZ() <= fremde2Z) {
                        bereichbesetzt = true;
                    }

                }
                if (bereichbesetzt) break;
                double x = neueLocation.getX();
                double y = neueLocation.getY();
                double z = neueLocation.getZ();
                config.set("Claim." + playerUUIDString + ".pos1.x", x);
                config.set("Claim." + playerUUIDString + ".pos1.y", y);
                config.set("Claim." + playerUUIDString + ".pos1.z", z);
                plugin.saveConfig();
                break;
            case "pos2":
                Location pos2 = player.getLocation();
                for (String fremdeUUID : config.getKeys(false)) {
                    double fremde1X = config.getDouble("Claim." + fremdeUUID + ".pos1.x");
                    double fremde1Y = config.getDouble("Claim." + fremdeUUID + ".pos1.y");
                    double fremde1Z = config.getDouble("Claim." + fremdeUUID + ".pos1.z");
                    double fremde2X = config.getDouble("Claim." + fremdeUUID + ".pos2.x");
                    double fremde2Y = config.getDouble("Claim." + fremdeUUID + ".pos2.y");
                    double fremde2Z = config.getDouble("Claim." + fremdeUUID + ".pos2.z");
                    if (pos2.getX() >= fremde1X && pos2.getX() <= fremde2X
                            && pos2.getZ() >= fremde1Z && pos2.getZ() <= fremde2Z) {
                        bereichbesetzt = true;
                    }
                    if (bereichbesetzt) break;
                    double pos2X = pos2.getX();
                    double pos2Y = pos2.getY();
                    double pos2Z = pos2.getZ();
                    config.set("Claim." + playerUUIDString + ".pos2.x", pos2X);
                    config.set("Claim." + playerUUIDString + ".pos2.y", pos2Y);
                    config.set("Claim." + playerUUIDString + ".pos2.z", pos2Z);
                    plugin.saveConfig();
                }
                break;
        }
        return false;
    }
}
