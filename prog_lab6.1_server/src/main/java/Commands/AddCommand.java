package Commands;

import commandDescriptions.AddDescription;
import commandDescriptions.CommandDescription;
import startClasses.Coordinates;
import startClasses.Flat;
import startClasses.House;
import utils.CollectionManager;
import utils.UserAsker;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * Класс команды добавления экземпляра в коллекцию
 */
public class AddCommand extends AbstractCommand {
    public AddCommand(CollectionManager collectionManager, AddDescription addDescription) {
        super(addDescription, "Добавляет новый элемент в коллекцию", collectionManager);


    }

    @Override
    public boolean execute(CommandDescription commandDescription) {
       return true;
    }
}
