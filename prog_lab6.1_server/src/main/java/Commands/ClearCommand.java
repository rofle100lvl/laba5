package Commands;

import commandDescriptions.AddDescription;
import commandDescriptions.ClearDescription;
import commandDescriptions.CommandDescription;
import utils.CollectionManager;
import utils.Request;
import utils.UserAsker;

/**
 * Класс команды чистки коллекции
 */
public class ClearCommand extends AbstractCommand {
    public ClearCommand(CollectionManager collectionManager) {
        super("clear", "Добавляет новый элемент в коллекцию", collectionManager);
    }

    @Override
    public Request execute(CommandDescription commandDescription) {
        ClearDescription clearDescription = (ClearDescription) commandDescription;
        Request request;
        if(getCollectionManager().clear()){
            request = new Request(200,"Коллекция успешно очищена");
        }
        else {
            request = new Request(200,"Коллекция пустая");
        }
        return request;
    }


}