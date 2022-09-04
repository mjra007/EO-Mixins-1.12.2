package uk.enchantedoasis.eomixins.mocreatures;

import drzhark.mocreatures.entity.MoCEntityTameableAquatic;
import drzhark.mocreatures.entity.aquatic.MoCEntityJellyFish;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(value = MoCEntityJellyFish.class ,remap = false)
public class MixinJellyFish  extends MoCEntityTameableAquatic {

    @Shadow
    private static final DataParameter<Boolean> GLOWS = EntityDataManager.createKey(MoCEntityJellyFish.class, DataSerializers.BOOLEAN);

    public MixinJellyFish(World world) {
        super(world);
    }

    @Overwrite
    public boolean func_184202_aL() {
        try{
            return this.dataManager.get(GLOWS);
        }catch(ClassCastException ex){
            return false;
        }
    }

}
