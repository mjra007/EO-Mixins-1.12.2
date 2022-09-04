package uk.enchantedoasis.eomixins.thaumicwands;

 import de.zpenguin.thaumicwands.client.model.ModelWand;
 import de.zpenguin.thaumicwands.client.render.item.ItemWandRenderer;
 import de.zpenguin.thaumicwands.item.ItemWand;
 import net.minecraft.client.renderer.GlStateManager;
 import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
 import net.minecraft.client.renderer.tileentity.TileEntityItemStackRenderer;
 import net.minecraft.item.ItemStack;
 import org.spongepowered.asm.mixin.Mixin;
 import org.spongepowered.asm.mixin.Overwrite;
 import org.spongepowered.asm.mixin.Shadow;
 import uk.enchantedoasis.eomixins.ModelWandImproved;

@Mixin(value = ItemWandRenderer.class, remap = false)
public abstract class MixinItemWandRenderer extends TileEntityItemStackRenderer {

    @Shadow
    public static ItemCameraTransforms.TransformType transform;


        @Overwrite
    public void func_179022_a(ItemStack stack){
        if (stack != null && stack.getItem() instanceof ItemWand) {
            ModelWandImproved modelWand = new ModelWandImproved();
            GlStateManager.pushMatrix();
            if (transform == ItemCameraTransforms.TransformType.GUI) {
                GlStateManager.scale(1.6, 1.8, 1.6);
                GlStateManager.translate(1.14, 0.77, 0.0);
                GlStateManager.rotate(180.0F, 1.0F, 0.0F, 0.0F);
                GlStateManager.rotate(135.0F, 0.0F, 0.0F, 1.0F);
                GlStateManager.rotate(60.0F, 0.0F, 1.0F, 0.0F);
                modelWand.render(stack);
            }

            if (transform == ItemCameraTransforms.TransformType.GROUND) {
                GlStateManager.scale(0.8, 1.0, 0.8);
                GlStateManager.translate(0, 0.5, 0);
            }

            if (transform == ItemCameraTransforms.TransformType.FIRST_PERSON_RIGHT_HAND || transform == ItemCameraTransforms.TransformType.FIRST_PERSON_LEFT_HAND) {
                GlStateManager.scale(0.8, 1.0, 0.8);
                GlStateManager.translate(0, 0.4, 0);
             }

            if (transform == ItemCameraTransforms.TransformType.THIRD_PERSON_LEFT_HAND || transform == ItemCameraTransforms.TransformType.THIRD_PERSON_RIGHT_HAND) {
                GlStateManager.translate(-0.14, 0.4, -0.105);
            }

            modelWand.render(stack);
            GlStateManager.popMatrix();
        }
    }


    static {
        transform = ItemCameraTransforms.TransformType.GUI;
    }

}


