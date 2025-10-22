package com.aero.pages;

import com.aero.annotations.Path;
import com.aero.common.AbsCommon;
import com.aero.utils.PropertyLoader;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import org.openqa.selenium.WebDriver;

@SuppressFBWarnings(
        value = "THROWS_METHOD_THROWS_RUNTIMEEXCEPTION",
        justification = "Метод выбрасывает RuntimeException намеренно, в тестах безопасно"
)
public abstract class AbsBasePage<T extends AbsBasePage<T>> extends AbsCommon {
  public AbsBasePage(WebDriver driver) {
    super(driver);
  }

  public T openPage() {
    driver.get(PropertyLoader.getBaseUrl() + getPath());
    driver.navigate().refresh();
    return (T) this;
  }

  private String getPath() {
    Class clazz = this.getClass();
    if (clazz.isAnnotationPresent(Path.class)) {
      Path path = (Path) clazz.getDeclaredAnnotation(Path.class);
      return path.value();
    }
    throw new RuntimeException(String.valueOf(clazz));
  }
}
