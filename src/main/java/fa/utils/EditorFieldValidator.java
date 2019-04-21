package fa.utils;

import java.util.function.Function;

public class EditorFieldValidator {
  private Function<String, Boolean> validator;
  private String errorText;

  public EditorFieldValidator(Function<String, Boolean> validator, String errorText) {
    this.validator = validator;
    this.errorText = errorText;
  }

  public boolean validate(String str) {
    return validator.apply(str);
  }

  public String getErrorText() {
    return errorText;
  }

  public static EditorFieldValidator requireNonEmpty() {
    return new EditorFieldValidator(
      val -> val != null && val.matches(".+"),
      "Can not be empty"
    );
  }

  public static EditorFieldValidator requireLettersAndSpaceOnly() {
    return new EditorFieldValidator(
      val -> val.matches("^[A-Za-z ]*$"),
      "Can only contain letters and spaces"
    );
  }
}
