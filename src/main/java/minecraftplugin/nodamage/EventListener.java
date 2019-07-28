package minecraftplugin.nodamage;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.Date;
import java.util.HashMap;


public class EventListener implements Listener {

    HashMap<String, Long> playerList = new HashMap<>();

    public EventListener() {
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        if (!playerList.containsKey(event.getPlayer().getName())) {
            Date date = new Date();
            playerList.put(event.getPlayer().getName(), date.getTime());
        }
    }

    @EventHandler
    public void playerGotDamage(EntityDamageEvent event) {
        Entity entity = event.getEntity();
        Date currentTime = new Date();
        if (entity instanceof Player) {
            Player player = (Player) entity;
            if (playerList.containsKey(player.getName())) {
                if (currentTime.getTime() - playerList.get(player.getName()) < 600000) {
                    event.setCancelled(true);
                    event.setDamage(0);
                }
            }
        }
    }
}
