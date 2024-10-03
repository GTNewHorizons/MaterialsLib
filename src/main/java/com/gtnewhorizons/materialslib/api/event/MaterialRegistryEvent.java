package com.gtnewhorizons.materialslib.api.event;

import com.gtnewhorizons.materialslib.api.registry.IMaterialRegistryManager;
import cpw.mods.fml.common.eventhandler.Event;

/**
 * An {@link Event Event} fired to register new
 * {@link com.gtnewhorizons.materialslib.api.registry.IMaterialRegistry Material Registries} to the Manager. Subscribe
 * to this event to get your own Material Registry. Will be called before any other Material-related events.
 *
 * @see IMaterialRegistryManager#createRegistry(String)
 * @author serenibyss
 * @since 1.0
 */
public class MaterialRegistryEvent extends Event {

    private final IMaterialRegistryManager manager;

    public MaterialRegistryEvent(IMaterialRegistryManager manager) {
        this.manager = manager;
    }

    public IMaterialRegistryManager getManager() {
        return manager;
    }
}
