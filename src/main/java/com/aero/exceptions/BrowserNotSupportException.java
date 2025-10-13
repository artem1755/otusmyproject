package com.aero.exceptions;

public class BrowserNotSupportException extends RuntimeException {
  public BrowserNotSupportException() {
    super("Browser is not supported");
  }
}
