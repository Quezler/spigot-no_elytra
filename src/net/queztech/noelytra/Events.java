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
import org.bukkit.scheduler.BukkitRunnable;

public class Events implements Listener {
    private NoElytra plugin;
    Events(NoElytra noelytra) {
        this.plugin = noelytra;

        new BukkitRunnable(){
            public void run(){
                for(Player p : plugin.getServer().getOnlinePlayers())
                    dequipElytra(p);
            }
        }.runTaskTimer(plugin, 20 * 5, 20 * 5);
    }

    @EventHandler
    public void onGlide(EntityToggleGlideEvent event) {
        if (event.getEntity().getType().equals(EntityType.PLAYER))
            dequipElytra((Player) event.getEntity());
    }

    private void dequipElytra(Player player) {
        PlayerInventory i = player.getInventory();
        if (!i.getChestplate().getType().equals(Material.ELYTRA))
            return;
        
            i.setChestplate(null);

            ItemStack elytra = new ItemStack(Material.ELYTRA, 1);
            Location       l = i.getLocation();

            // inventory full?
            if (i.firstEmpty() != -1) {
                i.addItem(elytra);
            } else {
                l.getWorld().dropItemNaturally(l, elytra);
                player.updateInventory();
            }
    }
}
