package Commands;

import commandDescriptions.CommandDescription;
import utils.CollectionManager;
import utils.Request;

/**
 * Класс команды, сохраняющей коллекцию в файл
 */

public class SaveCommand extends AbstractCommand {
    public SaveCommand(CollectionManager collectionManager) {

        super("save", "Сохранение коллекции в файл", collectionManager);
    }

    @Override
    public Request execute(CommandDescription commandDescription) {
        return new Request(200,"Save");
    }
}