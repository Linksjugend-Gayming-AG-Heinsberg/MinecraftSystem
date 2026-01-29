package mofplays.test.linksjugendsystem.Partei;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.inventory.AnvilInventory;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.net.http.WebSocket;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ParteiEvent implements Listener {
    Map<UUID, String> anvilText = new HashMap<>();
    @EventHandler
    public void onInventory(InventoryClickEvent event) {
        var inv = event.getView();
        var player = (Player) event.getWhoClicked();
        String title = event.getView().getTitle();
        if (title.equalsIgnoreCase("Partei")) {
            var clickItem = event.getCurrentItem();
            if (clickItem == null) return;
            var Meta = clickItem.getItemMeta();
            if (Meta.getDisplayName().equalsIgnoreCase("Click") && clickItem.getType() == Material.BOOK) {
                var inv2 = Bukkit.createInventory(null, 9, "Partei erstellen");
                player.openInventory(inv2);
            }
        }
    }
}
