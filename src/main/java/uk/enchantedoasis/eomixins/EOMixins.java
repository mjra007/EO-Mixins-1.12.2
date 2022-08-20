package uk.enchantedoasis.eomixins;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


@Mod(modid = uk.enchantedoasis.eomixins.EOMixins.MODID, version = uk.enchantedoasis.eomixins.EOMixins.VERSION, name = uk.enchantedoasis.eomixins.EOMixins.NAME, dependencies = "required-after:grimoire@[3.2.10,)")
public class EOMixins {
    public static final String MODID = "eomixins";
    public static final String NAME = "EO Mixins";
    public static final String VERSION = "@VERSION@";

    public static final Logger logger = LogManager.getLogger("EOMixins");

    @EventHandler
    public void load(FMLInitializationEvent event) {

    }

    @EventHandler
    public void serverLoad(FMLServerStartingEvent event) {
        // NO-OP
    }

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {

    }

}
