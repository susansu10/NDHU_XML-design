package isalab.ndhu.encryption;

import org.w3c.dom.Document;

import org.apache.xml.security.encryption.EncryptedKey;
import org.apache.xml.security.encryption.XMLCipher;

public interface KeySecurityInterface
{
   public EncryptedKey encrypt(Document document, byte[] kek, byte[] key, String algorithm) throws Exception;
   public XMLCipher decrypt(Document document, byte[] kek) throws Exception;
}