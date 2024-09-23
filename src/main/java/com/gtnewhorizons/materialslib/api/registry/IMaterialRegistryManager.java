package com.gtnewhorizons.materialslib.api.registry;

import java.util.Collection;

import org.jetbrains.annotations.NotNull;

/**
 * @author serenibyss
 * @since 1.0
 */
public interface IMaterialRegistryManager {

    @NotNull
    IMaterialRegistry createRegistry(@NotNull String modid);

    @NotNull
    IMaterialRegistry getRegistry(@NotNull String modid);

    @NotNull
    Collection<@NotNull IMaterialRegistry> getRegistries();

    @NotNull
    Phase getPhase();

    enum Phase {
        /** Material Registration has not yet started. Used for creating new registries. */
        PRE,
        /** Material Registration has started. Registries may no longer be created, Materials may be added. */
        OPEN,
        /** Material Registration has ended. All registries are frozen. */
        FROZEN
    }
}
