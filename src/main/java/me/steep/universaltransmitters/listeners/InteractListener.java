package me.steep.universaltransmitters.listeners;

import com.jeff_media.morepersistentdatatypes.DataType;
import me.steep.universaltransmitters.handlers.DataHandler;
import me.steep.universaltransmitters.transmitters.Transmitter;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;

@SuppressWarnings("all")
public class InteractListener implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {

        if(e.getHand() != EquipmentSlot.HAND || e.getAction() != Action.RIGHT_CLICK_BLOCK) return;
        if (e.getClickedBlock() == null || e.getItem() == null) return;
        if (!DataHandler.hasData(e.getItem(), "pipe_type", DataType.STRING)) return;

        Transmitter.createTransmitter(e.getClickedBlock().getRelative(e.getBlockFace()),
                Transmitter.getTransmitter(DataHandler.getDataString(e.getItem(), "pipe_type")));

    }


}
