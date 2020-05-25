package Tasks;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

public class Task3 {
    public static void main(String[] args){

        System.out.println(nextPrime(24));
    }
    //number 1
    public static int solutions(int a, int b, int c){
        int sum = b*b-4*a*c;
        if (sum<0){
            return 0;
        } else if (sum==0){
            return 1;
        } else {
            return 2;
        }

    }
    //number 2
    public static int findZip(String s){
        return s.indexOf("zip",s.indexOf("zip")+1);
    }
    //number 3
    public static boolean checkPerfect(int a){
        int ch=0;
        for (int i=1;i<a;i++){
            if (a%i==0){
                ch+=i;
            }
        }
        if (ch==a){
            return true;
        } else return false;
    }
    //number 4
    public static String flipEndChars(String s){
        if (s.length()<2){
            return "Incompatible.";
        } else if(s.charAt(0)==s.charAt(s.length()-1)){
            return "Two's a pair";
        } else {
            return s.charAt(s.length()-1) + s.substring(1,s.length()-1) + s.charAt(0);
        }
    }
    //number 5
    static boolean isValidHexCode (String s){
        return s.matches("(\\#)[a-fA-F0-9]{6}");
    }
    //number 6
    static boolean same (Integer[] arr1,Integer[] arr2) {
        Set<Integer> set1 = new LinkedHashSet<>(Arrays.asList(arr1));
        Set<Integer> set2 = new LinkedHashSet<>(Arrays.asList(arr2));
        if (set1.size()==set2.size()) return true;
        return false;
    }
    //number 7
    public static boolean isKaprekar(int a){
        int a3=a;
        a=a*a;
        int a2=a;
        int i=0;
        int cl,cr;
        while (a2!=0){
            a2=a2/10;
            i++;
        }
        if (i%2==1){
            cl = (int) (a/(Math.pow(10,i/2+1)));
            cr = (int) (a%(Math.pow(10,i/2+1)));
            System.out.println(cl + " " + cr + " 1");
        } else {
            cl = (int) (a/(Math.pow(10,i/2)));
            cr = (int) (a%(Math.pow(10,i/2)));
            System.out.println(cl + " " + cr + " 0");
        }
        return (a3==cl+cr);
    }
    //number 8
    public static String longestZero(String s){
        int max=0;
        int maxi=0;
        for (int i=0; i<s.length(); i++){
            if(s.charAt(i)=='0'){
                maxi++;
                if (maxi>max) max=maxi;
            } else {
                maxi=0;
            }
        }
        String res="";
        for (int i=1; i<=max; i++) res+="0";
        return res;
    }
    //number 9
    public static int nextPrime(int a){
        int i=a;
        while (!IsPrime(i)) i++;
        return i;}
    private static boolean IsPrime(int n) {
        for (int i = 2; i < n; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }
    //number 10
    public static boolean rightTriangle(int a, int b, int c){
        return triangle(a,b,c) | triangle(b,c,a) | triangle(c,a,b);
    }
    private static boolean triangle(int a,int b, int c){
        return Math.sqrt(a*a+b*b)==Math.sqrt(c*c);
    }
}

