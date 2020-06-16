package Lab7;

import java.io.*;
import java.net.*;
import java.util.LinkedList;

public class Crawler {

    public static void main(String[] args) throws IOException {
        String[] args2 = new String[2];
        args2[0] = "https://4pda.ru/forum/index.php?act=idx"; //сайт
        args2[1] = "2"; //maxdepth
        sites(args2[0], Integer.parseInt(args2[1]));
        getSites(viewed_links);
        System.out.println("Итого ссылок: " + viewed_links.size());
    }

    // список для просмотренных ссылок
    static LinkedList <URLDEP> viewed_links = new LinkedList <URLDEP>();

    // список для ожидающих ссылок
    static LinkedList <URLDEP> finded_links = new LinkedList <URLDEP>();

    public static void getSites(LinkedList<URLDEP> viewLinks) { //возвращает все просмотренные ссылки вместе с их глубиной
        for (URLDEP c : viewLinks)
            System.out.println("Depth : "+c.getDepth() + "\tUrl : "+c.getURL());
    }

    public static void requestToserver(PrintWriter out,URLDEP pair) throws MalformedURLException { //осуществляет запрос на сервер
        out.println("GET " + pair.getPath() + " HTTP/1.1");
        out.println("Host: " + pair.getHost());
        out.println("Connection: close");
        out.println();
        out.flush();
    }

    public static void sites(String pair, int maxDepth) throws IOException {
        try {
            finded_links.add(new URLDEP(pair, 0)); // добавить сайт в ожидающий список ссылок
            while (!finded_links.isEmpty()) {
                URLDEP currentPair = finded_links.removeFirst(); // удаление первого элемента из списка findLink и присваивание его currentPair
                if (currentPair.depth < maxDepth) {
                    // Инициализируем новый сокет из строки String
                    Socket myso = new Socket(currentPair.getHost(), 80); //создание нового сокета
                    System.out.println("Connect to url: " + currentPair.getURL());
                    myso.setSoTimeout(45000);
                    BufferedReader buffin = new BufferedReader(new InputStreamReader(myso.getInputStream()));
                    PrintWriter printout = new PrintWriter(myso.getOutputStream(), true);
                    requestToserver(printout, currentPair); //запрос
                    String st;
                    while ((st = buffin.readLine()) != null) { // если строка line не пуста
                        if (st.indexOf(currentPair.URL_PREFIX) != -1) {
                            StringBuilder currentLink = new StringBuilder();
                            for (int i = st.indexOf(currentPair.URL_PREFIX) + 9; st.charAt(i) != '"'; i++) {
                                currentLink.append(st.charAt(i)); }
                            URLDEP newPair = new URLDEP(currentLink.toString(), currentPair.depth + 1); //новая пара с глубиной,ув. на 1
                            if (currentPair.AT_LIST(finded_links, newPair) && currentPair.AT_LIST(viewed_links, newPair) && !currentPair.URL.equals(newPair.URL))
                                finded_links.add(newPair); //добавить ссылку в список ожидающих
                        }
                    }
                    myso.close();
                }
                viewed_links.add(currentPair);   //добавление просмотренной ссылки в список
            }
        } catch (Exception e){
            System.out.println("usage: java Crawler <URL> <depth>");
            System.exit(1);
        }
    }
}
