package com.gtnewhorizons.materialstest.materials;

import static com.gtnewhorizons.materialstest.MaterialsTest.MODID;

import com.gtnewhorizons.materialslib.api.material.Material;

public class MTMaterials {

    public static Material TestMaterial1;

    public static void register() {
        TestMaterial1 = Material.builder(MODID, "testMaterial1", 1)
            .build();
    }
}
