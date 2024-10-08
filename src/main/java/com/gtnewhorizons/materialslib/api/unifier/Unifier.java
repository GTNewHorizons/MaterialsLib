package com.gtnewhorizons.materialslib.api.unifier;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

import com.gtnewhorizons.materialslib.MaterialLog;
import com.gtnewhorizons.materialslib.api.material.Material;
import com.gtnewhorizons.materialslib.api.type.IMaterialType;

import it.unimi.dsi.fastutil.objects.Object2ObjectMap;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;

/**
 * Utility class for handling {@link IMaterialType} + {@link Material} -> {@link ItemStack} associations.
 * <p/>
 * Also handles Material relations to the {@link OreDictionary}.
 *
 * @author serenibyss
 * @since 1.0
 */
public final class Unifier {

    private Unifier() {
        throw new UnsupportedOperationException();
    }

    private static final Object2ObjectMap<UnifierEntry, ItemAndMetadata> UD = new Object2ObjectOpenHashMap<>();

    /**
     * Register a generated {@link Material} item for the provided {@link IMaterialType}.
     * Also registers the provided item to the {@link OreDictionary}.
     * This will be called automatically, so this should not be called externally.
     */
    @ApiStatus.Internal
    public static void register(ItemStack stack, @NotNull IMaterialType<ItemStack> type, @NotNull Material material) {
        if (stack == null || stack.getItem() == null) {
            MaterialLog.debug.error("Null stack passed to unifier register --- type: {}, material: {}", type, material);
            return;
        }

        MaterialLog.debug.info("Registering unifier item --- type: {}, material{}, stack: {}", type, material, stack);
        UD.put(new UnifierEntry(type, material), new ItemAndMetadata(stack.getItem(), stack.getItemDamage()));
        OreDictionary.registerOre(type.getPrefix() + material.getUpperName(), stack.copy());
    }

    /**
     * Register a custom ItemStack override for a {@link IMaterialType} + {@link Material} pair.
     * For example, if you would rather use the Avaritia Infinity Ingot rather than a generated Infinity Ingot,
     * this method can be called to set the Avaritia ingot as the representative stack of this type + material pair.
     * <p/>
     * This method assumes that the provided item has already been registered with the {@link OreDictionary}.
     * <p/>
     * Calling this manually is usually not necessary, as this will be called by todo link the type override function
     *
     * @param stack    The custom ItemStack to associate with the provided type + material pair.
     * @param type     The item type of this ItemStack.
     * @param material The material of this ItemStack.
     */
    public static void registerCustom(ItemStack stack, @NotNull IMaterialType<ItemStack> type, @NotNull Material material) {
        if (stack == null || stack.getItem() == null) {
            MaterialLog.debug.error("Null stack passed to unifier registerCustom --- type: {}, material: {}", type, material);
            return;
        }

        MaterialLog.debug.info("Registering custom unifier item --- type: {}, material{}, stack: {}", type, material, stack);
        UD.put(new UnifierEntry(type, material), new ItemAndMetadata(stack.getItem(), stack.getItemDamage()));
    }

    public static ItemStack get(IMaterialType<ItemStack> type, Material material) {
        return get(new UnifierEntry(type, material), 1);
    }

    public static ItemStack get(IMaterialType<ItemStack> type, Material material, int amount) {
        return get(new UnifierEntry(type, material), amount);
    }

    public static ItemStack get(UnifierEntry entry) {
        return get(entry, 1);
    }

    public static ItemStack get(UnifierEntry entry, int amount) {
        ItemAndMetadata data = UD.get(entry);
        if (data == null) return null;
        return data.toItemStack(amount);
    }
}
