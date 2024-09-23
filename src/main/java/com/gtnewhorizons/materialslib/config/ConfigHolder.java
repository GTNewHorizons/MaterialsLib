package com.gtnewhorizons.materialslib.config;

import com.gtnewhorizon.gtnhlib.config.Config;
import com.gtnewhorizons.materialslib.MaterialsLib;

@Config(modid = MaterialsLib.MODID)
public class ConfigHolder {

    @Config.Comment("Debug")
    public static boolean debug = false;
}
