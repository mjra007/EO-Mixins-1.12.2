package uk.enchantedoasis.eomixins;

import io.github.crucible.omniconfig.api.annotation.AnnotationConfig;
import io.github.crucible.omniconfig.api.annotation.properties.ConfigBoolean;
import io.github.crucible.omniconfig.api.annotation.properties.ConfigInt;

@AnnotationConfig(version = "1.0.1")
public class EOMixinsConfig {

    @ConfigBoolean
    public static boolean ThaumicWandsMixinEnabled = true;
    @ConfigBoolean
    public static boolean MysticalAgricultureEnabled = true;

    @ConfigBoolean
    public static boolean SimpleHarvestEnabled = true;

    @ConfigBoolean
    public static boolean MoCreaturesFix =true;
    @ConfigInt(comment = "Chance of vis dropping from mobs. Default in thaumic wands is 50", name ="Chance of vis dropping", max = 100)
    public static int chanceOfVisOnMobKilled = 100;

    @ConfigInt(comment ="Amount of vis to drop when mob is killed.", name ="Vis amount")
    public static int amountOfVisToDrop = 1;

}
