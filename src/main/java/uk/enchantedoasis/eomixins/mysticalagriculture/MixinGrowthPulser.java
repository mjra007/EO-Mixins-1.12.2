package uk.enchantedoasis.eomixins.mysticalagriculture;

import com.blakebr0.cucumber.iface.IEnableable;
import com.blakebr0.mysticalagriculture.blocks.BlockAccelerator;
import com.blakebr0.mysticalagriculture.blocks.BlockBase;
import com.mojang.realmsclient.gui.ChatFormatting;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import javax.annotation.Nullable;
import java.util.List;

@Mixin(value = BlockAccelerator.class, remap = false)
public abstract class MixinGrowthPulser extends BlockBase implements IEnableable {

    public MixinGrowthPulser(String name, Material material, SoundType sound, float hardness, float resistance, String tool, int level) {
        super(name, material, sound, hardness, resistance, tool, level);
    }

    @Inject(method = {"func_190948_a"},  at=@At(value ="TAIL"))
    public void func_190948_a(ItemStack stack, @Nullable World player, @NotNull List<String> tooltip, ITooltipFlag advanced, CallbackInfo ci) {

        tooltip.add(ChatFormatting.RED+"WARNING");
        tooltip.add(ChatFormatting.LIGHT_PURPLE+ "Crops become destabilized the more growth accelerators you use:");
        tooltip.add(" ");
        createToolTip(tooltip,"0 to 13", "100%", "100%");
        createToolTip(tooltip,"14 to 16", "100%", "60%");
        createToolTip(tooltip,"17 to 24", "95%", "30%");
        createToolTip(tooltip,"25 and up", "90%", "30%");
    }

    public void createToolTip(List<String> tooltip, String pulsers, String seedDrop, String essenceDrop){
      tooltip.add(pulsers+": ");
      tooltip.add(ChatFormatting.GRAY+"  Seed drop chance-> "+ChatFormatting.AQUA + seedDrop );
      tooltip.add(ChatFormatting.GRAY+"  Essence drop chance-> "+ChatFormatting.AQUA + essenceDrop);
    }

}
