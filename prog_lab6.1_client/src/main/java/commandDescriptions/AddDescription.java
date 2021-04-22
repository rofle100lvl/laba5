package commandDescriptions;

import startClasses.Flat;
import support.CommandName;


public class AddDescription extends CommandDescription{
    Flat flat;

    public AddDescription(CommandName name,Flat flat) {
        super(name);
        this.flat=flat;
    }
}
