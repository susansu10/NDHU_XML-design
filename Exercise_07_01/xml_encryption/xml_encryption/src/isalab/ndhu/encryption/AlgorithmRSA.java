package isalab.ndhu.encryption;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.xml.security.encryption.XMLCipher;
import org.apache.xml.security.encryption.EncryptedKey;

import org.w3c.dom.Document;

import java.security.spec.X509EncodedKeySpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.PrivateKey;

import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.SecretKeyFactory;
import javax.crypto.SecretKey;

public class AlgorithmRSA implements KeySecurityInterface
{
   static
   {
      org.apache.xml.security.Init.init(); // initialize the xml-security library
   }
   public EncryptedKey encrypt(Document document, byte[] kek, byte[] key, String algorithm) throws Exception
   {
      X509EncodedKeySpec pubKeySpec = new X509EncodedKeySpec(kek);
      KeyFactory keyFactory = KeyFactory.getInstance("RSA");
      PublicKey pubkey = keyFactory.generatePublic(pubKeySpec);

      DESedeKeySpec keySpec = new DESedeKeySpec(key);
      SecretKeyFactory skf  = SecretKeyFactory.getInstance(algorithm);
      SecretKey secretKey = skf.generateSecret(keySpec);

      String algorithmURI = XMLCipher.RSA_v1dot5;
      XMLCipher keyCipher = XMLCipher.getInstance(algorithmURI);
      keyCipher.init(XMLCipher.WRAP_MODE, pubkey);
      EncryptedKey encryptedKey = keyCipher.encryptKey(document, secretKey);

      return encryptedKey;
   }

   public void encrypt(String filename, byte[] kek, byte[] key, String algorithm) throws Exception
   {
      DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
      dbf.setIgnoringElementContentWhitespace(true);
      DocumentBuilder db = dbf.newDocumentBuilder();
      Document document = db.parse(new File(filename));
      encrypt(document, kek, key, algorithm);
   }

   public XMLCipher decrypt(Document document, byte[] kek) throws Exception
   {
      PKCS8EncodedKeySpec priKeySpec = new PKCS8EncodedKeySpec(kek);
      KeyFactory keyFactory = KeyFactory.getInstance("RSA");
      PrivateKey prikey = keyFactory.generatePrivate(priKeySpec);

      XMLCipher xmlCipher = XMLCipher.getInstance();

      xmlCipher.init(XMLCipher.DECRYPT_MODE, null);
      xmlCipher.setKEK(prikey);

      return xmlCipher;
   }

   public void decrypt(String filename, byte[] kek) throws Exception
   {
      DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
      dbf.setIgnoringElementContentWhitespace(true);
      DocumentBuilder db = dbf.newDocumentBuilder();
      Document document = db.parse(new File(filename));
      decrypt(document, kek);
   }
}