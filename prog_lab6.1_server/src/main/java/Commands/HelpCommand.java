package Commands;

import commandDescriptions.CommandDescription;
import commandDescriptions.HeadDescription;
import commandDescriptions.HelpCommandDescription;
import utils.CollectionManager;
import utils.Request;

import java.util.stream.Collectors;

public class HelpCommand extends AbstractCommand{
    public HelpCommand(CollectionManager collectionManager) {
        super("help", "Выводит справку о командах", collectionManager);
    }


    @Override
    public Request execute(CommandDescription commandDescription) {
        HelpCommandDescription helpCommandDescription = (HelpCommandDescription) commandDescription;
        return new Request(200, getCollectionManager().getCommandManager().getCommands().stream()
                .map(c -> c.getName() + " " + c.getDescription())
                .collect(Collectors.joining("\n")));
    }
}
