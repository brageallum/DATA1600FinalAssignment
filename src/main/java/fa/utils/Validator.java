package fa.utils;

import java.util.function.Function;

public class Validator<T> {
  private Function<T, Boolean> validator;
  private String errorText;

  public Validator(Function<T, Boolean> validator, String errorText) {
    this.validator = validator;
    this.errorText = errorText;
  }

  public boolean validate(T value) {
    return validator.apply(value);
  }

  public String getErrorText() {
    return errorText;
  }
}
