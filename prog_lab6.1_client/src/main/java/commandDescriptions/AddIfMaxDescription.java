package commandDescriptions;

import startClasses.Flat;
import support.CommandName;

public class AddIfMaxDescription extends CommandDescription{
    Flat flat;

    public AddIfMaxDescription(CommandName name,Flat flat) {
        super(name);
        this.flat = flat;

    }
}
