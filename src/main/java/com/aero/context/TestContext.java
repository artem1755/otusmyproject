package com.aero.context;

import io.cucumber.guice.ScenarioScoped;

//@Singleton
@ScenarioScoped
public class TestContext {
  private String selectedCourse;

  public String getSelectedCourse() {
    return selectedCourse;
  }

  public void setSelectedCourse(String selectedCourse) {
    this.selectedCourse = selectedCourse;
  }
}
