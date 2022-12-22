package isalab.ndhu.signature;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class SaveSigAlgorithmRSA
{
   public static void main(String args[]) throws Exception
   {
      SignatureAlgorithmRSA algorithm = new SignatureAlgorithmRSA();
      File AlgorithmFile = new File(args[0] + ".alg");
      ObjectOutputStream obj = new ObjectOutputStream(new FileOutputStream(AlgorithmFile));
      obj.writeObject(algorithm);
      obj.close();
      System.out.println("RSA Signature Algorithm stored in " + AlgorithmFile.toURI().toURL().toString());
   }
}