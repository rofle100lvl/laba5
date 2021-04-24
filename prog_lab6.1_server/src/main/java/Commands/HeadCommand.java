package Commands;

import commandDescriptions.CommandDescription;
import commandDescriptions.HeadDescription;
import utils.CollectionManager;
import utils.Request;
import utils.UserAsker;

/**
 * Класс команды вывода первого элемента коллекции
 */
public class HeadCommand extends AbstractCommand {
    public HeadCommand(CollectionManager collectionManager) {
        super("head", "Вывод первого элемента коллекции", collectionManager);
    }


    @Override
    public Request execute(CommandDescription commandDescription) {
        HeadDescription headDescription = (HeadDescription) commandDescription;
        return new Request(200, getCollectionManager().head());
    }
}