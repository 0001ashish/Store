public class Coprime{
    private static int gcd(int a,int b){
        if(a==0||b==0){return a+b;}
        if(a>b){
           return gcd(a%b,b); 
        }
        else{
            return gcd(b%a,a);
        }
    }

    public static boolean isCoprime(int a,int b){
        int result = gcd(a,b);
        if(result==1){
            return true;
        }else{return false;}
    }

    public static int findCoprime(int num){
        int i = 2;
        while(!isCoprime(i,num)){
            i++;
            if(i>num){
                return 1;
            }
        }
        return i;
    }
}