package com.tenniscourts.exceptions;

/**
 * The type Entity not found exception.
 */
public class EntityNotFoundException extends RuntimeException {
  /**
   * Instantiates a new Entity not found exception.
   *
   * @param msg the msg
   */

  private static final String MESSAGE_DEFAULT = "Tennis Court not found.";

  public EntityNotFoundException(String msg) {
      super(msg);
  }

  public EntityNotFoundException() {
     super(MESSAGE_DEFAULT);
  }

}
