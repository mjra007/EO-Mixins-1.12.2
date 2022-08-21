package uk.enchantedoasis.eomixins.hats;

import io.github.crucible.grimoire.common.api.grimmix.Grimmix;
import io.github.crucible.grimoire.common.api.grimmix.GrimmixController;
import io.github.crucible.grimoire.common.api.grimmix.lifecycle.IConfigBuildingEvent;
import io.github.crucible.grimoire.common.api.mixin.ConfigurationType;

@Grimmix(id = "eomixins", name = "EO Mixins")
public class EOMixinsGrimmix extends GrimmixController {

    @Override
    public void buildMixinConfigs(IConfigBuildingEvent event) {

        event.createBuilder("eomixins/mixins.hats.json")
        .mixinPackage("uk.enchantedoasis.eomixins.hats.mixins")
        .commonMixins("MixinProxyCommon")
        .refmap("@MIXIN_REFMAP@")
        .configurationType(ConfigurationType.MOD)
        .verbose(true)
        .required(true)
        .build();

    }

}