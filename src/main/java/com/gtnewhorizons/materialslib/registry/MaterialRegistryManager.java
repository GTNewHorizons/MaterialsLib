package com.gtnewhorizons.materialslib.registry;

import java.util.Collection;

import org.jetbrains.annotations.NotNull;

import com.google.common.base.Preconditions;
import com.gtnewhorizons.materialslib.MaterialLog;
import com.gtnewhorizons.materialslib.MaterialsLib;
import com.gtnewhorizons.materialslib.api.registry.IMaterialRegistry;
import com.gtnewhorizons.materialslib.api.registry.IMaterialRegistryManager;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.ModContainer;
import it.unimi.dsi.fastutil.objects.Object2ObjectMap;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.objects.ObjectCollections;

public final class MaterialRegistryManager implements IMaterialRegistryManager {

    private static MaterialRegistryManager INSTANCE;

    private final Object2ObjectMap<String, MaterialRegistry> registries = new Object2ObjectOpenHashMap<>();

    private IMaterialRegistry fallbackRegistry;
    private Phase registrationPhase = Phase.PRE;

    private MaterialRegistryManager() {}

    public static MaterialRegistryManager getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new MaterialRegistryManager();
        }
        return INSTANCE;
    }

    @Override
    public @NotNull IMaterialRegistry createRegistry(@NotNull String modid) {
        if (getPhase() != Phase.PRE) {
            throw new IllegalStateException("Cannot create registries in phase " + getPhase());
        }

        Preconditions
            .checkArgument(!registries.containsKey(modid), "Material registry already exists for modid %s", modid);
        MaterialLog.debug.info("Creating new material registry --- {}", modid);
        MaterialRegistry registry = new MaterialRegistry(modid);
        registries.put(modid, registry);

        if (fallbackRegistry == null) {
            MaterialLog.debug.info("Assigning fallback registry --- {}", modid);
            fallbackRegistry = registry;
        }
        return registry;
    }

    @Override
    public @NotNull IMaterialRegistry getRegistry(@NotNull String modid) {
        MaterialRegistry registry = registries.get(modid);
        if (registry == null) {
            MaterialLog.debug
                .warn("Registry {} not found, providing fallback registry --- {}", modid, fallbackRegistry.getModId());
            return fallbackRegistry;
        }
        return fallbackRegistry;
    }

    @Override
    public @NotNull Collection<@NotNull IMaterialRegistry> getRegistries() {
        if (getPhase() == Phase.PRE) {
            throw new IllegalStateException("Cannot get all material registries during phase " + getPhase());
        }
        return ObjectCollections.unmodifiable(registries.values());
    }

    @Override
    public @NotNull Phase getPhase() {
        return registrationPhase;
    }

    public void freezeAll() {
        if (!validateCaller()) {
            MaterialLog.debug.error("Bad caller to MaterialRegistryManager#freezeAll() --- {}", getCaller());
            return;
        }
        switch (getPhase()) {
            case PRE -> throw new IllegalStateException("Cannot freeze registries during the PRE phase!");
            case FROZEN -> throw new IllegalStateException("Cannot freeze registries during the FROZEN phase!");
        }

        MaterialLog.debug.info("Freezing all registries --- OPEN -> FROZEN");
        registries.values()
            .forEach(MaterialRegistry::freeze);
        registrationPhase = Phase.FROZEN;
    }

    private static boolean validateCaller() {
        return getCaller().equals(MaterialsLib.MODID);
    }

    private static String getCaller() {
        ModContainer container = Loader.instance()
            .activeModContainer();
        return container != null ? container.getModId() : "";
    }
}
