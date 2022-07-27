package me.steep.universaltransmitters.commands;

import me.steep.universaltransmitters.handlers.DataHandler;
import me.steep.universaltransmitters.transmitters.ItemTransmitter;
import me.steep.universaltransmitters.transmitters.LiquidTransmitter;
import me.steep.universaltransmitters.transmitters.RedstoneTransmitter;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.VisibleForTesting;

@VisibleForTesting
public class PipeCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {

        if(!(sender instanceof Player p)) return true;

        if(p.getInventory().firstEmpty() == -1) return true;

        if(args.length == 1) {

            switch (args[0].toLowerCase()) {

                case "liquid" -> p.getInventory().addItem(DataHandler.setDataString(new ItemStack(Material.STICK, 64), "pipe_type", LiquidTransmitter.class.getCanonicalName()));

                case "redstone" -> p.getInventory().addItem(DataHandler.setDataString(new ItemStack(Material.STICK, 64), "pipe_type", RedstoneTransmitter.class.getCanonicalName()));

                default -> p.getInventory().addItem(DataHandler.setDataString(new ItemStack(Material.STICK, 64), "pipe_type", ItemTransmitter.class.getCanonicalName()));

            }

        }

        return true;
    }
}
