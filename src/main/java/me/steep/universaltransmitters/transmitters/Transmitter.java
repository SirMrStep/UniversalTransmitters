package me.steep.universaltransmitters.transmitters;

import com.jeff_media.customblockdata.CustomBlockData;
import com.jeff_media.morepersistentdatatypes.DataType;
import me.steep.universaltransmitters.UniversalTransmitters;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.Nullable;

@SuppressWarnings("all")
public class Transmitter {

    private Block block;

    public Transmitter(Block block) {
        this.block = block;
    }

    public Block getBlock() {
        return this.block;
    }

    public static boolean isTransmitter(Block block) {
        return new CustomBlockData(block, UniversalTransmitters.getInst()).has(new NamespacedKey(UniversalTransmitters.getInst(), "UT_Transmitter"), DataType.BOOLEAN);
    }

    @Nullable
    public static <V extends Transmitter> V createTransmitter(Block block, Class<? extends Transmitter> clazz) {

        final V[] pipe = (V[]) new Transmitter[]{null};

        new BukkitRunnable() {
            @Override
            public void run() {

                try {
                    pipe[0] = (V) clazz.getConstructor(Block.class).newInstance(block);
                } catch (Exception e) {
                    pipe[0] = null;
                }

            }
        }.runTaskLater(UniversalTransmitters.getInst(), 0);

        return pipe[0];

    }

    @Nullable
    public static Class<? extends Transmitter> getTransmitter(String classname) {
        try {
            return (Class<? extends Transmitter>) Class.forName(classname);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public enum TransmitterType {

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

    }

}
