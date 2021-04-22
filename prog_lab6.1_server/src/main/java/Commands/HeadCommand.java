package Commands;

import CollectionManager.Flats;
import utils.UserAsker;

/**
 * Класс команды вывода первого элемента коллекции
 */
public class HeadCommand extends AbstractCommand {
    public HeadCommand(UserAsker userAsker,Flats flats) {
        super("head", "Вывод первого элемента коллекции");
        setUserAsker(userAsker);
        setFlats(flats);
        setCountOfArguments(0);
    }

    /**
     * Метод запускающий команду
     * @param argument Запрос пользователя
     * @return Возвращает true, если команда обработана
     */

    @Override
    public boolean execute(String argument) {
        flats.head();
        return true;
    }
}