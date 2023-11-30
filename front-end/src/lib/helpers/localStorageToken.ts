export function setAuthToken(token: string) {
    localStorage.setItem("authToken", token);
}
export function getAuthToken() {
    const defaultToken = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyMUBleGFtcGxlLmNvbSIsImlhdCI6MTcwMTMzNTQyMiwiZXhwIjo0ODU0OTM1NDIyfQ.E3mjB65U_xU5ET_gl61HnZ62yLgvT3NrN4GNAKym1AA";
    return localStorage.getItem("authToken") || defaultToken;
}