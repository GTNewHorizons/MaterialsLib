package com.gtnewhorizons.materialslib.api.unifier;

import java.util.Objects;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

import org.jetbrains.annotations.NotNull;

/**
 * @author serenibyss
 * @since 1.0
 */
final class ItemAndMetadata {

    private final @NotNull Item item;
    private final int damage;

    public ItemAndMetadata(@NotNull Item item, int damage) {
        this.item = item;
        this.damage = damage;
    }

    public ItemAndMetadata(@NotNull ItemStack stack) {
        Objects.requireNonNull(stack.getItem());
        this.item = stack.getItem();
        this.damage = stack.getItemDamage();
    }

    public ItemStack toItemStack() {
        return toItemStack(1);
    }

    public ItemStack toItemStack(int amount) {
        return new ItemStack(item, amount, damage);
    }

    public boolean isWildcard() {
        return this.damage == OreDictionary.WILDCARD_VALUE;
    }

    public ItemAndMetadata toWildcard() {
        return this.isWildcard() ? this : new ItemAndMetadata(item, OreDictionary.WILDCARD_VALUE);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ItemAndMetadata that = (ItemAndMetadata) o;

        if (damage != that.damage) return false;
        return item == that.item;
    }

    @Override
    public int hashCode() {
        return 31 * item.hashCode() + damage;
    }

    @Override
    public String toString() {
        return "ItemAndMetadata{item=" + item.getUnlocalizedName(toItemStack()) + "}";
    }
}
