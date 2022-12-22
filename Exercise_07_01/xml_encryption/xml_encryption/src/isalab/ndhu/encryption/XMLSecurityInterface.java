package isalab.ndhu.encryption;

import org.w3c.dom.Document;

import org.apache.xml.security.encryption.EncryptedKey;
import org.apache.xml.security.encryption.XMLCipher;

public interface XMLSecurityInterface
{
   public Document encrypt(Document document, String xpathExpression, boolean content, byte[] key, EncryptedKey encKey) throws Exception;
   public Document decrypt(Document document, byte[] key, XMLCipher xmlCipher) throws Exception;
}