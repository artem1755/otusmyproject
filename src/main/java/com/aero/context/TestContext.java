package com.aero.context;

import com.aero.models.CourseDTO;
import io.cucumber.guice.ScenarioScoped;
import java.util.ArrayList;
import java.util.List;

@ScenarioScoped
public class TestContext {
  private String selectedCourse;

  private List<CourseDTO> courses;

  public String getSelectedCourse() {
    return selectedCourse;
  }

  public void setSelectedCourse(String selectedCourse) {
    this.selectedCourse = selectedCourse;
  }

  public List<CourseDTO> getCourses() {
    return List.copyOf(courses);
  }

  public void setCourses(List<CourseDTO> courses) {
    this.courses = courses != null ? new ArrayList<>(courses) : new ArrayList<>();
  }
}
