package isalab.ndhu.encryption;

import java.io.File;
import java.io.FileOutputStream;
import java.io.BufferedOutputStream;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PublicKey;
import java.security.PrivateKey;

import javax.crypto.SecretKey;
import javax.crypto.KeyGenerator;

public class GenerateKey
{
   public static void main(String[]args) throws Exception
   {
      try
      {
         if (args[0].equals("RSA"))
         {
            KeyPairGenerator kpg = KeyPairGenerator.getInstance(args[0]);
            kpg.initialize(1024);
            KeyPair kp = kpg.genKeyPair();
            PublicKey pubkey = kp.getPublic();
            PrivateKey prikey = kp.getPrivate();

            File KeyFile1 = new File(args[1] + ".pub");
            BufferedOutputStream f1 = new BufferedOutputStream(new FileOutputStream(KeyFile1));
            byte[] keybyte = pubkey.getEncoded();
            //System.out.println(pubkey.getFormat());
            f1.write(keybyte);
            f1.flush();
            f1.close();
            System.out.println("Public Key stored in " + KeyFile1.toURI().toURL().toString());

            File KeyFile2 = new File(args[1] + ".prv");
            BufferedOutputStream f2 = new BufferedOutputStream(new FileOutputStream(KeyFile2));
            keybyte = prikey.getEncoded();
            //System.out.println(prikey.getFormat());
            f2.write(keybyte);
            f2.flush();
            f2.close();
            System.out.println("Private Key stored in " + KeyFile2.toURI().toURL().toString());
         }

         if (args[0].equals("3DES"))
         {
            KeyGenerator kg = KeyGenerator.getInstance("DESede");
            SecretKey seckey = kg.generateKey();

            byte[] keybyte = seckey.getEncoded();
            //System.out.println(seckey.getFormat());
            File KeyFile = new File(args[1] + ".ser");
            FileOutputStream f = new FileOutputStream(KeyFile);
            f.write(keybyte);
            f.close();
            System.out.println("Secret Key stored in " + KeyFile.toURI().toURL().toString());
         }
      }
      catch (ArrayIndexOutOfBoundsException e)
      {
         System.out.println("Usage: GererateKey keyType keyName");
      }
   }
}