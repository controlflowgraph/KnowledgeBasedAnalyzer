package kba.model.numeric;

import kba.model.comparator.Greater;
import kba.model.comparator.GreaterEqual;
import kba.model.comparator.Less;
import kba.model.comparator.LessEqual;
import kba.model.logic.LogicNode;

import java.util.Arrays;
import java.util.List;

public class NumericBuilder
{
    public static NumericNode add(NumericNode ... nodes)
    {
        return new Add(Arrays.asList(nodes));
    }

    public static NumericNode mul(NumericNode ... nodes)
    {
        return new Multiply(Arrays.asList(nodes));
    }

    public static NumericNode inv(NumericNode node)
    {
        return new Inverse(node);
    }

    public static NumericNode neg(NumericNode node)
    {
        return new Negate(node);
    }

    public static NumericNode sub(NumericNode left, NumericNode right)
    {
        return new Add(List.of(left, new Negate(right)));
    }

    public static NumericNode div(NumericNode top, NumericNode bottom)
    {
        return new Multiply(List.of(top, new Inverse(bottom)));
    }

    public static NumericNode var(String name)
    {
        return new Variable(name);
    }

    public static NumericNode num(double value)
    {
        return new Value(value);
    }

    public static LogicNode less(NumericNode left, NumericNode right)
    {
        return new Less(left, right);
    }

    public static LogicNode lessEqual(NumericNode left, NumericNode right)
    {
        return new LessEqual(left, right);
    }

    public static LogicNode greater(NumericNode left, NumericNode right)
    {
        return new Greater(left, right);
    }

    public static LogicNode greaterEqual(NumericNode left, NumericNode right)
    {
        return new GreaterEqual(left, right);
    }
}
