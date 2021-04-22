package startClasses;

import annotations.NotNull;



/**
 * Начальный класс координат
 */
public class Coordinates {
    private float x;

    @NotNull
    private Float y; //Поле не может быть null


    public Coordinates(float x, Float y){
        this.x = x;
        this.y=y;
    }



    public void setX(float x) {
        this.x = x;
    }

    public void setY(Float y) {
        this.y = y;
    }

    public float getX() {
        return x;
    }

    public Float getY() {
        return y;
    }

    public Coordinates() {
    }
}
