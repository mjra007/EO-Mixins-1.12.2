package uk.enchantedoasis.eomixins.thaumicwands;

import de.zpenguin.thaumicwands.entity.EntityVisOrb;
import de.zpenguin.thaumicwands.event.TW_EventHandler;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import uk.enchantedoasis.eomixins.EOMixinsConfig;

@Mod.EventBusSubscriber(
        modid = "thaumicwands"
)
@Mixin(value = TW_EventHandler.class)
public abstract class MixinTW_EventHandler{

    /**
     * @author mjra007
     * @reason to make it more balanced  && EOMixinsConfig.chanceOfVisOnMobKilled <= e.getEntityLiving().world.rand.nextInt(100)
     */
    @Overwrite
    @SubscribeEvent
    public static void onDeath(LivingDeathEvent e) {
        if (!e.getEntityLiving().world.isRemote) {
            if ( e.getEntityLiving().world.rand.nextInt(100) <= EOMixinsConfig.chanceOfVisOnMobKilled ) {
                EntityVisOrb orb = new EntityVisOrb(e.getEntityLiving().getEntityWorld(), e.getEntityLiving().posX, e.getEntityLiving().posY, e.getEntityLiving().posZ,
                        EOMixinsConfig.amountOfVisToDrop);
                e.getEntityLiving().getEntityWorld().spawnEntity(orb);
            }
        }
    }

}
