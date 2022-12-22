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

import org.apache.xml.security.signature.XMLSignature;
import org.apache.xml.security.transforms.Transforms;
import org.apache.xml.security.transforms.params.XPathContainer;
import org.apache.xml.security.utils.Constants;
import org.apache.xml.security.utils.XMLUtils;

import javax.xml.transform.TransformerFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

public class CreateSignature
{
   public static void main(String args[]) throws Exception
   {
      Constants.setSignatureSpecNSprefix("");
      File signatureFile = new File("signature.xml");

      //get the private key for signing.
      FileInputStream keyFile = new FileInputStream("RSA.prv");
      byte[] prikey = new byte[keyFile.available()];
      keyFile.read(prikey);
      keyFile.close();

      PKCS8EncodedKeySpec priKeySpec = new PKCS8EncodedKeySpec(prikey);
      KeyFactory keyFactory = KeyFactory.getInstance("RSA");
      PrivateKey privateKey = keyFactory.generatePrivate(priKeySpec);
      
      FileInputStream keyFile2 = new FileInputStream("RSA.pub");
      byte[] keyEncKey = new byte[keyFile2.available()];
      
      keyFile2.read(keyEncKey);
      keyFile2.close();


      X509EncodedKeySpec pubKeySpec = new X509EncodedKeySpec(keyEncKey);
      keyFactory = KeyFactory.getInstance("RSA");
      PublicKey publicKey = keyFactory.generatePublic(pubKeySpec);

      DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

      //XML Signature needs to be namespace aware
      dbf.setNamespaceAware(true);

      dbf.setIgnoringElementContentWhitespace(true);
      DocumentBuilder db = dbf.newDocumentBuilder();
      Document doc = db.parse(new File("Demo.xml"));

      //The BaseURI is the URI that's used to prepend to relative URIs
      String BaseURI = signatureFile.toURI().toURL().toString();
      //Create an XML Signature object from the document, BaseURI and
      //signature algorithm
      XMLSignature sig = new XMLSignature(doc, BaseURI, XMLSignature.ALGO_ID_SIGNATURE_RSA_SHA1);

      Element root = doc.getDocumentElement();
      root.appendChild(sig.getElement());

      {
         //create the transforms object for the Document/Reference
         Transforms transforms = new Transforms(doc);
                 
         XPathContainer xc = new XPathContainer(doc);
         String xpath = "ancestor-or-self::node() = /addressbook/name/address";
         xc.setXPath(xpath);
         transforms.addTransform(Transforms.TRANSFORM_XPATH, xc.getElementPlusReturns());
                 
         //First we have to strip away the signature element (it's not part of the
         //signature calculations). The enveloped transform can be used for this.
         transforms.addTransform(Transforms.TRANSFORM_ENVELOPED_SIGNATURE);
         //Part of the signature element needs to be canonicalized. It is a kind
         //of normalizing algorithm for XML. For more information please take a
         //look at the W3C XML Digital Signature webpage.
         transforms.addTransform(Transforms.TRANSFORM_C14N_WITH_COMMENTS);
         //Add the above Document/Reference
         sig.addDocument("", transforms, Constants.ALGO_ID_DIGEST_SHA1);
      }

      {
         //Add this public key to the KeyInfo.
         sig.addKeyInfo(publicKey);

         System.out.println("First signer: Start signing");
         sig.sign(privateKey);
         System.out.println("First signer: Finished signing");
      }
      
      /*XMLSignature sig2 = new XMLSignature(doc, BaseURI, XMLSignature.ALGO_ID_SIGNATURE_RSA_SHA1);

      Element root2 = doc.getDocumentElement();
      root2.appendChild(sig2.getElement());

      {
         //create the transforms object for the Document/Reference
         Transforms transforms2 = new Transforms(doc);
                 
         XPathContainer xc2 = new XPathContainer(doc);
         String xpath2 = "ancestor-or-self::node() = /addressbook/name[2]";
         xc2.setXPath(xpath2);
         transforms2.addTransform(Transforms.TRANSFORM_XPATH, xc2.getElementPlusReturns());
                 
         //First we have to strip away the signature element (it's not part of the
         //signature calculations). The enveloped transform can be used for this.
         transforms2.addTransform(Transforms.TRANSFORM_ENVELOPED_SIGNATURE);
         //Part of the signature element needs to be canonicalized. It is a kind
         //of normalizing algorithm for XML. For more information please take a
         //look at the W3C XML Digital Signature webpage.
         transforms2.addTransform(Transforms.TRANSFORM_C14N_WITH_COMMENTS);
         //Add the above Document/Reference
         sig2.addDocument("", transforms2, Constants.ALGO_ID_DIGEST_SHA1);
      }

      {
         //Add this public key to the KeyInfo.
         sig2.addKeyInfo(publicKey);

         System.out.println("Second signer: Start signing");
         sig2.sign(privateKey);
         System.out.println("Second signer: Finished signing");
      }
      */

      FileOutputStream f = new FileOutputStream(signatureFile);

      XMLUtils.outputDOMc14nWithComments(doc, f);

      f.close();
      System.out.println("Wrote signature to " + BaseURI);
   }

   static
   {
      org.apache.xml.security.Init.init();
   }
}
