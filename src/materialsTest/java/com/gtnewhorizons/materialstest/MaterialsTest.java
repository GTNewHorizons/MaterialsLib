package com.gtnewhorizons.materialstest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import cpw.mods.fml.common.Mod;

@Mod(modid = MaterialsTest.MODID, version = "1.0", name = MaterialsTest.MODNAME, acceptedMinecraftVersions = "[1.7.10]")
public class MaterialsTest {

    public static final String MODID = "materialstest";
    public static final String MODNAME = "MaterialsTest";
    public static final Logger LOG = LogManager.getLogger(MODID);
}
