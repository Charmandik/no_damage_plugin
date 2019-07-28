package minecraftplugin.nodamage;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import static org.bukkit.Bukkit.getLogger;

public class CommandListener implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        getLogger().info("trying to catch command");
        if (cmd.getName().equalsIgnoreCase("basic")) {
            getLogger().info("command listener");
        }
        return true;
    }
}
