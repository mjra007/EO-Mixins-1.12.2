package uk.enchantedoasis.eomixins.mysticalagriculture;

import com.blakebr0.cucumber.util.Utils;
import com.blakebr0.mysticalagriculture.blocks.BlockAccelerator;
import com.blakebr0.mysticalagriculture.blocks.crop.BlockInferiumCrop;
import com.blakebr0.mysticalagriculture.blocks.crop.BlockMysticalCrop;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.IBlockAccess;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Iterator;
import java.util.Random;

@Mixin(value= BlockInferiumCrop.class, remap=false)
public class MixinBlockInferiumCrop extends BlockMysticalCrop {
    private final float chanceOfDroppingEssence16Pulsars = 0.60f;
    private final float chanceOfDroppingEssence25Pulsars = 0.30f;
    private final float chanceOfDroppingSeeds25Pulsars = 0.95f;
    private final float chanceOfDroppingEssenceMoreThan25Pulsars = 0.30f;
    private final float chanceOfDroppingSeedMoreThan25Pulsars = 0.90f;

    @Shadow
    private int tier;

    public MixinBlockInferiumCrop(String name) {
        super(name);
    }

    @Overwrite @Override
    public void getDrops(NonNullList<ItemStack> drops, IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
        int age = state.getValue(this.AGE);
        Random rand = Utils.rand;
        int essence = 0;
        int seeds = 0;
        int numberOfPulsars = 0;
        if (age == 7) {
            numberOfPulsars = getNumberOfPulsars(world, pos.getX(), pos.getY()-2, pos.getZ());
            System.out.println(numberOfPulsars);
            if (numberOfPulsars <= 13) {
                seeds = 1;
                essence = this.tier;
            } else if (numberOfPulsars <= 16) {
                double chance = rand.nextFloat();
                if (chance <= chanceOfDroppingEssence16Pulsars) {
                    essence = this.tier;
                }
                seeds = 1;
            } else if (numberOfPulsars <= 25) {
                double chance = rand.nextFloat();
                if (chance <= chanceOfDroppingEssence25Pulsars) {
                    essence = this.tier;
                }
                if (chance <= chanceOfDroppingSeeds25Pulsars) {
                    seeds = 1;
                }
            }else{
                double chance = rand.nextFloat();
                if(chance<=chanceOfDroppingEssenceMoreThan25Pulsars){
                    essence = this.tier;
                }
                if(chance<=chanceOfDroppingSeedMoreThan25Pulsars){
                    seeds =1;
                }
            }
        }else{
            seeds = 1;
        }

        if(seeds > 0)
            drops.add(new ItemStack(this.getSeed(), seeds, 0));

        if (essence > 0) {
            drops.add(new ItemStack(this.getCrop(), essence, 0));
        }
    }

    public int getNumberOfPulsars(IBlockAccess world, int x, int y, int z){
        BlockPos pos = new BlockPos(x,y,z);
        Iterable<BlockPos> blocks = BlockPos.getAllInBox(pos.subtract(new Vec3i(0, 1, 0)), pos.subtract(new Vec3i(0, 64, 0)));
        Iterator<BlockPos> blocksIterator = blocks.iterator();
        int counter = 1;
        while(blocksIterator.hasNext()){

            BlockPos pos1 = blocksIterator.next();
            Block block = world.getBlockState(pos1).getBlock();

            if(block instanceof BlockAccelerator){
                counter++;
            }
        }
        return counter;
    }


}
