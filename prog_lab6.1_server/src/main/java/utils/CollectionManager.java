package utils;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.stream.Collectors;

public class CollectionManager {
    private LinkedList<Integer> linkedList;
    private LocalDateTime creationDate;
    //Конец на сегодня

    public CollectionManager(){
        linkedList = new LinkedList<>();
        creationDate = LocalDateTime.now();
    }


    public void info(){
        String result = Integer.toString(linkedList.size());
        result += creationDate.toString();
        Request request = new Request(1, result);
    }

    public void show(){
        String result = linkedList.stream().map(Object::toString).collect(Collectors.joining("\n"));
        Request request = new Request(1, result);
    }
}
