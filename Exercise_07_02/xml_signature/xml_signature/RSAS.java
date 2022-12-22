import java.io.*;
import java.math.BigInteger;

import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;





public class RSAS {


	public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeySpecException, IOException {
		KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
		kpg.initialize(512);
		KeyPair kp = kpg.genKeyPair();
		Key publicKey = kp.getPublic();
		Key privateKey = kp.getPrivate();

		KeyFactory fact = KeyFactory.getInstance("RSA");
		RSAPublicKeySpec pub = fact.getKeySpec(kp.getPublic(),RSAPublicKeySpec.class);
		RSAPrivateKeySpec priv = fact.getKeySpec(kp.getPrivate(),RSAPrivateKeySpec.class);
		System.out.println("************");
		saveToFile("RSA_pub.key", pub.getModulus(),pub.getPublicExponent());
		saveToFile("RSA_prv.key", priv.getModulus(),priv.getPrivateExponent());
		

		
	}

	private static void saveToFile(String fileName,
			  BigInteger mod, BigInteger exp) throws IOException {
		  ObjectOutputStream oout = new ObjectOutputStream(
		    new BufferedOutputStream(new FileOutputStream(fileName)));
		  try {
		    oout.writeObject(mod);
		    oout.writeObject(exp);
		  } catch (Exception e) {
		    throw new IOException("Unexpected error", e);
		  } finally {
		    oout.close();
		  }
		}






}
