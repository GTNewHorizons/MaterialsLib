package com.gtnewhorizons.materialslib;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.filter.AbstractFilter;

import com.gtnewhorizons.materialslib.config.ConfigHolder;

public class MaterialLog {

    /** Standard Logger. */
    public static final Logger out = LogManager.getLogger(MaterialsLib.MODNAME);
    /** Debug Logger - Only logs if {@link ConfigHolder#debug} is enabled, no checks needed! */
    public static final Logger debug = LogManager.getLogger(MaterialsLib.MODNAME + "/debug");
    /** Test Logger - Only for internal use during development */
    public static final Logger testing = LogManager.getLogger(MaterialsLib.MODNAME + "/testing");

    static {
        updateDebugLog();
    }

    private static void updateDebugLog() {
        var logger = (org.apache.logging.log4j.core.Logger) debug;
        logger.addFilter(new DebugLogFilter());
    }

    private static class DebugLogFilter extends AbstractFilter {

        @Override
        public Result filter(LogEvent event) {
            return ConfigHolder.debug ? Result.ACCEPT : Result.DENY;
        }
    }
}
