package com.gtnewhorizons.materialslib.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import com.gtnewhorizons.materialslib.api.MaterialsAPI;
import com.gtnewhorizons.materialslib.api.item.IMaterialItem;
import com.gtnewhorizons.materialslib.api.material.Material;
import com.gtnewhorizons.materialslib.api.registry.IMaterialRegistry;
import com.gtnewhorizons.materialslib.api.type.IMaterialType;

public class MetaMaterialItem extends Item implements IMaterialItem {

    private final IMaterialRegistry registry;
    private final IMaterialType<Item> type;

    public MetaMaterialItem(@NotNull IMaterialRegistry registry, @NotNull IMaterialType<Item> type) {
        super();
        this.registry = registry;
        this.type = type;
        setHasSubtypes(true);
        setMaxDamage(MaterialsAPI.REGISTRY_MAXIMUM);
        // todo creativetab
    }

    @Override
    public @Nullable Material getMaterial(@NotNull ItemStack stack) {
        if (stack.getItem() != this) return null;
        return registry.getMaterial(stack.getItemDamage());
    }

    @Override
    public @Nullable Material getMaterial(int metadata) {
        if (metadata < 0 || metadata > MaterialsAPI.REGISTRY_MAXIMUM) {
            throw new IllegalArgumentException();
        }
        return registry.getMaterial(metadata);
    }

    @Override
    public @NotNull IMaterialType<Item> getType() {
        return type;
    }

    @Override
    public @NotNull IMaterialRegistry getOwnerRegistry() {
        return registry;
    }
}
