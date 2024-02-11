export function PasswordValidator(password: string) {
  const flags = {
    passwordLength: false,
    containsNumber: false,
    containsSpecSymbol: false,
    containsCapitalizeLetter: false,
  };

  let trimmedPassword = password.replaceAll(" ", "");

  // if password is empty, we set "false" to all flags. There's nothing to validate
  if (trimmedPassword.length === 0) {
    return flags;
  }

  const nums = password.replace(/[^\d]/g, "");
  const SPEC_CHARS = "!@#$%^&*()_+-={}[]:;\"'`,./<>?~";

  flags.passwordLength =
    trimmedPassword.length > 5 && trimmedPassword.length < 101; // set true, if password length > 5 and < 101

  flags.containsNumber = nums.length > 0; // set true, if password contains any number

  flags.containsCapitalizeLetter =
    trimmedPassword.toLowerCase() !== trimmedPassword; // set true, if password contains a capitalize letter

  flags.containsSpecSymbol = false; // reset to false before checking if password contains a spec symbol

  for (const ch of trimmedPassword) {
    if (SPEC_CHARS.includes(ch)) {
      flags.containsSpecSymbol = true;

      break;
    }
  }

  return flags;
}
