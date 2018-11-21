package utils;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class PasswordHasher {
	
	private PasswordHasher(){}
	
	public static final byte[] SALT = { 1, 2, 63, 84, 100, 7, 2 };
	public static final int ITERATIONS = 15;
	public static final int KEYLENGTH = 255;
	
	public static byte[] run( final char[] password, final byte[] salt, final int iterations, final int keyLength ) {
		byte[] result = null;
		try {
			SecretKeyFactory skf = SecretKeyFactory.getInstance( "PBKDF2WithHmacSHA512" );
			PBEKeySpec spec = new PBEKeySpec( password, salt, iterations, keyLength );
			SecretKey key = skf.generateSecret( spec );
			result = key.getEncoded( );
		} catch( NoSuchAlgorithmException | InvalidKeySpecException e ) {
			Debug.log("PasswordHasher", e.getMessage());
			throw new RuntimeException( e );
		}
		return result;
	}
	
	public static void clear(byte[] hash){
		if(hash != null)
			for(int i = 0; i < hash.length; i++)
				hash[i] = 0;
	}
	
}
