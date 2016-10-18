package heuristicas;

public class PrintHeuristica implements Heuristica
{
    public void execute(Object data)
    {
        System.out.println(data.toString());
    }
}