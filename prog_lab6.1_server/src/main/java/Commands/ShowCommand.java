package Commands;

import commandDescriptions.CommandDescription;
import commandDescriptions.ShowCommandDescription;
import utils.CollectionManager;
import utils.Request;
import utils.UserAsker;

/**
 * Класс команды, выводящей элементы коллекции
 */

public class ShowCommand extends AbstractCommand {
    public ShowCommand(CollectionManager collectionManager) {
        super("show", "Выводит элементы коллекции", collectionManager);
    }

    @Override
    public Request execute(CommandDescription commandDescription) {
        ShowCommandDescription showCommandDescription = (ShowCommandDescription) commandDescription;
        return new Request(200, getCollectionManager().show());
    }
}