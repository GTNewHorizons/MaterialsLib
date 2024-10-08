package com.gtnewhorizons.materialslib.api.unifier;

import net.minecraft.item.ItemStack;

import org.jetbrains.annotations.NotNull;

import com.gtnewhorizons.materialslib.api.material.Material;
import com.gtnewhorizons.materialslib.api.type.IMaterialType;

/**
 * @author serenibyss
 * @since 1.0
 */
public final class UnifierEntry {

    private final @NotNull IMaterialType<ItemStack> type;
    private final @NotNull Material material;

    public UnifierEntry(@NotNull IMaterialType<ItemStack> type, @NotNull Material material) {
        this.type = type;
        this.material = material;
    }

    public @NotNull IMaterialType<ItemStack> getType() {
        return type;
    }

    public @NotNull Material getMaterial() {
        return material;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UnifierEntry that = (UnifierEntry) o;

        if (type != that.type) return false;
        return material == that.material;
    }

    @Override
    public int hashCode() {
        return 31 * type.hashCode() + material.hashCode();
    }

    @Override
    public String toString() {
        return "UnifierEntry{type=" + type + ", material=" + material + "}";
    }
}
