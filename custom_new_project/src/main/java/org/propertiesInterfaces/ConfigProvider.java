package org.propertiesInterfaces;

import org.aeonbits.owner.ConfigFactory;

public class ConfigProvider {
    public static ConfigHiddenProperties configHiddenProperties = ConfigFactory.create(ConfigHiddenProperties.class);

}
