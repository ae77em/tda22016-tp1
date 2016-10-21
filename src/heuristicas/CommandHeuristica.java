package heuristicas;

/**
 * Created by ariel on 18/10/16.
 */
public class CommandHeuristica {

    public static void callCommand(Heuristica command, Object... data) {
        command.execute(data);
    }

}
