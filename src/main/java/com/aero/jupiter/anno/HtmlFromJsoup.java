package com.aero.jupiter.anno;

import com.aero.jupiter.extensions.JsoupExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@ExtendWith(JsoupExtension.class)
public @interface HtmlFromJsoup {
  String url();
  String cssQuery() default "";
}
