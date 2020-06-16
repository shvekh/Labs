package Lab8;

import java.util.LinkedList;
import java.net.MalformedURLException;
import java.net.URL;

public class URLDEP {

    public static final String URL_PREFIX = "<a href=\"http";

    public String URL; //URL
    private int depth; //глубина
    URL host_path;     //ссылка URL

    public URLDEP (String url, int depth){ //url - адрес depth - глубина
        this.URL=url;
        this.depth=depth;
        try {
            this.host_path= new URL(URL);
        }
        catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public String getHost(){ //возвращает значения хоста url
        return host_path.getHost();
    }

    public String getPath(){
        return host_path.getPath();
    }

    public int getDepth() { //возвращает значение поля depth
        return depth;
    }

    public String getURL() { //возвращает значение поля URL
        return URL;
    }

    /** метод возвращает true если url
     // объекта pair содержится хотя бы в одном объекте списка */


    public static boolean AT_LIST(LinkedList<URLDEP> Links, URLDEP pair) { //возвращает true если url
        boolean isA = true;                            // объекта pair содержится хотя бы в одном объекте списка
        for (URLDEP c : Links)
            if (c.getURL().equals(pair.getURL())) isA=false;
        return isA;
    }

    public String toString(){
        String stringD = Integer.toString(depth);
        return stringD + '\t' + this.URL;
    }
}