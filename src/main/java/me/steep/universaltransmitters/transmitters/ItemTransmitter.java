package me.steep.universaltransmitters.transmitters;

import org.bukkit.Material;
import org.bukkit.block.Block;

public class ItemTransmitter extends Transmitter {

    public ItemTransmitter(Block block) {

        super(block);



        block.setType(Material.BEDROCK);

    }


}
