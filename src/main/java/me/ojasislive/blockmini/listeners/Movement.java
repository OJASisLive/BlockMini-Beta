package me.ojasislive.blockmini.listeners;

import me.ojasislive.blockmini.BlockMini;
import me.ojasislive.blockmini.functions.Regionchecker;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Movement implements Listener {

    List<Material> ignored = new ArrayList<>(Arrays.asList(Material.STONE, Material.GRASS_BLOCK, Material.COBBLESTONE,
            Material.BEDROCK, Material.BARRIER,Material.LIGHT,Material.WATER));

    private final BlockMini plugin;

    public Movement(BlockMini plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public  void onPlayerMove(PlayerMoveEvent event){
        if(!(event.getTo() == null)) {
            if (event.getFrom().getBlock().equals(event.getTo().getBlock())) return;

            Player p = event.getPlayer();

            Location loca = p.getLocation();

            Block b = loca.getBlock().getRelative(BlockFace.DOWN);

            if(Regionchecker.isInRegion(p)) {

                if(ignored.contains(b.getType())) return;

                b.setType(Material.YELLOW_CONCRETE);
                Bukkit.getScheduler().runTaskLater(plugin, () -> {
                    b.setType(Material.ORANGE_CONCRETE);
                    Bukkit.getScheduler().runTaskLater(plugin, () -> b.setType(Material.RED_CONCRETE), 20L);
                    Bukkit.getScheduler().runTaskLater(plugin, () -> b.setType(Material.AIR), 30L);
                }, 20L);

            }

        }

    }

}
