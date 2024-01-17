import { AuthAPI } from "./AuthAPI";

describe('AuthAPI', () => {
  const token = 'alalalala';

  it('Should update token in headers', () => {
    AuthAPI.setTokenToHeader(token);

    const result = AuthAPI.headers;

    expect(result.Authorization).toBe(`Bearer ${token}`)
  })
})