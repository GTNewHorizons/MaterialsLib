package com.gtnewhorizons.materialslib.registry;

import java.util.Collection;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import com.gtnewhorizons.materialslib.MaterialLog;
import com.gtnewhorizons.materialslib.api.MaterialsAPI;
import com.gtnewhorizons.materialslib.api.material.Material;
import com.gtnewhorizons.materialslib.api.registry.IMaterialRegistry;
import com.gtnewhorizons.materialslib.api.registry.IMaterialRegistryManager.Phase;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.objects.Object2ObjectMap;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.objects.ObjectCollections;

public final class MaterialRegistry implements IMaterialRegistry {

    private final String modid;
    private final int maxId = MaterialsAPI.REGISTRY_MAXIMUM;

    private final Int2ObjectMap<Material> idRegistry = new Int2ObjectOpenHashMap<>(maxId);
    private final Object2ObjectMap<String, Material> nameRegistry = new Object2ObjectOpenHashMap<>(maxId);

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
        if (idRegistry.containsKey(id)) {
            Material existing = idRegistry.get(id);
            throw new IllegalArgumentException(
                String.format(
                    "Tried to reassign id %d to %s (%s), but it is already assigned to %s (%s)!",
                    id,
                    material,
                    material.getName(),
                    existing,
                    existing.getName()));
        }

        if (nameRegistry.containsKey(material.getName())) {
            throw new IllegalArgumentException(
                String.format("Tried to reassign name %s, but it is already assigned!", material.getName()));
        }

        String name = material.getName();
        MaterialLog.debug.info("Registering Material --- Registry: {}, Material: {} ({})", modid, name, id);
        idRegistry.put(id, material);
        nameRegistry.put(name, material);
    }

    @Override
    public @NotNull Collection<Material> getAllMaterials() {
        return ObjectCollections.unmodifiable(nameRegistry.values());
    }

    @Override
    public @NotNull String getModId() {
        return modid;
    }

    @Override
    public @Nullable Material getMaterial(@NotNull String name) {
        return nameRegistry.get(name);
    }

    @Override
    public @Nullable Material getMaterial(int id) {
        return idRegistry.get(id);
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
