package me.steep.universaltransmitters.transmitters;

import com.jeff_media.morepersistentdatatypes.DataType;
import me.steep.universaltransmitters.UniversalTransmitters;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.block.Container;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;

@SuppressWarnings("all")
public class ItemTransmitter extends Transmitter {

    private ArmorStand entity;

    public ItemTransmitter(Block block) {

        super(block);

        if(!(block instanceof Container)) return;

        if(Transmitter.isTransmitter(block)) {
            this.entity = findEntity();
            return;
        }

        this.entity = createEntity();
        this.data.set(this.typeKey, DataType.STRING, this.getClass().getCanonicalName());

    }

    private ArmorStand createEntity() {
        ArmorStand stand = (ArmorStand) this.block.getWorld().spawnEntity(this.block.getLocation().add(0.25, 0, 0), EntityType.ARMOR_STAND);
        stand.setInvisible(true);
        stand.setMarker(true);
        stand.getEquipment().setItemInMainHand(new ItemStack(Material.TRIDENT), true);
        return stand;
    }

    private ArmorStand findEntity() {
        return (ArmorStand) this.block.getWorld().getNearbyEntities(this.block.getLocation(), 1, 1,1,
                ent -> ent.getUniqueId().equals(this.data.get(new NamespacedKey(UniversalTransmitters.getInst(), "transmitter_ent"), DataType.UUID)));
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
