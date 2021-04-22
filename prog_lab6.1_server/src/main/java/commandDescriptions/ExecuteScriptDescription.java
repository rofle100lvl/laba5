package commandDescriptions;

import support.CommandName;

public class ExecuteScriptDescription extends CommandDescription
{
    String file;
    public ExecuteScriptDescription(CommandName name, String file) {
        super(name);
        this.file = file;
    }
}
