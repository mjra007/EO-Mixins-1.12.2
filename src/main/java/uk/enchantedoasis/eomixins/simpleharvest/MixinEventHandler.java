package uk.enchantedoasis.eomixins.simpleharvest;

import net.minecraft.block.BlockCrops;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.event.ForgeEventFactory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import tehnut.harvest.BlockStack;
import tehnut.harvest.Crop;
import tehnut.harvest.Harvest;

import java.util.Iterator;

@Mixin(value = Harvest.EventHandler.class, remap = false)
public class MixinEventHandler {

    @Overwrite
    private static void defaultHandlePlant(World world, BlockPos pos, IBlockState state, EntityPlayer player, BlockStack worldBlock) {
        BlockStack newBlock = ((Crop)Harvest.config.getCropMap().get(worldBlock)).getFinalBlock();
        NonNullList<ItemStack> drops = NonNullList.create();
        worldBlock.getBlock().getDrops(drops, world, pos, state, 0);
        ForgeEventFactory.fireBlockHarvesting(drops, world, pos, state, 0, 1.0F, false, player);
        boolean foundSeed = false;
        Iterator var8 = drops.iterator();

        while(var8.hasNext()) {
            ItemStack stack = (ItemStack)var8.next();
            if (!stack.isEmpty() && stack.getItem() instanceof IPlantable) {
                stack.shrink(1);
                foundSeed = true;
                break;
            }
        }

        boolean seedNotNull = true;
        if (worldBlock.getBlock() instanceof BlockCrops) {
            try {
                Item seed = (Item)Harvest._GET_SEED.invoke(worldBlock.getBlock());
                seedNotNull = seed != null && seed != Items.AIR;
            } catch (Exception var12) {
                Harvest.LOGGER.error("Failed to reflect BlockCrops: {}", var12.getLocalizedMessage());
            }
        }

        if (seedNotNull && foundSeed) {
            if (!world.isRemote) {
                world.setBlockState(pos, newBlock.getState());
                Iterator var15 = drops.iterator();

                while(var15.hasNext()) {
                    ItemStack stack = (ItemStack)var15.next();
                    EntityItem entityItem = new EntityItem(world, (double)pos.getX() + 0.5, (double)pos.getY() + 0.5, (double)pos.getZ() + 0.5, stack);
                    entityItem.setPickupDelay(10);
                    world.spawnEntity(entityItem);
                }
            }
        } else {
            if (!world.isRemote) {

                world.setBlockToAir(pos);
                Iterator var15 = drops.iterator();

                while(var15.hasNext()) {
                    ItemStack stack = (ItemStack)var15.next();
                    EntityItem entityItem = new EntityItem(world, (double)pos.getX() + 0.5, (double)pos.getY() + 0.5, (double)pos.getZ() + 0.5, stack);
                    entityItem.setPickupDelay(10);
                    world.spawnEntity(entityItem);
                }
            }
        }

    }

}
