package kba.model.logic;

import java.util.Arrays;

public class LogicBuilder
{
    public static LogicNode and(LogicNode ... nodes)
    {
        return new And(Arrays.asList(nodes));
    }

    public static LogicNode or(LogicNode ... nodes)
    {
        return new Or(Arrays.asList(nodes));
    }

    public static LogicNode not(LogicNode nodes)
    {
        return new Not(nodes);
    }

    public static LogicNode val(String name)
    {
        return new Variable(name);
    }

    public static LogicNode val(boolean value)
    {
        return new Value(value);
    }
}
