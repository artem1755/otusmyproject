package com.aero;

import static io.cucumber.core.options.Constants.GLUE_PROPERTY_NAME;

import io.cucumber.junit.platform.engine.Cucumber;
import org.junit.platform.suite.api.*;

@Suite
@IncludeEngines("cucumber")
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "com.aero.steps,com.aero.hooks")
@IncludeTags({"courses_block"})
@SelectClasspathResource("otus")
@Cucumber
public class RunnerTest {
}
