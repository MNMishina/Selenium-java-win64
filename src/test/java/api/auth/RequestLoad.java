package api.auth;

public abstract class RequestLoad {
    public static String authSignInFast(String phoneNumber)
    {
        String authSignInFastReturn = "{\r\n" +
                " \"phone\":\""+phoneNumber+"\" " +
                "}";

        return authSignInFastReturn;
    }
}
