package com.aero.pages;

import com.aero.annotations.Path;
import com.aero.components.HeaderComponent;
import com.aero.scoped.GuiceScoped;
import com.google.inject.Inject;

@Path("/")
public class MainPage extends AbsBasePage<MainPage> {

  @Inject
  HeaderComponent headerComponent;

  @Inject
  public MainPage(GuiceScoped guiceScoped, HeaderComponent headerComponent) {
    super(guiceScoped);
    this.headerComponent = headerComponent;
  }

  public HeaderComponent header() {
    return headerComponent;
  }
}
