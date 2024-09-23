package com.gtnewhorizons.materialslib.api.type;

import net.minecraft.item.ItemStack;

/**
 * Represents a "provider" which when given a {@link IMaterialType Material Type}, can provide an {@link ItemStack}
 * representation of itself combined with that material type.
 * <br>
 * <br>
 * For example:
 * <ul>
 * <li>"Aluminium" could be an item provider, which when passed the "gear" material type, provides an Aluminium Gear.
 * <li>A tier could be an item provider, such as "EV," which when passed the "circuit" material type, provides an EV
 * circuit.
 * </ul>
 *
 * @author serenibyss
 * @since 1.0
 */
@FunctionalInterface
public interface IItemProvider {

    ItemStack get(IMaterialType type);
}
