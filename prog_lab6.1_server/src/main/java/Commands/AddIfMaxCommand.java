package Commands;

import commandDescriptions.AddIfMaxDescription;
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
 * Класс команды добавления экземпляра в коллекцию, если он максимальный
 */
public class AddIfMaxCommand extends AbstractCommand {


    public AddIfMaxCommand(CollectionManager collectionManager) {
        super("add_if_max", "Добавляет новый элемент в коллекцию, если он максимальный", collectionManager);
    }

    @Override
    public Request execute(CommandDescription commandDescription) {
        AddIfMaxDescription addIfMaxDescription = (AddIfMaxDescription) commandDescription;
        System.out.println(addIfMaxDescription.getName().name());
        return new Request(200,"AddIfMax");
    }
}