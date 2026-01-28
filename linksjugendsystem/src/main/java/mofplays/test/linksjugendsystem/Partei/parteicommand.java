package mofplays.test.linksjugendsystem.Partei;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

import java.lang.module.Configuration;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class parteicommand implements CommandExecutor {
    private Plugin plugin;
    @NotNull FileConfiguration config = plugin.getConfig();
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String @NotNull [] strings) {
        String message = strings[0];
        List<UUID> inviteListe = new ArrayList<>();
        List<UUID> inviteHauptUUID = new ArrayList<>();
        switch (message) {
            case "create":
                String parteiName = strings[1];
                String tag = strings[2];
                Player player = (Player) commandSender;
                UUID playerUUID = player.getUniqueId();
                config.set("Partei." + parteiName + ".tag", tag);
                config.set("Partei." + parteiName + ".owner", playerUUID);
                plugin.saveConfig();
                break;
            case "invite":
                String targetName = strings[1];
                Player target = Bukkit.getPlayer(targetName);
                Player player1 = (Player) commandSender;
                UUID player1UUID = player1.getUniqueId();
                if (target == null) break;
                UUID targetUUID = target.getUniqueId();
                inviteListe.add(targetUUID);
                inviteHauptUUID.add(player1UUID);
            case "accept":
                Player player2 = (Player) commandSender;
                UUID player2UUID = player2.getUniqueId();
                inviteListe.remove(player2UUID);
                List<UUID> Mitglieder = new ArrayList<>();
                Mitglieder.add(player2UUID);
                UUID hauptUUID = inviteHauptUUID.getFirst();
                String hauptUUIDString = hauptUUID.toString();
                for (String parteiname : config.getKeys(false)) {
                    UUID ownerUUID = (UUID) config.get("Partei." + parteiname + ".owner");
                    if (ownerUUID == null) break;
                    String ownerUUIDString = ownerUUID.toString();
                    if (ownerUUIDString.equalsIgnoreCase(hauptUUIDString)) {
                        config.set("Partei." + parteiname + ".Mitglieder", Mitglieder);
                        plugin.saveConfig();
                    }
                }
        }
        return false;
    }
}
