package Commands;

import commandDescriptions.AddDescription;
import commandDescriptions.CommandDescription;
import commandDescriptions.ExecuteScriptDescription;
import utils.CollectionManager;
import utils.CommandManager;
import utils.Request;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Stack;

/**
 * Класс команды выполнения скрипта
 */

public class ExecuteScriptCommand extends AbstractCommand {
    public ExecuteScriptCommand(CollectionManager collectionManager) {
        super("execute_script", "Выполнение скрипта",collectionManager);
    }

    @Override
    public Request execute(CommandDescription commandDescription) {
        ExecuteScriptDescription executeScriptDescription = (ExecuteScriptDescription) commandDescription;
        System.out.println(executeScriptDescription.getName().name());
        return new Request(200,"execute_script");
    }
}