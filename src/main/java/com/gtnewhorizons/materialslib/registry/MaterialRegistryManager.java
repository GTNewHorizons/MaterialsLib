package com.gtnewhorizons.materialslib.registry;

import java.util.Collection;

import org.jetbrains.annotations.NotNull;

import com.gtnewhorizons.materialslib.api.registry.IMaterialRegistry;
import com.gtnewhorizons.materialslib.api.registry.IMaterialRegistryManager;

import it.unimi.dsi.fastutil.objects.Object2ObjectMap;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;

public final class MaterialRegistryManager implements IMaterialRegistryManager {

    private static MaterialRegistryManager INSTANCE;

    private final Object2ObjectMap<String, IMaterialRegistry> registries = new Object2ObjectOpenHashMap<>();

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
        return null; // todo
    }

    @Override
    public @NotNull IMaterialRegistry getRegistry(@NotNull String modid) {
        return null; // todo
    }

    @Override
    public @NotNull Collection<@NotNull IMaterialRegistry> getRegistries() {
        return null; // todo
    }

    @Override
    public @NotNull Phase getPhase() {
        return registrationPhase;
    }
}
