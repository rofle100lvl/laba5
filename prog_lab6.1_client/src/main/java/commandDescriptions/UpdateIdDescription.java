package commandDescriptions;

import startClasses.Flat;
import support.CommandName;

public class UpdateIdDescription extends CommandDescription{
    Flat flat;
    int id;

    public UpdateIdDescription(CommandName name, Flat flat,int id) {
        super(name);
        this.flat=flat;
        this.id = id;
    }

}
