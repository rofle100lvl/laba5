package startClasses;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 * Начальный класс координат
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class Coordinates {
    @XmlElement
    private float x;

    @XmlElement
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
