package org.propertiesInterfaces;

import org.aeonbits.owner.Config;

@Config.Sources(value = "file:./src/main/resources/properties/testLink.properties")

public interface TestLinkConfigProperties extends Config {

	String TEST_PROJECT_NAME();
	String TEST_PLAN_NAME();
	String BUILD_NAME();
	String TEST_CASE_PREFIX();

}
