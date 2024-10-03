package com.gtnewhorizons.materialstest;

import net.minecraftforge.common.MinecraftForge;

import com.gtnewhorizons.materialslib.api.event.MaterialEvent;
import com.gtnewhorizons.materialslib.api.event.MaterialRegistryEvent;
import com.gtnewhorizons.materialslib.api.registry.IMaterialRegistryManager;
import com.gtnewhorizons.materialstest.materials.MTMaterials;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLConstructionEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

@Mod(modid = MaterialsTest.MODID, version = "1.0", name = MaterialsTest.MODNAME, acceptedMinecraftVersions = "[1.7.10]")
public class MaterialsTest {

    public static final String MODID = "materialstest";
    public static final String MODNAME = "MaterialsTest";

    @EventHandler
    public void onConstruction(FMLConstructionEvent event) {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onMaterialRegistryEvent(MaterialRegistryEvent event) {
        IMaterialRegistryManager manager = event.getManager();
        manager.createRegistry(MODID);
    }

    @SubscribeEvent
    public void onMaterialEvent(MaterialEvent event) {
        MTMaterials.register();
    }
}
