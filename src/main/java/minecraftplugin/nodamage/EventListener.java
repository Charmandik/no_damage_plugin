package minecraftplugin.nodamage;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.permissions.Permission;
import org.bukkit.plugin.RegisteredServiceProvider;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Vector;

import static org.bukkit.Bukkit.getServer;


public class EventListener implements Listener {

    private HashMap<String, Long> newPlayers = new HashMap<>();
    private Vector<String> oldPlayers;
    private final static String FILENAME = "oldPlayers.txt";
    {
        try {
            oldPlayers = initOldPlayersVector();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public EventListener() {
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        if(!oldPlayers.contains(event.getPlayer().getName()))
        if (!newPlayers.containsKey(event.getPlayer().getName())) {
            Date date = new Date();
            newPlayers.put(event.getPlayer().getName(), date.getTime());
        }
    }

    @EventHandler
    public void playerGotDamage(EntityDamageEvent event) {
        Entity entity = event.getEntity();
        Date currentTime = new Date();
        if (entity instanceof Player) {
            Player player = (Player) entity;
            if(!oldPlayers.contains(player.getName()))
            if (newPlayers.containsKey(player.getName())) {
                if (currentTime.getTime() - newPlayers.get(player.getName()) < GlobalVar.INSTANCE.getTimeout()) {
                    event.setCancelled(true);
                    event.setDamage(0);
                }
                else {
                    oldPlayers.add(player.getName());
                    addPlayerToFile();
                }
            }
        }
    }

    private void addPlayerToFile()
    {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(FILENAME));
            writer.write(oldPlayers.toString());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Vector<String> initOldPlayersVector()throws Exception
    {
        String data = new String(Files.readAllBytes(Paths.get(FILENAME)));
        String[] dataArray = data.split(" ");
        return new Vector<>(Arrays.asList(dataArray));
    }




}
