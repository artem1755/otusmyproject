package com.aero.utils;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public final class StringUtils {

  private StringUtils() {
  }

  public static String removeBrackets(String text) {
    if (text == null) {
      throw new RuntimeException("Входной параметр null");
    }
    return text.replaceAll("\\s*\\(\\d+\\)$", "").trim();
  }

  public static String extractDatePart(String rawText) {
    if (rawText == null || rawText.isBlank()) {
      throw new IllegalArgumentException("Строка с датой пуста");
    }

    return rawText.split("·")[0].trim(); // всё до точки
  }

  public static LocalDate parseDate(String dateText) {
    if (dateText == null || dateText.isBlank()) {
      throw new IllegalArgumentException("Строка с датой пуста");
    }

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMMM, yyyy", new Locale("ru"));
    return LocalDate.parse(dateText.trim(), formatter);
  }

  public static String formatToDayMonth(LocalDate date) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMMM", new Locale("ru"));
    return date.format(formatter);
  }

  public static String extractPath(String url) {
    try {
      URL parsedUrl = new java.net.URL(url);
      return parsedUrl.getPath();
    } catch (Exception e) {
      throw new IllegalArgumentException("Некорректный URL: " + url, e);
    }
  }
}
