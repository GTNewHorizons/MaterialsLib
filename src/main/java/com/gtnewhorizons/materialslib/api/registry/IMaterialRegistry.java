package com.gtnewhorizons.materialslib.api.registry;

import java.util.Collection;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import com.gtnewhorizons.materialslib.api.material.Material;

/**
 * @author serenibyss
 * @since 1.0
 */
public interface IMaterialRegistry {

    void register(@NotNull Material material);

    @NotNull
    Collection<Material> getAllMaterials();

    @NotNull
    String getModId();

    @Nullable
    Material getMaterial(@NotNull String name);

    @Nullable
    Material getMaterial(int id);
}
