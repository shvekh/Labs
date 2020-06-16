package Lab8;

import java.io.IOException;
import java.util.LinkedList;

public class Crawler {

    public static void main(String[] args) throws IOException {
        sites(args);
    }

    public static void getSites(LinkedList<URLDEP> viewLinks){ //возвращ. все просмотренные ссылки  с их глубиной
        for (URLDEP c : viewLinks)
            System.out.println("Depth : "+c.getDepth() + "\t  Url : "+c.getURL());
    }

    public static boolean isch(String st){
        for(int i = 0;i<st.length();i++){
            if(!(Character.isDigit(st.charAt(i)))) return false;
        }
        return true;
    }

    public static void sites(String[] args) throws IOException {
        args = new String[]{"http://help.megagroup.ru/", "3", "5"};
        if (args.length == 3 && isch(args[1]) && isch(args[2])) {

            String pai = args[0]; //url
            int maxdepth = Integer.parseInt(args[1]);
            int T = Integer.parseInt(args[2]);

            URLPool pool = new URLPool(maxdepth);
            pool.addPair(new URLDEP(pai, 0));

            for (int i = 0; i < T; i++) {
                CrawlerTask crew = new CrawlerTask(pool);
                Thread th = new Thread(crew);
                th.start();
            }
            while (pool.getWait() != T) {
                try {
                    Thread.sleep(600);
                } catch (InterruptedException e) {
                    System.out.println("Error");
                }
            }
            try {
                getSites(pool.getResult()); //вывод
                System.out.println("Итого ссылок: " + pool.getResult().size());
            }
            catch (NullPointerException e){
                System.out.println("Error");
            }
            System.exit(0);
        }
        else {
            System.out.println("usage: java Crawler <URL><depth> <threads>");
            System.exit(1);
        }
    }
}