package net.queztech.noelytra;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityToggleGlideEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public class Events implements Listener {
    private NoElytra plugin;
    public Events(NoElytra noelytra) {
        this.plugin = noelytra;
    }

    @EventHandler
    public void onGlide(EntityToggleGlideEvent event) {
        if (event.getEntity().getType().equals(EntityType.PLAYER)) {
            dequipElytra((Player) event.getEntity());
        }
    }

    private void dequipElytra(Player player) {
        PlayerInventory i = player.getInventory();
        if (i.getChestplate().getType().equals(Material.ELYTRA)) {
            i.setChestplate(null);

            ItemStack elytra = new ItemStack(Material.ELYTRA, 1);
            Location       l = i.getLocation();

            // inventory full?
            if (i.firstEmpty() > 0) {
                i.addItem(elytra);
            } else {
                l.getWorld().dropItemNaturally(l, elytra);
            }
            player.updateInventory();
        }
    }
}
