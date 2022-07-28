package me.steep.universaltransmitters.transmitters;

import com.jeff_media.customblockdata.CustomBlockData;
import com.jeff_media.morepersistentdatatypes.DataType;
import me.steep.universaltransmitters.UniversalTransmitters;
import me.steep.universaltransmitters.handlers.DataHandler;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.Nullable;

@SuppressWarnings("all")
public class Transmitter {

    final Block block;
    final CustomBlockData data;
    final NamespacedKey typeKey = new NamespacedKey(UniversalTransmitters.getInst(), "transmitter_type");

    public Transmitter(Block block) {
        this.block = block;
        this.data = new CustomBlockData(block, UniversalTransmitters.getInst());
    }

    public Block getBlock() {
        return this.block;
    }
    public CustomBlockData getData() {
        return data;
    }
    public NamespacedKey getTypeKey() {
        return typeKey;
    }
    public void remove() {
        this.data.clear();
    }

    public static boolean isTransmitter(Block block) {
        return new CustomBlockData(block, UniversalTransmitters.getInst()).has(new NamespacedKey(UniversalTransmitters.getInst(), "transmitter_type"), DataType.STRING);
    }

    @Nullable
    public static Transmitter createTransmitter(Block block, Class<? extends Transmitter> clazz) {

        if(isTransmitter(block)) return getTransmitter(block);

        final Transmitter[] pipe = new Transmitter[1];

        new BukkitRunnable() {
            @Override
            public void run() {

                try {
                    pipe[0] = clazz.getConstructor(Block.class).newInstance(block);
                } catch (Exception e) {
                    e.printStackTrace();
                    pipe[0] = null;
                }

            }
        }.runTaskLater(UniversalTransmitters.getInst(), 0);

        return pipe[0];

    }

    public static void removeTransmitter(Block block) {

        getTransmitter(block).remove();

    }

    @Nullable
    public static Class<? extends Transmitter> getTransmitterClass(String classname) {
        try {
            return (Class<? extends Transmitter>) Class.forName(classname);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Nullable
    public static Transmitter getTransmitter(Block block) {

        if(!isTransmitter(block)) return null;

        try {
            return (Transmitter) Class.forName(DataHandler.getDataString(block, "transmitter_type"))
                    .getConstructor(Block.class).newInstance(block);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    /*public enum TransmitterType {

        ITEM_TRANSMITTER(ItemTransmitter.class),
        LIQUID_TRANSMITTER(LiquidTransmitter.class),
        REDSTONE_TRANSMITTER(RedstoneTransmitter.class);

        private final Class<? extends Transmitter> clazz;

        TransmitterType(Class<? extends Transmitter> c) {
            this.clazz = c;
        }

        Class<? extends Transmitter> get() {
            return this.clazz;
        }

    }*/

}
