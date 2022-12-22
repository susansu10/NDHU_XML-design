package isalab.ndhu.signature;

import org.w3c.dom.Document;

public interface XMLSignatureInterface
{
  public Document sign(Document doc, byte[] pubKey, byte[] signingKey, String xpath) throws Exception;
  public void verify(Document doc) throws Exception;  
}