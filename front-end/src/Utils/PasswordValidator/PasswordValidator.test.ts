import { PasswordValidator } from "./PasswordValidator";

describe("PasswordValidator function", () => {
  test("All flags contain true value, if password is OK", () => {
    expect(PasswordValidator("Abcdefg1_")).toEqual({
      passwordLength: true,
      containsNumber: true,
      containsSpecSymbol: true,
      containsCapitalizeLetter: true,
    });
  });

  test("All flags contain false value, if password length = 0", () => {
    expect(PasswordValidator("")).toEqual({
      passwordLength: false,
      containsNumber: false,
      containsSpecSymbol: false,
      containsCapitalizeLetter: false,
    });
  });

  test('The "passwordLength" must be false, if password length less than 6 symbols after removing ALL spaces', () => {
    expect(PasswordValidator("  Qwe    r t   ").passwordLength).toBe(false);
  });

  test('The "containsNumber" flag equal "false" if there is not any number in password', () => {
    expect(PasswordValidator("Qwerty_")).toEqual({
      passwordLength: true,
      containsNumber: false,
      containsSpecSymbol: true,
      containsCapitalizeLetter: true,
    });

    expect(PasswordValidator("  Qwerty_  ")).toEqual({
      passwordLength: true,
      containsNumber: false,
      containsSpecSymbol: true,
      containsCapitalizeLetter: true,
    });
  });

  test('The "containsSpecSymbol" flag equal "false" if there is not any spec-symbol in password', () => {
    expect(PasswordValidator("Qwerty123")).toEqual({
      passwordLength: true,
      containsNumber: true,
      containsSpecSymbol: false,
      containsCapitalizeLetter: true,
    });

    expect(PasswordValidator("  Qwerty123  ")).toEqual({
      passwordLength: true,
      containsNumber: true,
      containsSpecSymbol: false,
      containsCapitalizeLetter: true,
    });
  });
});
