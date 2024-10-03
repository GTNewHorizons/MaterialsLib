package com.gtnewhorizons.materialslib;

import com.gtnewhorizon.gtnhlib.config.ConfigException;
import com.gtnewhorizon.gtnhlib.config.ConfigurationManager;
import com.gtnewhorizons.materialslib.api.MaterialsAPI;
import com.gtnewhorizons.materialslib.api.event.MaterialEvent;
import com.gtnewhorizons.materialslib.api.event.MaterialRegistryEvent;
import com.gtnewhorizons.materialslib.config.ConfigHolder;
import com.gtnewhorizons.materialslib.proxy.CommonProxy;
import com.gtnewhorizons.materialslib.registry.MaterialRegistryManager;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.common.MinecraftForge;

@Mod(
    modid = MaterialsLib.MODID,
    version = Tags.VERSION,
    name = MaterialsLib.MODNAME,
    acceptedMinecraftVersions = "[1.7.10]")
public class MaterialsLib {

    public static final String MODID = "materialslib";
    public static final String MODNAME = "MaterialsLib";

    @SidedProxy(
        clientSide = "com.gtnewhorizons.materialslib.proxy.ClientProxy",
        serverSide = "com.gtnewhorizons.materialslib.proxy.CommonProxy")
    public static CommonProxy proxy;

    static {
        try {
            ConfigurationManager.registerConfig(ConfigHolder.class);
        } catch (ConfigException e) {
            throw new RuntimeException(e);
        }
    }

    public MaterialsLib() {
        MaterialsAPI.materialManager = MaterialRegistryManager.getInstance();
    }

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        proxy.preInit(event);

        /* Begin Registration */

        MaterialRegistryManager managerInternal = (MaterialRegistryManager) MaterialsAPI.materialManager;

        /* Material Registry Registration */
        MaterialLog.out.info("Registering material registries");
        MinecraftForge.EVENT_BUS.post(new MaterialRegistryEvent(managerInternal));
        managerInternal.openRegistries();

        /* Material Registration */
        MaterialLog.out.info("Registering materials");
        MinecraftForge.EVENT_BUS.post(new MaterialEvent());
        managerInternal.freezeRegistries();

        /* End Registration */
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.init(event);
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        proxy.postInit(event);
    }

    @Mod.EventHandler
    public void serverStarting(FMLServerStartingEvent event) {
        proxy.serverStarting(event);
    }
}
