package uk.enchantedoasis.eomixins;

import io.github.crucible.grimoire.common.api.grimmix.Grimmix;
import io.github.crucible.grimoire.common.api.grimmix.GrimmixController;
import io.github.crucible.grimoire.common.api.grimmix.lifecycle.IConfigBuildingEvent;
import io.github.crucible.grimoire.common.api.mixin.ConfigurationType;
import io.github.crucible.omniconfig.api.OmniconfigAPI;

@Grimmix(id = "eomixins", name = "EO Mixins")
public class EOMixinsGrimmix extends GrimmixController {

    @Override
    public void buildMixinConfigs(IConfigBuildingEvent event) {
        OmniconfigAPI.registerAnnotationConfig(EOMixinsConfig.class);

        if(EOMixinsConfig.ThaumicWandsMixinEnabled){
            event.createBuilder("mixins.thaumicwands.json")
                    .mixinPackage("uk.enchantedoasis.eomixins.thaumicwands")
                    .commonMixins("MixinTW_EventHandler")
                    .clientMixins("MixinItemWandRenderer")
                    .refmap("@MIXIN_REFMAP@")
                    .configurationType(ConfigurationType.CORE)
                    .verbose(true)
                    .required(false)
                    .build();
        }

        if(EOMixinsConfig.MysticalAgricultureEnabled){
            event.createBuilder("mixins.mysticalagriculture.json")
                    .mixinPackage("uk.enchantedoasis.eomixins.mysticalagriculture")
                    .commonMixins("MixinBlockMysticalCrop", "MixinBlockInferiumCrop")
                    .clientMixins("MixinGrowthPulser")
                    .refmap("@MIXIN_REFMAP@")
                    .configurationType(ConfigurationType.MOD)
                    .verbose(true)
                    .required(false)
                    .build();
        }

        if(EOMixinsConfig.MoCreaturesFix)
            event.createBuilder("mixins.mocreatures.json")
                .mixinPackage("uk.enchantedoasis.eomixins.mocreatures")
                .commonMixins("MixinMoCEntityTameableAquatic", "MixinJellyFish")
                .refmap("@MIXIN_REFMAP@")
                .configurationType(ConfigurationType.MOD)
                .verbose(true)
                .required(false)
                .build();

        if(EOMixinsConfig.SimpleHarvestEnabled){
            event.createBuilder("mixins.simpleharvest.json")
                    .mixinPackage("uk.enchantedoasis.eomixins.simpleharvest")
                    .commonMixins("MixinEventHandler")
                    .refmap("@MIXIN_REFMAP@")
                    .configurationType(ConfigurationType.MOD)
                    .verbose(true)
                    .required(false)
                    .build();
        }

    }

}