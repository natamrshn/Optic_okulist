import { LocalStorage } from "./LocalStorageUtil";

describe('LocalStorage', () => {
  describe('getToken', () => {
    test('Should return null', () => {
      const token: string = '';

      localStorage.setItem('optic-okulist__token', token);

      expect(LocalStorage.getToken()).toBe(null);
    });

    test('Should return non-empty token', () => {
      const token: string = 'the secretToken';

      localStorage.setItem('optic-okulist__token', token);

      expect(LocalStorage.getToken()).toBe(token);
    });
  });

  describe('setToken', () => {
    test('Should set non-empty token', () => {
      const token: string = '';
      const tokenToSet: string = 'the secret token'

      localStorage.setItem('optic-okulist__token', token);

      LocalStorage.setToken(tokenToSet);
      expect(LocalStorage.getToken()).toBe(tokenToSet);
    });

    test('Should throw error', () => {
      const token: string = '';
      const tokenToSet: string = ''

      localStorage.setItem('optic-okulist__token', token);

      expect(() => LocalStorage.setToken(tokenToSet)).toThrowError();
    });
  });
});