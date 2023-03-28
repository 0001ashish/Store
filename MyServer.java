import java.io.*;  
import java.net.*;  
import java.util.*;
import java.math.*;
public class MyServer {  
    public static void main(String[] args){  
        Scanner sc = new Scanner(System.in);
        try{  
            ServerSocket ss=new ServerSocket(6666);  
            Socket s=ss.accept();//establishes connection   
            DataInputStream dis=new DataInputStream(s.getInputStream());  
            String  str=(String)dis.readUTF(); 
            System.out.println(str);

            String[] arr = str.split(" ");
            
            BigInteger e = new BigInteger(arr[0]);
            BigInteger n = new BigInteger(arr[1]);

            System.out.println("Enter the Message(integer of course !):");
            
            BigInteger M = sc.nextBigInteger();
            BigInteger C = M.pow(e.intValue()).mod(n);
            System.out.println("Cipher text:"+C.toString());
        
            DataOutputStream dout=new DataOutputStream(s.getOutputStream());  
            dout.writeUTF(C.toString());  
            dout.flush();  
            dout.close();  
            ss.close();  
        
 
        }catch(Exception e){System.out.println(e);}  
    }  
}  