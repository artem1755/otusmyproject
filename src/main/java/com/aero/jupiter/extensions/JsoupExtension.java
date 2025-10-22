package com.aero.jupiter.extensions;

import com.aero.jupiter.anno.HtmlFromJsoup;
import com.aero.utils.PropertyLoader;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolver;

import java.util.Optional;

public class JsoupExtension implements BeforeEachCallback, ParameterResolver {

  private String pageText;

  @Override
  public void beforeEach(ExtensionContext context) throws Exception {
    HtmlFromJsoup annotation = context.getTestMethod()
            .flatMap(m -> Optional.ofNullable(m.getAnnotation(HtmlFromJsoup.class)))
            .orElseThrow(() -> new RuntimeException("@HtmlFromJsoup annotation is required"));

    String url = annotation.url();
    String cssQuery = annotation.cssQuery();
    Document doc = Jsoup.connect(PropertyLoader.getBaseUrl() + url).get();
    Elements courseTitles = doc.select(cssQuery);

    Optional<String> firstTitle = courseTitles.stream()
            .map(Element::text)
            .findFirst();

    this.pageText = firstTitle.get();
  }

  @Override
  public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) {
    return parameterContext.getParameter().getType().equals(String.class);
  }

  @Override
  public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) {
    return pageText;
  }
}
