package me.ojasislive.blockmini;

import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.protection.flags.Flag;
import com.sk89q.worldguard.protection.flags.StateFlag;
import com.sk89q.worldguard.protection.flags.registry.FlagConflictException;
import com.sk89q.worldguard.protection.flags.registry.FlagRegistry;
import me.ojasislive.blockmini.listeners.Movement;
import org.bukkit.plugin.java.JavaPlugin;

public final class BlockMini extends JavaPlugin {

    public static StateFlag BLOCKDROP;
    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new Movement(this),this);
        getLogger().info("Plugin has Started!!!");
        getLogger().info("Made by OJASisLive#9313");
    }

    @Override
    public void onDisable() {

    }

    @Override
    public void onLoad(){
        final FlagRegistry registry = WorldGuard.getInstance().getFlagRegistry();

        try {
            final StateFlag flag = new StateFlag("BlockMini-enabled", true);
            registry.register(flag);
            BlockMini.BLOCKDROP = flag;
        }
        catch (FlagConflictException e) {
            final Flag<?> existing = registry.get("BlockMini-enabled");
            if (existing instanceof StateFlag) {
                BlockMini.BLOCKDROP = (StateFlag)existing;
            }
            else {
                System.out.println("Couldn't register flag BlockMini-enabled  :(");
            }
        }


    }
}
