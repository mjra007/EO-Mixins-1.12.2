package uk.enchantedoasis.eomixins;

import de.zpenguin.thaumicwands.item.ItemWand;
import java.awt.Color;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Mixin;

public class ModelWandImproved  extends ModelBase {
    final ModelRenderer rod;
    final ModelRenderer capTop;
    final ModelRenderer capBottom;
    final ModelRenderer focus;

    float  rotationX = 0.0f;
    float rotationZ =0.0F;

    float rotationXCap= 0.0f;
    public ModelWandImproved() {
        this.textureWidth = 32;
        this.textureHeight = 32;
        this.rod = new ModelRenderer(this, 0, 8);
        this.rod.addBox(-1.0F, 0.0F, -1.0F, 2, 18, 2);
        this.rod.setRotationPoint(rotationX, 2.0F, rotationZ);
        this.rod.setTextureSize(64, 64);
        this.capTop = new ModelRenderer(this, 0, 0);
        this.capTop.addBox(-1.0F, -1.0F, -1.0F, 2, 2, 2);
        this.capTop.setRotationPoint(rotationXCap, 2.0F, rotationXCap);
        this.capTop.setTextureSize(64, 32);
        this.capBottom = new ModelRenderer(this, 0, 0);
        this.capBottom.addBox(-1.0F, -1.0F, -1.0F, 2, 2, 2);
        this.capBottom.setRotationPoint(rotationXCap, 20.0F, rotationXCap);
        this.capBottom.setTextureSize(64, 32);
        this.focus = new ModelRenderer(this, 0, 0);
        this.focus.addBox(-1.0F, 0.10F, -1.0F, 2, 2, 2);
        this.focus.setRotationPoint(rotationXCap, 22.0F, rotationXCap);
        this.focus.setTextureSize(32, 32);
        setRotation(rod, 0,0,0);
        setRotation(capTop, 0,0,0);
        setRotation(capBottom, 0,0,0);
        setRotation(focus, 0,0,0);
    }

    public void render(ItemStack wandStack) {
        float scale = 0.0325F;
        ItemWand wand = (ItemWand)wandStack.getItem();
        Minecraft.getMinecraft().renderEngine.bindTexture(wand.getRod(wandStack).getTexture());
        GL11.glPushMatrix();
        GlStateManager.translate(0.66375, -0.125, 0.66375);
        this.rod.render(scale);
        Minecraft.getMinecraft().renderEngine.bindTexture(wand.getCap(wandStack).getTexture());
        GL11.glScaled(1.275, 1.0, 1.275);
        GlStateManager.enableLighting();
        this.capTop.render(scale);
        this.capBottom.render(scale);
        if (wand.getFocus(wandStack) != null) {
            Color c = new Color(wand.getFocus(wandStack).getFocusColor(wand.getFocusStack(wandStack)));
            GL11.glColor3f((float)c.getRed() / 255.0F, (float)c.getGreen() / 255.0F, (float)c.getBlue() / 255.0F);
            GlStateManager.scale(1.1, 1.1, 1.1);
            GlStateManager.translate(0.0, -0.125, 0.0);
            Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation("thaumicwands", "textures/models/wand_focus.png"));
            this.focus.render(scale);
            GL11.glColor3f(1.0F, 1.0F, 1.0F);
        }

        GL11.glPopMatrix();
    }

    private void setRotation(ModelRenderer model, float x , float y, float z){
        model.rotateAngleX = 0;
        model.rotateAngleY=0;
        model.rotateAngleZ=0;
    }
}