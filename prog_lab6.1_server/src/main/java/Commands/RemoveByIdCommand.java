package Commands;

import commandDescriptions.CommandDescription;
import commandDescriptions.RemoveByIdDescription;
import utils.CollectionManager;
import utils.Request;
import utils.UserAsker;

/**
 * Класс команды, удаляющей элемент с заданным id
 */
public class RemoveByIdCommand extends AbstractCommand {
    public RemoveByIdCommand(CollectionManager collectionManager) {
        super("remove_by_id", "Удаляет элемент с заданным id", collectionManager);
    }

    @Override
    public Request execute(CommandDescription commandDescription) {
        RemoveByIdDescription removeByIdDescription = (RemoveByIdDescription) commandDescription;
        Request request;
        if(getCollectionManager().removeId(removeByIdDescription.getId()))
            request = new Request(200,"Элемент успешно удалён.");
        else request = new Request(200,"Элемент с данным id отсутствует в коллекции");
        return request;
    }


}