package com.gtnewhorizons.materialslib.api.type;

/**
 * Represents a type that a Material can take. This is most similar to OrePrefixes from the former Material system.
 *
 * @param <T> The object ({@link net.minecraft.item.Item Item}, {@link net.minecraft.block.Block Block},
 *            {@link net.minecraftforge.fluids.Fluid Fluid}, etc.) that this material type creates.
 *
 * @author serenibyss
 * @since 1.0
 */
// todo de-generify, this is item specific
public interface IMaterialType<T> {

    /**
     * Create the object ({@link net.minecraft.item.Item Item}, {@link net.minecraft.block.Block Block},
     * {@link net.minecraftforge.fluids.Fluid Fluid}, etc.) associated with this type.
     *
     * @return The newly created object.
     */
    T createObject();

    /**
     *
     */
    void processObjects();

    /**
     * Get the {@link net.minecraftforge.oredict.OreDictionary OreDictionary} prefix to use for this material type.
     * For example: an Ingot may use the prefix "ingot" for the OreDictionary.
     *
     * @return The Ore Dictionary prefix to use for this material type.
     */
    String getPrefix();
}
