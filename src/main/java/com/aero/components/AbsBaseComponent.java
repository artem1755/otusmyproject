package com.aero.components;

import com.aero.common.AbsCommon;
import com.aero.scoped.GuiceScoped;
import org.openqa.selenium.WebDriver;

public abstract class AbsBaseComponent extends AbsCommon {
  public AbsBaseComponent(GuiceScoped guiceScoped){
    super(guiceScoped);
  }
}
