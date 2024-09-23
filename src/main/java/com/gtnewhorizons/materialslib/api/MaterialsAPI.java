package com.gtnewhorizons.materialslib.api;

import com.gtnewhorizons.materialslib.api.registry.IMaterialRegistryManager;

/**
 * Primary API entrypoint for the Material API.
 *
 * @author serenibyss
 * @since 1.0
 */
public final class MaterialsAPI {

    private MaterialsAPI() {
        throw new UnsupportedOperationException();
    }

    /**
     * Material Registry Manager for registering and querying
     * {@link com.gtnewhorizons.materialslib.api.registry.IMaterialRegistry Material Registries}.
     * <br>
     * <br>
     * Available at the Construction stage.
     */
    public static IMaterialRegistryManager materialManager;

    /**
     * The maximum number of Materials per registry.
     * <br>
     * <br>
     * Provided for reference not modification.
     */
    public static final int REGISTRY_MAXIMUM = Short.MAX_VALUE;
}
