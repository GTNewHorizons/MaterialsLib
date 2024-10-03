package com.gtnewhorizons.materialslib.api.event;

import cpw.mods.fml.common.eventhandler.Event;

/**
 * An {@link Event event} fired to register new {@link com.gtnewhorizons.materialslib.api.material.Material Materials}.
 * You should only register Materials to your own registry. This will be fired second, after
 * {@link MaterialRegistryEvent}.
 *
 * @author serenibyss
 * @since 1.0
 */
public class MaterialEvent extends Event {

    public MaterialEvent() {
        super();
    }
}
