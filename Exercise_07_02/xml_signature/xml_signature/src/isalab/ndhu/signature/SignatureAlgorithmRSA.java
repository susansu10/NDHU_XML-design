package isalab.ndhu.signature;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import java.security.spec.X509EncodedKeySpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.PrivateKey;

import org.apache.xml.security.keys.KeyInfo;
import org.apache.xml.security.signature.XMLSignature;
import org.apache.xml.security.transforms.Transforms;
import org.apache.xml.security.transforms.params.XPathContainer;
import org.apache.xml.security.utils.Constants;
import org.apache.xml.security.utils.XMLUtils;
import org.apache.xpath.XPathAPI;

public class SignatureAlgorithmRSA implements XMLSignatureInterface
{
   static
   {
      org.apache.xml.security.Init.init();
   }
   
   public Document sign(Document doc, byte[] pubKey, byte[] signingKey, String xpath) throws Exception
   {
      Constants.setSignatureSpecNSprefix("");

      X509EncodedKeySpec pubKeySpec = new X509EncodedKeySpec(pubKey);
      KeyFactory keyFactory = KeyFactory.getInstance("RSA");
      PublicKey publicKey = keyFactory.generatePublic(pubKeySpec);

      PKCS8EncodedKeySpec priKeySpec = new PKCS8EncodedKeySpec(signingKey);
      keyFactory = KeyFactory.getInstance("RSA");
      PrivateKey privateKey = keyFactory.generatePrivate(priKeySpec);

      XMLSignature sig = new XMLSignature(doc, Constants.SignatureSpecNS, XMLSignature.ALGO_ID_SIGNATURE_RSA_SHA1);

      Element root = doc.getDocumentElement();
      root.appendChild(sig.getElement());

      {
         Transforms transforms = new Transforms(doc);

         XPathContainer xc = new XPathContainer(doc);
         xc.setXPath(xpath);
         transforms.addTransform(Transforms.TRANSFORM_XPATH, xc.getElementPlusReturns());

         transforms.addTransform(Transforms.TRANSFORM_ENVELOPED_SIGNATURE);
         transforms.addTransform(Transforms.TRANSFORM_C14N_WITH_COMMENTS);

         sig.addDocument("", transforms, Constants.ALGO_ID_DIGEST_SHA1);
      }

      {
         sig.addKeyInfo(publicKey);

         System.out.println("Start signing");
         sig.sign(privateKey);
         System.out.println("Finished signing");
      }

      return doc;
   }

   public void verify(Document doc) throws Exception
   {
      Element nscontext = XMLUtils.createDSctx(doc, "ds", Constants.SignatureSpecNS);
      Element sigElement = (Element) XPathAPI.selectSingleNode(doc, "//ds:Signature[1]", nscontext);
      XMLSignature signature = new XMLSignature(sigElement, Constants.SignatureSpecNS);

      KeyInfo ki = signature.getKeyInfo();

      if (ki != null)
      {
         PublicKey pk = signature.getKeyInfo().getPublicKey();

         if (pk != null)
         {
            System.out.println("The XML signature " + " is "
                               + (signature.checkSignatureValue(pk)
                                  ? "valid"
                                  : "invalid !!!!!"));
         }
         else
         {
            System.out.println("Did not find a public key");
         }

      }
      else
      {
         System.out.println("Did not find a KeyInfo");
      }
   }
}