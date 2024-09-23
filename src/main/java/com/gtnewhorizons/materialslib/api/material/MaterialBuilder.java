package com.gtnewhorizons.materialslib.api.material;

import net.minecraft.util.ResourceLocation;

/**
 * A simple builder class for creating new {@link Material Materials}.
 *
 * @author serenibyss
 * @since 1.0
 */
public class MaterialBuilder {

    private final ResourceLocation resourceLocation;
    private final short metaItemSubId;

    protected MaterialBuilder(ResourceLocation resourceLocation, short metaItemSubId) {
        this.resourceLocation = resourceLocation;
        this.metaItemSubId = metaItemSubId;
    }

    public Material build() {
        return new Material(resourceLocation, metaItemSubId);
    }
}
