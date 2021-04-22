package Commands;

import commandDescriptions.CommandDescription;
import utils.CollectionManager;

public abstract class AbstractCommand {
    /**
     * Имя команды
     */
    private final String name;

    /**
     * Описание команды
     */
    private final String description;
    /**
     * Collection manager в котором будут производится изменения
     */
    private final CollectionManager collectionManager;

    public AbstractCommand(CommandDescription commandDescription, String description, CollectionManager collectionManager) {
        this.name = commandDescription.getName().name();
        this.description = description;
        this.collectionManager = collectionManager;
    }
    abstract public boolean execute(CommandDescription commandDescription);
}