package Lab7;

import java.util.LinkedList;
import java.net.MalformedURLException;
import java.net.URL;

// Класс для представления пар URL, depth
public class URLDEP {

    public static final String URL_PREFIX = "<a href=\"http";
    String URL; //URL
    int depth;  //глубина

    public URLDEP (String url, int depth){
        this.URL=url;
        this.depth=depth;
    }

    public String getHost() throws MalformedURLException {
        try {
            URL host = new URL(URL);
            return host.getHost();
        } catch (Exception e) {
            System.out.println(e.toString());
            return null;
        }
    }

    public String getPath() throws MalformedURLException {
        try {
            URL p = new URL(URL);
            return p.getPath();
        } catch (Exception e){
            System.out.println(e.toString());
            return null;
        }
    }

    public int getDepth() { //возвращает значение поля глубины
        return depth;
    }

    public String getURL() { //возвращает значение поля URL
        return URL;
    }

    public static boolean AT_LIST(LinkedList<URLDEP> Links, URLDEP pair) { //возвращает true если url
        boolean isA = true;                            // объекта pair содержится хотя бы в одном объекте списка
        for (URLDEP c : Links)
            if (c.getURL().equals(pair.getURL())) isA=false;
        return isA;
    }

    public String toString(){
        String stringD= Integer.toString(depth);
        return stringD + '\t' + this.URL;
    }
}