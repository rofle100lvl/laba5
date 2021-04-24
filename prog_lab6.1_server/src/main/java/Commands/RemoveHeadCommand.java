package Commands;

import commandDescriptions.CommandDescription;
import commandDescriptions.RemoveHeadDescription;
import utils.CollectionManager;
import utils.Request;
import utils.UserAsker;

/**
 * Класс команды, выводящей первый элемент и удаляющей его
 */
public class RemoveHeadCommand extends AbstractCommand {
    public RemoveHeadCommand(CollectionManager collectionManager) {

        super("remove_head", "Вывод первого элемента коллекции и удаление его", collectionManager);
    }


    @Override
    public Request execute(CommandDescription commandDescription) {
        RemoveHeadDescription removeHeadDescription = (RemoveHeadDescription) commandDescription;
        return new Request(200, getCollectionManager().removeHead());
    }
}