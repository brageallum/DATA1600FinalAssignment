package fa.utils.validation;

import fa.models.DB;

import java.util.Arrays;
import java.util.function.Function;

public class StringValidator extends Validator<String> {
  public StringValidator(Function<String, Boolean> validator, String errorText) {
    super(validator, errorText);
  }

  public static StringValidator requireNonEmpty() {
    return new StringValidator(
      val -> val != null && val.matches(".+"),
      "Can not be empty"
    );
  }

  public static StringValidator requireLettersAndSpaceOnly() {
    return new StringValidator(
      val -> val.matches("^[A-Za-z ]*$"),
      "Can only contain letters and spaces"
    );
  }

  public static StringValidator requireNumbersOnly() {
    return new StringValidator(
      val -> val.matches("^[0-9]*$"),
      "Can only contain numbers"
    );
  }

  public static StringValidator requireLength(int length) {
    return new StringValidator(
      val -> val.length() == length,
      "Has to be of length " + length
    );
  }

  public static StringValidator requireValidEmail() {
    return new StringValidator(
      val -> val.matches("^[\\w.-]+@\\w+\\.[A-Za-z]+$"),
      "Not a valid email"
    );
  }

  public static StringValidator requireValidSector() {
    return new StringValidator(
      val -> Arrays.stream(DB.sectorOptions.values()).anyMatch(t -> t.name().equals(val)),
      "Not a valid sector"
    );
  }
}
