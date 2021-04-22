package commandDescriptions;

import support.CommandName;

public class RemoveByIdDescription extends CommandDescription{
    int id;
    public RemoveByIdDescription(CommandName name, int id) {
        super(name);
        this.id = id;
    }
}
