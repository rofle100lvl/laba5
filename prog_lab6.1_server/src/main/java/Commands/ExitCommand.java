package Commands;

/**
 * Класс команды выхода из программы
 */
public class ExitCommand extends AbstractCommand {
    public ExitCommand() {
        super("exit", "Завершение программы");
        setCountOfArguments(0);
    }

    /**
     * Метод запускающий команду
     * @param argument Запрос пользователя
     * @return Возвращает true, если команда обработана
     */
    @Override
    public boolean execute(String argument) {
        return true;
    }
}