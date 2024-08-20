package codes.shiftmc.hrm.Server;

import net.minestom.server.coordinate.Pos;
import net.minestom.server.coordinate.Vec;
import net.minestom.server.entity.Entity;
import net.minestom.server.entity.EntityType;
import net.minestom.server.entity.metadata.display.BlockDisplayMeta;
import net.minestom.server.entity.metadata.display.ItemDisplayMeta;
import net.minestom.server.instance.Instance;
import net.minestom.server.instance.block.Block;
import net.minestom.server.item.ItemStack;
import net.minestom.server.item.Material;

public class Tile extends Entity {
    public Tile(Instance instance, Pos position) {
        super(EntityType.ITEM_DISPLAY);
        var meta = (ItemDisplayMeta) getEntityMeta();

        meta.setNotifyAboutChanges(false);
        meta.setScale(new Vec(0.5f, 0.5f, 0.5f));
        meta.setItemStack(ItemStack.of(Material.GREEN_WOOL));
        meta.setNotifyAboutChanges(true);

        setInstance(instance, position);
    }
}
