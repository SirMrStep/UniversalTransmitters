package me.steep.universaltransmitters;

import com.jeff_media.customblockdata.CustomBlockData;
import me.steep.universaltransmitters.commands.TransmitterCommand;
import me.steep.universaltransmitters.handlers.DataHandler;
import me.steep.universaltransmitters.listeners.BreakListener;
import me.steep.universaltransmitters.listeners.InteractListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class UniversalTransmitters extends JavaPlugin {

    @Override
    public void onEnable() {

        initialize(this);

    }

    private void initialize(UniversalTransmitters instance) {
        registerListeners(instance);
        registerCommands();
        DataHandler.register(instance);
    }

    private void registerListeners(UniversalTransmitters instance) {
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new InteractListener(), instance);
        pm.registerEvents(new BreakListener(), instance);
        CustomBlockData.registerListener(instance);
    }

    private void registerCommands() {
        getCommand("trans").setExecutor(new TransmitterCommand());
    }

    private static UniversalTransmitters instance;

    @Override
    public void onLoad() {
        instance = this;
    }

    public static UniversalTransmitters getInst() {
        return instance;
    }
}
