import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.SQLOutput;

public class Main
{
    public static void main(String[] args)
    {
        byte[] salt = geraSalt();
        byte[] senha = "senha".getBytes();

        byte[] hash = combinaSenhaComSalt(salt, senha);
    }

    public static byte[] geraSalt()
    {
        byte[] salt = new byte[16];

        SecureRandom secureRandom = new SecureRandom();

        secureRandom.nextBytes(salt);

        return salt;
    }

    public static byte[] combinaSenhaComSalt(byte[] salt, byte[] senha)
    {
        try
        {
            byte[] combinacao = new byte[salt.length + senha.length];

            System.arraycopy(salt, 0, combinacao, 0, salt.length);
            System.arraycopy(senha, 0, combinacao, salt.length, senha.length);

            // calcula hash
            MessageDigest md = MessageDigest.getInstance("SHA-256");

            return md.digest(combinacao);
        }
        catch(Exception e)
        {
            e.printStackTrace();

            return null;
        }
    }
}