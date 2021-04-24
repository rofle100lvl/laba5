package Commands;

import commandDescriptions.CommandDescription;
import commandDescriptions.InfoCommandDescription;
import utils.CollectionManager;
import utils.Request;
import utils.UserAsker;

/**
 * Класс команды выхода информации о коллекции
 */
public class InfoCommand extends AbstractCommand{
    public InfoCommand(CollectionManager collectionManager) {
        super("info", "Выводит информацию о коллекции", collectionManager);
    }


    @Override
    public Request execute(CommandDescription commandDescription) {
        InfoCommandDescription infoCommandDescription = (InfoCommandDescription) commandDescription;
        return new Request(200, getCollectionManager().getInfo());
    }
}
