package Commands;

import commandDescriptions.AddDescription;
import commandDescriptions.CommandDescription;
import startClasses.Coordinates;
import startClasses.Flat;
import startClasses.House;
import utils.CollectionManager;
import utils.Request;
import utils.UserAsker;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * Класс команды добавления экземпляра в коллекцию
 */
public class AddCommand extends AbstractCommand {
    public AddCommand(CollectionManager collectionManager) {
        super("add", "Добавляет новый элемент в коллекцию", collectionManager);
    }

    @Override
    public Request execute(CommandDescription commandDescription) {
        AddDescription addDescription = (AddDescription) commandDescription;
        getCollectionManager().add(addDescription.getFlat());
        return new Request(201,"Элемент успешно добавлен");
    }
}
