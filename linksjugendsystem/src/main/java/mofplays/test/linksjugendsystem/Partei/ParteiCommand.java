package mofplays.test.linksjugendsystem.Partei;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Item;
import org.bukkit.entity.ItemDisplay;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

import javax.accessibility.AccessibleRelation;

public class ParteiCommand implements CommandExecutor {
    private Plugin plugin;
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String @NotNull [] strings) {
        var config = plugin.getConfig();
        var message = strings[0];
        switch (message) {
            case "create":
                var player = (Player) commandSender;
                var uuid = player.getUniqueId();
                var name = strings[2];
                config.set("Partei." + name + ".owner", uuid);
                break;
            case "open":
                var player2 = (Player) commandSender;
                var inv = Bukkit.createInventory(null, 9, "Partei");
                player2.openInventory(inv);
        }
        return false;
    }
}
