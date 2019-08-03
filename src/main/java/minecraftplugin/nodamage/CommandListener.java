package minecraftplugin.nodamage;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Set;

import static org.bukkit.Bukkit.getLogger;
import static org.bukkit.Bukkit.getServer;

public class CommandListener implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("settimeout")) {
            if(sender instanceof Player) {
                if(((Player) sender).getPlayer().isOp()){
                    GlobalVar.INSTANCE.setTimeout(args[0]);
                    getLogger().info(String.valueOf(GlobalVar.INSTANCE.getTimeout()));
                }
                else
                    ((Player) sender).getPlayer().sendMessage("You don't have permissions to do that");
            }
        }
        return true;
    }
}
