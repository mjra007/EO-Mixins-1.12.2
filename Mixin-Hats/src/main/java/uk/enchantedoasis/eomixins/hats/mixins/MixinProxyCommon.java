package uk.enchantedoasis.eomixins.hats.mixins; ;

import me.ichun.mods.hats.common.Hats;
import me.ichun.mods.hats.common.core.ProxyCommon;
import me.ichun.mods.hats.common.entity.EntityHat;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


@Mixin(value = ProxyCommon.class, remap = false)
public abstract class MixinProxyCommon{

    @Inject(method={"preInitMod"}, at=@At("HEAD"))
    public void preInitMod(CallbackInfo ci){
        System.out.println("[EOMixins] Registering entity hats:hat to be able to add it to the list of entities entityculling mod doesnt cull.");
        final ResourceLocation registryName = new ResourceLocation(Hats.MOD_NAME, "hat");
        EntityRegistry.registerModEntity(registryName, EntityHat.class, registryName.toString(), 0, Hats.instance, 50, 5, true);
    }

}
