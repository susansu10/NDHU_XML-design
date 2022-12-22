import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.File;
import java.io.FileInputStream;
import java.security.PublicKey;

import org.apache.xml.security.keys.KeyInfo;
import org.apache.xml.security.signature.XMLSignature;
import org.apache.xml.security.utils.Constants;
import org.apache.xml.security.utils.XMLUtils;
import org.apache.xpath.XPathAPI;
import static java.lang.System.out;

public class VerifySignature
{

   public static void main(String args[])
   {
      String signatureFileName = "signature.xml";

      DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

      dbf.setNamespaceAware(true);

      dbf.setAttribute("http://xml.org/sax/features/namespaces", Boolean.TRUE);
      int i;
      try
      {
         File f = new File(signatureFileName);

         System.out.println("Try to verify " + f.toURI().toURL().toString());

         DocumentBuilder db = dbf.newDocumentBuilder();

         db.setErrorHandler(new org.apache.xml.security.utils.IgnoreAllErrorHandler());

         Document doc = db.parse(new java.io.FileInputStream(f));

         Element nscontext = XMLUtils.createDSctx(doc, "ds", Constants.SignatureSpecNS);
         NodeList signatureElems = XPathAPI.selectNodeList(doc, "//ds:Signature", nscontext);

         for ( i = 0; i < signatureElems.getLength(); i++)
         {
            Element sigElement = (Element) signatureElems.item(i);
            XMLSignature signature = new XMLSignature(sigElement, f.toURI().toURL().toString());

            KeyInfo ki = signature.getKeyInfo();

            if (ki != null)
            {
               PublicKey pk = signature.getKeyInfo().getPublicKey();

               if (pk != null)
               {
                  out.println("The signature number " + (i + 1) + " is "
                                     + (signature.checkSignatureValue(pk)
                                        ? "valid"
                                        : "invalid !!!!!"));
               }
               else
               {
                  System.out.println(
                     "Did not find a public key.");
               }
            }
            else
            {
               System.out.println("Did not find a KeyInfo");
            }
         }
      }
      catch (Exception ex)
      {
         //ex.printStackTrace();
    	  out.println(" is invalid");
      }
   }

   static
   {
      org.apache.xml.security.Init.init();
   }
}
