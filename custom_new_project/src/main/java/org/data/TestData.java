package org.data;

import org.propertiesInterfaces.ConfigProvider;

public class TestData {
    public final static String LOGIN_DEFAULT_API = System.getProperty("defaultLogin", ConfigProvider.configHiddenProperties.login());
    public final static String PASSWORD_DEFAULT_API = System.getProperty("defaultPassword", ConfigProvider.configHiddenProperties.password());

}
