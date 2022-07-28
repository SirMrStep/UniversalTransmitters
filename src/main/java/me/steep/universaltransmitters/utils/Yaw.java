package me.steep.universaltransmitters.utils;

import org.bukkit.block.BlockFace;

import java.util.Locale;

public enum Yaw {
    /*public static float NORTH = -180.0F;
    public static float EAST = -90.0F;
    public static float SOUTH = 0.0F;
    public static float WEST = 90.0F;*/

    NORTH(-180.0F),
    EAST(-90.0F),
    SOUTH(0.0F),
    WEST(90.0F),
    DOWN(0.0F),
    UP(0.0F);

    final float f;

    Yaw(float f) {
        this.f = f;
    }

    public float get() {
        return f;
    }

    public float get(BlockFace face) {
        return Yaw.valueOf(face.toString().toUpperCase()).get();
    }

    public float getOpposite(BlockFace face) {
        float opposite = 0.0F;
        switch (face) {
            case NORTH -> opposite = SOUTH.get();
            case EAST -> opposite = WEST.get();
            case SOUTH -> opposite = NORTH.get();
            case WEST -> opposite = EAST.get();
        }
        return opposite;
    }

}
