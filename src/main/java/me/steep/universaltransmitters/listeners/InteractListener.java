package me.steep.universaltransmitters.listeners;

import com.jeff_media.morepersistentdatatypes.DataType;
import me.steep.universaltransmitters.handlers.DataHandler;
import me.steep.universaltransmitters.transmitters.Transmitter;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;

@SuppressWarnings("all")
public class InteractListener implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {

        if (e.getHand() != EquipmentSlot.HAND || e.getAction() != Action.RIGHT_CLICK_BLOCK) return;
        if (e.getClickedBlock() == null || e.getItem() == null) return;
        if (!DataHandler.hasData(e.getItem(), "transmitter_type", DataType.STRING)) return;

        Class<? extends Transmitter> clazz = Transmitter.getTransmitterClass(DataHandler.getDataString(e.getItem(), "transmitter_type"));
        if(clazz == null) return;

        Transmitter.createTransmitter(e.getClickedBlock(), clazz);

    }


}
