package Commands;

import commandDescriptions.AddDescription;
import commandDescriptions.CommandDescription;
import commandDescriptions.ExitDescription;
import utils.CollectionManager;
import utils.Request;

/**
 * Класс команды выхода из программы
 */
public class ExitCommand extends AbstractCommand {
    public ExitCommand(CollectionManager collectionManager) {
        super("exit", "Завершение программы", collectionManager);
    }


    @Override
    public Request execute(CommandDescription commandDescription) {
        ExitDescription exitDescription = (ExitDescription) commandDescription;
        System.out.println(exitDescription.getName().name());
        return new Request(200,"ExitCommand");
    }
}