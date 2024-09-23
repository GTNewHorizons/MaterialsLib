package com.gtnewhorizons.materialslib.registry;

import java.util.Collection;

import org.jetbrains.annotations.NotNull;

import com.gtnewhorizons.materialslib.MaterialLog;
import com.gtnewhorizons.materialslib.api.MaterialsAPI;
import com.gtnewhorizons.materialslib.api.material.Material;
import com.gtnewhorizons.materialslib.api.registry.IMaterialRegistry;
import com.gtnewhorizons.materialslib.api.registry.IMaterialRegistryManager.Phase;

public final class MaterialRegistry implements IMaterialRegistry {

    private final String modid;
    private final int maxId = MaterialsAPI.REGISTRY_MAXIMUM;

    private boolean frozen = true;

    MaterialRegistry(@NotNull String modid) {
        this.modid = modid;
    }

    @Override
    public void register(@NotNull Material material) {
        Phase phase = MaterialsAPI.materialManager.getPhase();
        if (phase != Phase.OPEN) {
            MaterialLog.out
                .error("Materials cannot be registered at phase {}! Skipping material {}...", phase, material);
            return;
        }
        int id = material.getId();
        if (id < 0 || id >= maxId) {
            throw new IllegalArgumentException("Id " + id + " is out of range for Material: " + material);
        }
        // todo
    }

    @Override
    public @NotNull Collection<Material> getAllMaterials() {
        return null; // todo
    }

    @Override
    public @NotNull String getModId() {
        return modid;
    }

    void freeze() {
        if (frozen) {
            throw new IllegalStateException("Registry is already frozen!");
        }
        this.frozen = true;
    }

    void unfreeze() {
        if (!frozen) {
            throw new IllegalStateException("Registry is already unfrozen!");
        }
        this.frozen = false;
    }
}
