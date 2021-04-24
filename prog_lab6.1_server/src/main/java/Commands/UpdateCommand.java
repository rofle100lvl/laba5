package Commands;

import commandDescriptions.CommandDescription;
import commandDescriptions.UpdateIdDescription;
import startClasses.Coordinates;
import startClasses.Flat;
import startClasses.House;
import utils.CollectionManager;
import utils.Request;
import utils.UserAsker;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Класс команды, обновляещей значение элемента коллекции, id которого равен заданному
 */

public class UpdateCommand extends AbstractCommand {
    public UpdateCommand(CollectionManager collectionManager) {
        super("update", "Обновляет значение элемента коллекции, id" +
                " которого равен заданному", collectionManager);

    }

    @Override
    public Request execute(CommandDescription commandDescription) {
        UpdateIdDescription updateIdDescription = (UpdateIdDescription) commandDescription;
        return new Request(200, getCollectionManager().updateId(updateIdDescription.getId(),
                updateIdDescription.getFlat()));
    }
}


