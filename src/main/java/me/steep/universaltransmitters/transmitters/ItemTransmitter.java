package me.steep.universaltransmitters.transmitters;

import com.jeff_media.morepersistentdatatypes.DataType;
import me.steep.universaltransmitters.UniversalTransmitters;
import me.steep.universaltransmitters.utils.Yaw;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Container;
import org.bukkit.block.data.Directional;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;

@SuppressWarnings("all")
public class ItemTransmitter extends Transmitter {

    private ArmorStand entity = null;

    public ItemTransmitter(Block block) {

        super(block);

        if (!(block.getState() instanceof Container)) return;

        if (Transmitter.isTransmitter(block)) {
            this.entity = findEntity();
            //Bukkit.broadcastMessage("found");
            return;
        }

        this.entity = createEntity();
        this.data.set(this.typeKey, DataType.STRING, this.getClass().getCanonicalName());
        this.data.set(new NamespacedKey(UniversalTransmitters.getInst(), "transmitter_ent"), DataType.UUID, this.entity.getUniqueId());

        //Bukkit.broadcastMessage("created");

    }

    private ArmorStand createEntity() {

        ArmorStand stand = (ArmorStand) this.block.getWorld().spawnEntity(this.block.getLocation().add(0.5, 0, 0.5), EntityType.ARMOR_STAND);
        stand.setInvisible(true);
        stand.setMarker(true);
        stand.getEquipment().setHelmet(new ItemStack(Material.TRIDENT), true);

        centerEntity(stand);

        return stand;
    }

    private ArmorStand findEntity() {
        List<Entity> entities = (List<Entity>) this.block.getWorld().getNearbyEntities(this.block.getLocation(), 1, 1, 1,
                ent -> ent.getUniqueId().equals(this.data.get(new NamespacedKey(UniversalTransmitters.getInst(), "transmitter_ent"), DataType.UUID)));
        return (ArmorStand) entities.get(0);
    }

    private void centerEntity(ArmorStand stand) {

        float yaw = 0.0F;
        Location loc = stand.getLocation().clone();

        if (this.block.getBlockData() instanceof Directional) {
            BlockFace facing = ((Directional) this.block.getBlockData()).getFacing();
            yaw = Yaw.valueOf(facing.toString().toUpperCase()).get();
            switch (facing) {
                case NORTH ->  loc.add(0.3, 0, 0.3);
                case EAST -> loc.add(-0.3, 0, 0.3);
                case SOUTH -> loc.subtract(0.3, 0, 0.3);
                case WEST -> loc.add(0.3, 0, -0.3);
                default -> loc.subtract(0.3, 0, 0.3);
            }
        } else {
            loc.add(0.3, 0, 0.3);
        }

        loc.setYaw(yaw);

        new BukkitRunnable() {
            @Override
            public void run() {
                stand.teleport(loc);
            }
        }.runTaskLater(UniversalTransmitters.getInst(), 1L);

    }

    public ArmorStand getEntity() {
        return entity;
    }

    @Override
    public void remove() {
        this.entity.remove();
        this.data.clear();
    }
}
