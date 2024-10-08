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
     * <p/>
     * Available at the Construction stage.
     */
    public static IMaterialRegistryManager materialManager;

    /**
     * The maximum number of Materials per registry.
     * <p/>
     * Provided for reference not modification.
     */
    public static final int REGISTRY_MAXIMUM = Short.MAX_VALUE;

    /**
     * This is worth exactly one normal Item.
     * This Constant can be divided by many commonly used Numbers such as
     * 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 12, 14, 15, 16, 18, 20, 21, 24, ... 64 or 81
     * without loosing precision and is for that reason used as Unit of Amount.
     * But it is also small enough to be multiplied with larger Numbers.
     * <p/>
     * This is used to determine the amount of Material contained inside a prefixed Ore.
     * For example Nugget = M / 9 as it contains out of 1/9 of an Ingot.
     */
    public static final long UNIT = 3628800;
}
