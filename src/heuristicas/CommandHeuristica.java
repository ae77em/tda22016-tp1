package heuristicas;

/**
 * Created by ariel on 18/10/16.
 */
public class CommandHeuristica {

    public static void callCommand(PrintHeuristica command, Object data)
    {
        command.execute(data);
    }

}
