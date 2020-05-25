package Tasks;

public class Task1 {
    public static void main(String[] args){
        System.out.println(abcmath(5,2,1));
    }
    //number 1
    public static int remainder(int a, int b) {
        return (a % b);
    }

    //number 2
    public static int triArea(int a, int b) {
        return (a * b / 2);
    }


    //number 3
    public static int animals(int chickens, int cows, int pigs) {
        return (chickens * 2 + cows * 4 + pigs * 4);
    }


    //number 4
    public static boolean profitableGamble(double prob, double prize, double pay) {
        return (prob * prize > pay);
    }

    //number 5
    public static String operation(int s, int b, int a) {
        String res = "none";
        if ((a - b == s) | (b - a == s)) res = "substacted";
        if ((a + b == s)) res = "added";
        if ((a / b == s) | (b / a == s)) res = "division";
        if ((a * b == s)) res = "multiplication";
        return res;
    }


    //number 6
    public static int ctoa(String s) {
        return ((int) s.charAt(0));
    }


    //number 7
    public static int addUpTo(int a) {
        int s=0;
        for (int i = 0; i<=a; i++) s+=i;
        return (s);
    }


    //number 8
    public static int nextEdge(int a, int b) {
        return (a+b-1);
    }


    //number 9
    public static int sumOfCubes(int ... a) {
        int s=0;
        for (int i:a){
            s+=Math.pow(i,3);
        }
        return (s);
    }


    //number 10
    public static boolean abcmath(int a, int b, int c) {
        int s=a;
        for (int i=0; i<b;i++){
            s+=s;
        }
        return (s%c==0);
    }


}
