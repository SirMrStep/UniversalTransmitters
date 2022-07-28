package me.steep.universaltransmitters.listeners;

import com.jeff_media.customblockdata.events.CustomBlockDataRemoveEvent;
import me.steep.universaltransmitters.transmitters.Transmitter;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

@SuppressWarnings("all")
public class BreakListener implements Listener {

    @EventHandler
    public void onBreak(CustomBlockDataRemoveEvent e) {
        if(Transmitter.isTransmitter(e.getBlock())) {
            Transmitter.getTransmitter(e.getBlock()).remove();
        }
    }
}
