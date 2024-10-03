package com.gtnewhorizons.materialslib.api.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import com.gtnewhorizons.materialslib.api.material.Material;
import com.gtnewhorizons.materialslib.api.registry.IMaterialRegistry;
import com.gtnewhorizons.materialslib.api.type.IMaterialType;

/**
 * An interface which will always be present on any <strong>generated</strong> {@link Item} that represents some
 * material.
 * <br>
 * <br>
 * Some Items may not be found with this, such as an Iron Ingot, which may not be generated and instead use the
 * Vanilla Item directly. It is always recommended to use the Unifier (TODO LINK) to get the Item directly, instead
 * using this interface to test if a provided item is a Material generated item.
 *
 * @author serenibyss
 * @since 1.0
 */
public interface IMaterialItem {

    /**
     * Get the Material from an ItemStack. The passed ItemStack <strong>must</strong> be the same Item as the item this
     * method is called on.
     *
     * @param stack The ItemStack to get the Material from.
     *
     * @return The material, or <strong>null</strong> if none could be found for the provided ItemStack.
     */
    @Nullable
    Material getMaterial(@NotNull ItemStack stack);

    /**
     * Get the material associated with the provided metadata value on this Item.
     *
     * @param metadata The metadata, must be between 0 and the
     *                 {@link com.gtnewhorizons.materialslib.api.MaterialsAPI#REGISTRY_MAXIMUM Registry Maximum}
     *                 (inclusive).
     *
     * @return The material, if it exists for the provided metadata on this Item's registry.
     *
     * @throws IndexOutOfBoundsException if the metadata is outside the above constraint.
     */
    @Nullable
    Material getMaterial(int metadata);

    /**
     * Get the type associated with this Material Item. All valid metadata values of this item will be of this type.
     *
     * @return The type
     */
    @NotNull
    IMaterialType<Item> getType();

    /**
     * @return The registry which owns this Item.
     */
    @NotNull
    IMaterialRegistry getOwnerRegistry();
}
