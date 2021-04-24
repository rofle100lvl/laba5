package utils;

import java.io.Serializable;

public class Request implements Serializable {

    // 0 - плохо, 1 - хорошо
    private int code;
    private String requestText;

    public Request(int code, String requestText) {
        this.code = code;
        this.requestText = requestText;
    }

    public void printRequest(){
        System.out.println("Статус ответа: " + String.valueOf(code));
        System.out.println("Ответ сервера: " + requestText);
    }
}
