class AuthenticationException extends Exception
{
    AuthenticationException(String message)
    {
        super(message);
    }
}
public class Authenticator
{
    protected String username, password;
    Authenticator(String uName, String passwrd)
    {
        this.username = uName;
        this.password = passwrd;
    }

    void CheckCredentials() throws AuthenticationException
    {
        String adminPassword = "niam";
        String adminUsername = "system";
        if (!username.equals(adminUsername) || !password.equals(adminPassword))
        {
            throw new AuthenticationException("Invalid Username or Password");
        }else {
            MainMenuNaive.mainMenuDriver();
        }
    }

}