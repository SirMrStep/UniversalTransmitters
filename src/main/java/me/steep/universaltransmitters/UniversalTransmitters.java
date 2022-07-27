package me.steep.universaltransmitters;

import me.steep.universaltransmitters.commands.PipeCommand;
import me.steep.universaltransmitters.handlers.DataHandler;
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
        registerCommands(instance);
        DataHandler.register(instance);
    }

    private void registerListeners(UniversalTransmitters instance) {
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new InteractListener(), instance);
    }

    private void registerCommands(UniversalTransmitters instance) {
        getCommand("getpipe").setExecutor(new PipeCommand());
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
