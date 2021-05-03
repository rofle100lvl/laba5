import commandDescriptions.*;
import exceptions.ArgumentsCountException;
import exceptions.UnknownCommandNameException;
import startClasses.Coordinates;
import startClasses.Flat;
import startClasses.House;
import support.CommandName;
import utils.Validator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CommandDescriptionFactory {

    public CommandDescription getCommandDescription(String commandName, String arguments) throws
            UnknownCommandNameException, ArgumentsCountException, NumberFormatException {
        CommandName commandNameEnum = CommandName.findByName(commandName);
        switch (commandNameEnum) {
            case HELP:
                if(validateCountArgument(arguments,0)){
                    return new HelpCommandDescription();
                }
            case INFO:
                if(validateCountArgument(arguments,0)){
                    return new InfoCommandDescription();
                }
            case SHOW:
                if(validateCountArgument(arguments,0)){
                    return new ShowCommandDescription();
                }
            case CLEAR:
                if(validateCountArgument(arguments,0)){
                    return new ClearDescription();
                }
            case EXIT:
                if(validateCountArgument(arguments,0)){
                    return new ExitDescription();
                }
            case HEAD:
                if(validateCountArgument(arguments,0)){
                    return new HeadDescription();
                }
            case REMOVEHEAD:
                if(validateCountArgument(arguments,0)){
                    return new RemoveHeadDescription();
                }
            case PRINTUNIQUEPRICE:
                if(validateCountArgument(arguments,0)){
                    return new PrintUniquePriceDescription();
                }
            case PRINFFIELDDESCENDINGHOUSE:
                if(validateCountArgument(arguments,0)){
                    return new PrintFieldDescendingDescription();
                }
            case FILTERLESSTHANNUMBEROFROOMS:
                if(validateCountArgument(arguments,1)){
                    return new FilterLessThanNumberOfRoomsCommandDescription(Integer.parseInt(arguments));
                }
            case ADD:
                if(validateCountArgument(arguments,0)){
                    return new AddDescription(readNewFlat());
                }
            case ADDIFMAX:
                if(validateCountArgument(arguments,0)){
                    return new AddIfMaxDescription(readNewFlat());
                }
            case UPDATEID:
                if(validateCountArgument(arguments,1)){
                    return new UpdateIdDescription(readNewFlat(),Long.parseLong(arguments));
                }
            case REMOVEBYID:
                if(validateCountArgument(arguments,1)){
                    return new RemoveByIdDescription(Integer.parseInt(arguments));
                }
            case EXECUTESCRIPT:
                if(validateCountArgument(arguments,1)){
                    return new ExecuteScriptDescription(arguments);
                }

        }
        throw new UnknownCommandNameException();
    }

    private boolean validateCountArgument(String arg, int count) throws ArgumentsCountException {
        if (argumentsCountIsEqual(arg, count)) return true;
        throw new ArgumentsCountException();
    }
    private  boolean argumentsCountIsEqual(String arguments, int count) {
        if (arguments.equals("")) return count == 0;
        else return arguments.split(" ").length == count;
    }

    public Flat readNewFlat(){
        BufferedReader reader;
        reader = new BufferedReader(new InputStreamReader(System.in));
        Flat flat = new Flat();
        try {
            Validator validator = new Validator(Flat.class);
            String request = "";

            while (true) {
                System.out.print("Введите name: ");
                request = reader.readLine();
                flat.setName(flat.getName());
                if (validator.validateField(request, flat.getClass().getDeclaredField("name"), flat)) break;
                else{
                    System.out.println("Произошла ошибка при вводе поля. Ожидался не пустой String.");
                }
            }


            while (true) {
                System.out.printf("Введите площадь квартиры: ");
                request = reader.readLine();
                if (validator.validateField(request, flat.getClass().getDeclaredField("area"), flat)) break;
                else {
                    System.out.println("Произошла ошибка при вводе поля. Ожидался float.");
                }
            }

            while (true) {
                System.out.printf("Есть ли балкон (true/false): ");
                request = reader.readLine();
                if (validator.validateField(request, flat.getClass().getDeclaredField("balcony"), flat)) break;
                else {
                    System.out.println("Произошла ошибка при вводе поля. Ожидался Boolean.");
                }
            }

            while (true) {
                System.out.printf("Введите колличество комнат: ");
                request = reader.readLine();
                if (validator.validateField(request, flat.getClass().getDeclaredField("numberOfRooms"), flat)) break;
                else {
                    System.out.println("Произошла ошибка при вводе поля. Ожидался Long.");
                }
            }

            while (true) {
                System.out.printf("Введите цену: ");
                request = reader.readLine();
                if (validator.validateField(request, flat.getClass().getDeclaredField("price"), flat)) break;
                else {
                    System.out.println("Произошла ошибка при вводе поля. Ожидался Integer.");
                }
            }

            while (true) {
                System.out.printf("Обозначьте дизайн(DESIGNER/NONE/FINE/LITTLE): ");
                request = reader.readLine();
                if (validator.validateField(request, flat.getClass().getDeclaredField("furnish"), flat)) break;
                else {
                    System.out.println("Произошла ошибка при вводе поля. Ожидался String.");
                }
            }

            validator = new Validator(House.class);
            House house = new House();
            while (true) {
                System.out.printf("Введите имя дома: ");
                request = reader.readLine();
                if (validator.validateField(request, house.getClass().getDeclaredField("name"), house)) break;
                else {
                    System.out.println("Произошла ошибка при вводе поля. Ожидался не пустой String.");
                }
            }

            while (true) {
                System.out.printf("Введите год постройки дома: ");
                request = reader.readLine();
                if (validator.validateField(request, house.getClass().getDeclaredField("year"), house)) break;
                else {
                    System.out.println("Произошла ошибка при вводе поля. Ожидался Long.");
                }
            }

            while (true) {
                System.out.printf("Введите колличество этажей: ");
                request = reader.readLine();
                if (validator.validateField(request, house.getClass().getDeclaredField("numberOfFloors"), house)) break;
                else {
                    System.out.println("Произошла ошибка при вводе поля. Ожидался Integer.");
                }
            }

            while (true) {
                System.out.printf("Введите колличество квартир на этаже: ");
                request = reader.readLine();
                if (validator.validateField(request, house.getClass().getDeclaredField("numberOfFlatsOnFloor"), house))
                    break;
                else {
                    System.out.println("Произошла ошибка при вводе поля. Ожидался Integer.");
                }
            }

            while (true) {
                System.out.printf("Введите колличество лифтов: ");
                request = reader.readLine();
                if (validator.validateField(request, house.getClass().getDeclaredField("numberOfLifts"), house)) break;
                else {
                    System.out.println("Произошла ошибка при вводе поля. Ожидался Long.");
                }
            }

            flat.setHouse(house);
            validator = new Validator(Coordinates.class);
            Coordinates coordinates = new Coordinates();
            while (true) {
                System.out.printf("Введите координату x: ");
                request = reader.readLine();
                if (validator.validateField(request, coordinates.getClass().getDeclaredField("x"), coordinates)) break;
                else {
                    System.out.println("Произошла ошибка при вводе поля. Ожидался Float.");
                }
            }

            while (true) {
                System.out.printf("Введите координату y: ");
                request = reader.readLine();
                if (validator.validateField(request, coordinates.getClass().getDeclaredField("y"), coordinates)) break;
                else {
                    System.out.println("Произошла ошибка при вводе поля. Ожидался Float.");
                }
            }

            validator = new Validator(Flat.class);
            flat.setCoordinates(coordinates);

        } catch (IOException | NoSuchFieldException e) {

        }
        return flat;
    }

}
