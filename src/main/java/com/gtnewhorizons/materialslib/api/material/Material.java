package com.gtnewhorizons.materialslib.api.material;

import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import org.jetbrains.annotations.NotNull;

import com.gtnewhorizons.materialslib.api.MaterialsAPI;
import com.gtnewhorizons.materialslib.api.type.IItemProvider;
import com.gtnewhorizons.materialslib.api.type.IMaterialType;

/**
 * The main Material class.
 * <br>
 * <br>
 * Holds all relevant data for creating Material Items, Fluids, and Blocks.
 * <br>
 * <br>
 * Cannot be extended!
 *
 * @author serenibyss
 * @since 1.0
 */
public final class Material implements IItemProvider {

    private final ResourceLocation resourceLocation;
    private final int metaItemSubId;

    Material(ResourceLocation resourceLocation, int metaItemSubId) {
        this.resourceLocation = resourceLocation;
        this.metaItemSubId = metaItemSubId;
        register();
    }

    public static MaterialBuilder builder(String modId, String name, int id) {
        return builder(new ResourceLocation(modId, name), id);
    }

    public static MaterialBuilder builder(ResourceLocation name, int id) {
        return new MaterialBuilder(name, id);
    }

    @Override
    public ItemStack get(IMaterialType type) {
        return null; // todo
    }

    public int getId() {
        return metaItemSubId;
    }

    public @NotNull String getName() {
        return resourceLocation.getResourcePath();
    }

    public @NotNull String getUpperName() {
        String name = getName();
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < name.length(); i++) {
            char c = name.charAt(i);
            if (c == '_') continue;
            if (i == 0 || name.charAt(i - 1) == '_') {
                result.append(Character.toUpperCase(c));
            } else {
                result.append(c);
            }
        }
        return result.toString();
    }

    public String getModId() {
        return resourceLocation.getResourceDomain();
    }

    private void register() {
        MaterialsAPI.materialManager.getRegistry(getModId())
            .register(this);
    }
}
