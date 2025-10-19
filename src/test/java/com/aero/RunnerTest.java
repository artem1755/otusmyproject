package com.aero;

import static io.cucumber.core.options.Constants.GLUE_PROPERTY_NAME;

import org.junit.platform.suite.api.*;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("otus")
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "otus")
@IncludeTags({"test"})
public class RunnerTest {
}
