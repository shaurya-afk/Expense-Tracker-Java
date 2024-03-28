import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

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

    public static String HashPassword(String password1)
    {
        try{
            MessageDigest digest = MessageDigest.getInstance("SHA-256");

            byte[] hashedByte = digest.digest(password1.getBytes());
            StringBuilder builder = new StringBuilder();
            for(byte b:hashedByte)
            {
                builder.append(String.format("%02x",b));
            }
            return builder.toString();
        }catch (NoSuchAlgorithmException e)
        {
            System.out.println(e.getMessage());
            return null;
        }
    }

    void CheckCredentials() throws AuthenticationException
    {
        String adminUsername = DatabaseConnection.getUsername();

        String adminPassword = DatabaseConnection.getPassword(adminUsername);
        String storedHashPwd = HashPassword(password);

        if (!username.equals(adminUsername) || !storedHashPwd.equals(adminPassword))
        {
            throw new AuthenticationException("Invalid Username or Password\n");
        }else {
            MainMenuNaive.mainMenuDriver();
        }
    }

}