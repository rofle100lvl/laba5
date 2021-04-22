package Commands;

import CollectionManager.Flats;
import utils.UserAsker;

/**
 * Класс команды, выводящей элементы коллекции
 */

public class ShowCommand extends AbstractCommand {
    public ShowCommand(UserAsker userAsker, Flats flats)
    {
        super("show", "Выводит элементы коллекции");
        setFlats(flats);
        setUserAsker(userAsker);
        setCountOfArguments(0);
    }

    /**
     * Метод запускающий команду
     * @param argument Запрос пользователя
     * @return Возвращает true, если команда обработана
     */
    @Override
    public boolean execute(String argument) {
        flats.show();
        return true;

    }
}