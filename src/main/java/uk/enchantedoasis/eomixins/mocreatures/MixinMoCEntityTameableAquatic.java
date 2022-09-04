package uk.enchantedoasis.eomixins.mocreatures;

import drzhark.mocreatures.entity.IMoCTameable;
import drzhark.mocreatures.entity.MoCEntityAquatic;
import drzhark.mocreatures.entity.MoCEntityTameableAquatic;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(value= MoCEntityTameableAquatic.class, remap=false)
public abstract class MixinMoCEntityTameableAquatic extends MoCEntityAquatic implements IMoCTameable {

    public MixinMoCEntityTameableAquatic(World world) {
        super(world);
    }

    @Overwrite
    public boolean getIsTamed() {
       return false;
    }

    @Overwrite
    public void setTamed(boolean tamed){
    }

}
