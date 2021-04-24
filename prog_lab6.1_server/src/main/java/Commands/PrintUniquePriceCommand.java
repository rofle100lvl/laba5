package Commands;

import commandDescriptions.CommandDescription;
import commandDescriptions.PrintUniquePriceDescription;
import utils.CollectionManager;
import utils.Request;
import utils.UserAsker;

import java.util.HashSet;

/**
 * Класс команды, выводящей уникальные значения поля price всех элементов в коллекции
 */
public class PrintUniquePriceCommand extends AbstractCommand {
    public PrintUniquePriceCommand(CollectionManager collectionManager) {
        super("print_unique_price", "Выводит уникальные значения поля" +
                " price всех элементов в коллекции", collectionManager);
    }

    @Override
    public Request execute(CommandDescription commandDescription) {
        PrintUniquePriceDescription printUniquePriceDescription = (PrintUniquePriceDescription) commandDescription;
        return new Request(200, getCollectionManager().getUniquePrice());

    }

}