package com.aero.modules;

import com.aero.components.HeaderComponent;
import com.aero.context.TestContext;
import com.aero.pages.CourseDetailPage;
import com.aero.pages.CoursesPage;
import com.aero.pages.MainPage;
import com.aero.scoped.GuiceScoped;
import com.google.inject.AbstractModule;
import com.google.inject.Singleton;

public class TestModule extends AbstractModule {
  @Override
  protected void configure() {
    bind(TestContext.class).in(Singleton.class);

    // Пример для твоих страниц
    bind(GuiceScoped.class).in(Singleton.class);
    bind(MainPage.class).in(Singleton.class);
    bind(CoursesPage.class).in(Singleton.class);
    bind(CourseDetailPage.class).in(Singleton.class);
    bind(HeaderComponent.class).in(Singleton.class);
  }
}
