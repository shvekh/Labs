package Lab8;

import java.util.LinkedList;

//Класс URLPool
public class URLPool {

    LinkedList<URLDEP> view; //для просмотренных ссылок

    LinkedList<URLDEP> find; //для ожидающих обработки ссылок

    int maxdepth; //max глубина

    int wait; //количество потоков для обработки

    public URLPool(int maxdepth) {
        wait = 0;
        this.maxdepth = maxdepth;
        view = new LinkedList<URLDEP>();
        find = new LinkedList<URLDEP>();
    }

    public synchronized URLDEP getPair() {
        while (find.size() == 0) {
            wait++;
            try {
                wait();
            }
            catch (InterruptedException er) {
                System.out.println("Error !!!!! - " + er.toString());
            }
            wait--;
        }
        URLDEP nextPair = find.removeFirst();
        return nextPair;
    }

    public synchronized void addPair(URLDEP pair) { //добавление новой пары
        if(URLDEP.AT_LIST(view,pair)) {
            view.add(pair);
            if (pair.getDepth() < maxdepth) {
                find.add(pair);
                notify();
            }
        }
    }

    public synchronized int getWait(){ // метод возвращает значения поля waitT (количество потоков,ожидающих обработки)
        return wait;
    }

    public LinkedList<URLDEP> getResult() { // метод возвращает список просмотренных ссылок viewed_links
        return view;
    }
}