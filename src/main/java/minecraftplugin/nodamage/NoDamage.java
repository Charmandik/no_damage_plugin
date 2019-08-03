package minecraftplugin.nodamage;

import org.bukkit.plugin.java.JavaPlugin;

import java.util.Vector;

public final class NoDamage extends JavaPlugin {

    Vector<String> playerList = new Vector<>();

    @Override
    public void onEnable() {
        getLogger().info("Your plugin has been enabled!");
        this.getServer().getPluginManager().registerEvents(new EventListener(), this);
        this.getCommand("settimeout").setExecutor(new CommandListener());
    }

    @Override
    public void onDisable() {
    }
}

