package com.pprice.application.usecases;

public abstract class UseCase<P extends UseCaseParams, R> {

  public UseCase() {
  }

  public R execute(P params) {
    try {
      params.validate();

      R result = doOperation(params);

      return result;
    } catch (Exception cause) {
      throw cause;
    }
  }

  protected abstract R doOperation(P params);

}
