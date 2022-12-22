package isalab.ndhu.encryption;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;

import org.apache.xml.security.encryption.XMLCipher;
import org.apache.xml.security.encryption.EncryptedData;
import org.apache.xml.security.utils.EncryptionConstants;
import org.apache.xml.security.encryption.EncryptedKey;
import org.apache.xml.security.keys.KeyInfo;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

public class AlgorithmTripleDES implements XMLSecurityInterface
{
   static
   {
      org.apache.xml.security.Init.init(); // initialize the xml-security library
   }
   public Document encrypt(Document document, String xpathExpression, boolean content, byte[] key, EncryptedKey encKey) throws Exception
   {
      DESedeKeySpec keySpec = new DESedeKeySpec(key);
      SecretKeyFactory skf  = SecretKeyFactory.getInstance("DESede");
      SecretKey secretKey = skf.generateSecret(keySpec);

      String algorithmURI = XMLCipher.TRIPLEDES;
      XMLCipher xmlCipher = XMLCipher.getInstance(algorithmURI);
      xmlCipher.init(XMLCipher.ENCRYPT_MODE, secretKey);

      XPathFactory xpathFactory = XPathFactory.newInstance();
      XPath xpath = xpathFactory.newXPath();
      NodeList nodes = (NodeList) xpath.evaluate(xpathExpression, document, XPathConstants.NODESET);
      
      int num = nodes.getLength();
      EncryptedData encryptedData = xmlCipher.getEncryptedData();
      
      for (int i = 0; i < num; i++)
      {
         if (encKey != null)
         {           
            KeyInfo keyInfo = new KeyInfo(document);
            keyInfo.add(encKey);
            encryptedData.setKeyInfo(keyInfo);
         }
         
         xmlCipher.doFinal(document, (Element) nodes.item(i), content);
      }

      return document;
   }

   public void encrypt(String filename, String element, boolean content, byte[] key, EncryptedKey encKey) throws Exception
   {
      DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
      dbf.setIgnoringElementContentWhitespace(true);
      DocumentBuilder db = dbf.newDocumentBuilder();
      Document document = db.parse(new File(filename));
      encrypt(document, element, content, key, encKey);
   }

   public Document decrypt(Document document, byte[] key, XMLCipher xmlCipher) throws Exception
   {
      if (key != null)
      {
         DESedeKeySpec keySpec = new DESedeKeySpec(key);
         SecretKeyFactory skf  = SecretKeyFactory.getInstance("DESede");
         SecretKey secretKey = skf.generateSecret(keySpec);
         xmlCipher.init(XMLCipher.DECRYPT_MODE, secretKey);
      }

      NodeList nodes = document.getElementsByTagNameNS(

                          EncryptionConstants.EncryptionSpecNS, EncryptionConstants._TAG_ENCRYPTEDDATA);
      int num = nodes.getLength();
      
      for (int i = 0; i < num; i++)
      {
         xmlCipher.doFinal(document, (Element) nodes.item(0));
      }
      
      return document;
   }

   public void decrypt(String filename, byte[] key, XMLCipher xmlCipher) throws Exception
   {
      DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
      dbf.setIgnoringElementContentWhitespace(true);
      DocumentBuilder db = dbf.newDocumentBuilder();
      Document document = db.parse(new File(filename));
      decrypt(document, key, xmlCipher);
   }
}