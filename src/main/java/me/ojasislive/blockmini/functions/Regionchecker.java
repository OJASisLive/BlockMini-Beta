package me.ojasislive.blockmini.functions;

import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldguard.LocalPlayer;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.regions.RegionContainer;
import com.sk89q.worldguard.protection.regions.RegionQuery;
import me.ojasislive.blockmini.BlockMini;
import org.bukkit.entity.Player;

public class Regionchecker {

    public static boolean isInRegion(Player p){
        final com.sk89q.worldedit.util.Location loc = BukkitAdapter.adapt(p.getLocation());
        final RegionContainer container = WorldGuard.getInstance().getPlatform().getRegionContainer();
        final RegionQuery query = container.createQuery();
        final ApplicableRegionSet set = query.getApplicableRegions(loc);
        final LocalPlayer localPlayer = WorldGuardPlugin.inst().wrapPlayer(p);
        return set.testState(localPlayer, BlockMini.BLOCKDROP);
    }

}
