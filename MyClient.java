import java.io.*;  
import java.net.*;  
import java.util.*;
import java.math.*;

public class MyClient {  
    public static void main(String[] args) {  
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter 2 large prime numbers");
        System.out.print("First prime:");
        BigInteger p = sc.nextBigInteger();
        p = p.multiply(p).add(p).add(new BigInteger("41"));

        System.out.print("Second prime:");
        BigInteger q = sc.nextBigInteger();
        q = q.multiply(q).add(q).add(new BigInteger("41"));
      
        BigInteger n = p.multiply(q);
        BigInteger phi_n = (p.subtract(new BigInteger("1"))).multiply(q.subtract(new BigInteger("1")));
        BigInteger e = new BigInteger(""+Coprime.findCoprime(phi_n.intValue()));
        BigInteger d = e.modInverse(phi_n);
        try{      
            Socket s=new Socket("localhost",6666);  
            DataOutputStream dout=new DataOutputStream(s.getOutputStream());  
            dout.writeUTF(e.toString()+" "+n.toString());
            
            DataInputStream dis=new DataInputStream(s.getInputStream());  
            String  str=(String)dis.readUTF(); 
            BigInteger C = new BigInteger(str);
            System.out.println("Cipher text:"+C);
            System.out.println(d.toString());
            BigInteger rec_M = C.mod(n).pow(d.intValue()).mod(n);
            System.out.println("Cipher text recieved:"+C);
            System.out.println("Message recieved:"+rec_M);
            dout.flush();
            dout.close();  
            s.close();  
              
        }catch(Exception exc){System.out.println(exc);}  

    }  
}  