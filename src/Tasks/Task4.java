package Tasks;

public class Task4 {
    public static void main(String[] args) {

        System.out.println(toStarShorthand(""));
    }
    //number 1
    static String textEdit(int n, int k, String s) {
        StringBuilder resultText = new StringBuilder();
        int lenght = 0;
        while (s.indexOf(" ")==0)
            s=s.substring(1);
        while (s.indexOf(" ")!=-1)
            s=s.substring(0,s.indexOf(" "))+s.substring(s.indexOf(" ")+1);

        String[] text = s.split(" ");

        if (text.length == n) {
            for (String word : text) {
                System.out.println(word.length());
                if (word.length() > k) {
                    return "none";
                }else if (lenght + word.length() <= k) {
                    lenght += word.length();
                    resultText.append(word).append(" ");
                }else {
                    lenght = word.length();
                    resultText.append("\n").append(word).append(" ");
                }
            }
        }
        else
            return "none";
        return resultText.toString();
    }
    // number 2
    public static String split(String s){
        int maxl=0;
        int maxr=0;
        StringBuilder res = new StringBuilder();
        res.append("[");
        String r ="";
        for (char c : s.toCharArray()){
            if (c=='('){
                r+='(';
                maxl++;
            } else {
                maxr++;
                r+=')';
            }
            if (maxl==maxr){
                maxr=0;maxl=0;
                res.append("\""+r+"\", ");
                r="";
            }
        }
        res.deleteCharAt(res.length()-1);
        res.deleteCharAt(res.length()-1);
        res.append("]");
        return res.toString();
    }
    //number 3
    static String toCamelCase(String snake) {
        while (snake.contains("_"))
            snake = snake.substring(0, snake.indexOf("_")) +
                    Character.toUpperCase(snake.charAt(snake.indexOf("_") + 1)) +
                    snake.substring(snake.indexOf("_") + 2);
        return snake;
    }
    static String toSnakeCase(String camel) {
        String result = "";
        for (char c : camel.toCharArray())
            result += Character.isUpperCase(c) ? "_" + Character.toLowerCase(c) : c;
        return result;
    }
    //number 4
    public static double overTime(double a, double b, double c, double d) {
        double zp = 0;
        if (b > 17) {
            zp = (17 - a) * c + ((b - 17) * d * c);
        } else zp = (b - a) * c;
        System.out.format("%.2f", zp);
        return zp;
    }
    //number 5
    static String BMI(String weight, String height) {
        double h = Double.parseDouble(height.substring(0, height.indexOf(" ")));
        double w = Double.parseDouble(weight.substring(0, weight.indexOf(" ")));

        h = height.contains("inches") ? 0.0254 * h : (height.contains("meters") ? h : 0);
        w = weight.contains("pounds") ? 0.453592 * w : (weight.contains("kilos") ? w : 0);

        double bmi = h != 0 ? Math.round(10 * w / (h * h)) / 10.0 : 0;
        return bmi == 0 ? "Incorrect line" : (bmi <= 18.4 ? bmi + " Недостаточный вес" : (bmi >= 25 ? bmi + " Избыточный вес" : bmi + " Нормальный вес"));
    }
    //number 6
    public static int bugger(int a){
        if (a<10) return 0;
        int ch =1;
        while(a!=0){
            ch*=(a%10);
            a/=10;
        }
        return 1 + bugger(ch);

    }
    //number 7
    public static String toStarShorthand(String a){
        String st="";
        if(a==""){
            return st;
        }
        a+=" ";
        int kol=1;
        for (int i=1;i<a.length();i++){
            if (a.charAt(i-1)==a.charAt(i)){
                kol++;

            } else if(kol>1){
                st+=a.charAt(i-1)+"*"+kol;
                kol=1;

            }else if(kol==1){
                st+=a.charAt(i-1);
            }
        }
        return st;
    }
    //number 8
    public static boolean doesRhyme(String a, String b) {
        String la=a.split(" ")[a.split(" ").length-1].replaceAll("[!.?]","");
        String lb=b.split(" ")[b.split(" ").length-1].replaceAll("[!.?]","");
        return lb.toLowerCase().endsWith(la.toLowerCase());
    }
    //number 9
    public static boolean trouble(int a, int b) {
        int[] MassA = new int[10];
        int[] MassB = new int[10];
        for (int i=0;i<=9;i++){
            MassA[i]=0;
            MassB[i]=0;
        }
        while (a!=0 | b!=0){
            MassA[a%10]++;
            MassB[b%10]++;
            a/=10;
            b/=10;
        }
        for (int i=0;i<=9;i++){
            if (MassA[i]==3 & MassB[i]==2){
                return true;
            }
        }
        return false;
    }
    //number 10
    public static int countUniqueBooks(String stringSequence, char bookEnd) {
        int i = 0;
        String res = "";
        for (String s : stringSequence.split(Character.toString(bookEnd))) {
            if (i++ % 2 == 1) {
                for (char c : s.toCharArray()) {
                    if (res.indexOf(c) == -1)
                        res += c;
                }
            }
        }
        return res.length();
    }
}
