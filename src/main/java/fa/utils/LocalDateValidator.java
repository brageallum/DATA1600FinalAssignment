package fa.utils;

import java.time.LocalDate;
import java.util.function.Function;

public class LocalDateValidator extends Validator<LocalDate>{
  public LocalDateValidator(Function<LocalDate, Boolean> validator, String errorText) {
    super(validator, errorText);
  }

  public static LocalDateValidator requireNonEmpty() {
    return new LocalDateValidator(
      val -> val != null,
      "Can not be empty"
    );
  }

  public static LocalDateValidator requireAge(int age) {
    return new LocalDateValidator(
      val -> val.isBefore(LocalDate.now().minusYears(age)),
      String.format("Must be over %s years old", age)
    );
  }
}
