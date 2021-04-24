package Commands;

import commandDescriptions.CommandDescription;
import commandDescriptions.PrintFieldDescendingDescription;
import startClasses.Flat;
import utils.CollectionManager;
import utils.Request;
import utils.UserAsker;

/**
 * Класс команды, выводящей значения поля house всех элементов в порядке убывания
 */

public class PrintFieldDescendingHouseCommand extends AbstractCommand {
    public PrintFieldDescendingHouseCommand(CollectionManager collectionManager) {
        super("print_field_descending_house", "Выводит значения поля house всех элементов в " +
                "порядке убывания", collectionManager);
    }


    @Override
    public Request execute(CommandDescription commandDescription) {
        PrintFieldDescendingDescription printFieldDescendingHouseCommand =
                (PrintFieldDescendingDescription) commandDescription;
        getCollectionManager().sort();

        return new Request(200, getCollectionManager().getFieldDescendingHouse());
    }
}