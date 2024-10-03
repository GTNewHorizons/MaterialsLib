package com.gtnewhorizons.materialstest.materials;

import com.gtnewhorizons.materialslib.api.material.Material;
import static com.gtnewhorizons.materialstest.MaterialsTest.MODID;

public class MTMaterials {

    public static Material TestMaterial1;

    public static void register() {
        TestMaterial1 = Material.builder(MODID, "testMaterial1", 1).build();
    }
}
