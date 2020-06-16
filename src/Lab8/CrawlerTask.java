package Lab8;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.Socket;

public class CrawlerTask implements Runnable{

    URLPool myurlPool; //ссылка на экземпляр пула

    public static final String URLPREFIX = "<a href=\"http";

    public CrawlerTask(URLPool pool){
        myurlPool = pool;
    }

    public static void requestToserver(PrintWriter out,URLDEP pair) throws MalformedURLException {
        out.println("GET " + pair.getPath() + " HTTP/1.1");
        out.println("Host: " + pair.getHost());
        out.println("Connection: close");
        out.println();
        out.flush();
    }

    @Override
    public void run(){
        while (100<900){
            URLDEP currentPair = myurlPool.getPair();
            try{
                Socket mysoc = new Socket(currentPair.getHost(), 80);
                System.out.println("Connected to " + currentPair.getURL());
                mysoc.setSoTimeout(5000); //время ожидания
                BufferedReader buff =  new BufferedReader(new InputStreamReader(mysoc.getInputStream()));
                PrintWriter print = new PrintWriter(mysoc.getOutputStream(), true);
                requestToserver(print,currentPair); //запроса на сервер
                String s;
                while ((s = buff.readLine()) != null){
                    if (s.indexOf(currentPair.URL_PREFIX)!=-1) {
                        try {
                            String current = s.substring(s.indexOf(URLPREFIX)+9,s.indexOf("\"", s.indexOf(URLPREFIX)+9));
                            myurlPool.addPair(new URLDEP(current, currentPair.getDepth()+1));
                        }
                        catch (Exception er) {
                            System.out.println("Error - " + er.toString());
                        }
                    }
                }
                mysoc.close();
            }
            catch (Exception er) {
                System.out.println("Error  - " + er.toString());
            }
        }
    }
}
