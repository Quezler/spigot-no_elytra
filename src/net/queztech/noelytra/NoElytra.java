package net.queztech.noelytra;

import org.bukkit.plugin.java.JavaPlugin;

public class NoElytra extends JavaPlugin {

    @Override
    public void onEnable(){
        //Fired when the server enables the plugin

        getLogger().info("See that bird? *rips wings off*");

        getServer().getPluginManager().registerEvents(new Events(this), this);
    }

}
