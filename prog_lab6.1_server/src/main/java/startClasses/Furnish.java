package startClasses;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

/**
 * Начальный enum ремонта
 */
@XmlType(name = "Furnish")
@XmlEnum
public enum Furnish {
    DESIGNER,
    NONE,
    FINE,
    LITTLE;
}