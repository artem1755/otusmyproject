package com.aero.components;

import com.aero.common.AbsCommon;
import org.openqa.selenium.WebDriver;

public abstract class AbsBaseComponent extends AbsCommon {
  public AbsBaseComponent(WebDriver driver){
    super(driver);
  }
}
