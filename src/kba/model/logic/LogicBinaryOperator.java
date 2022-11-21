package kba.model.logic;

import java.util.List;
import java.util.Objects;
import java.util.function.BinaryOperator;

public abstract class LogicBinaryOperator implements LogicNode
{
    protected final List<LogicNode> nodes;
    private final boolean start;
    private final BinaryOperator<Boolean> operator;

    protected LogicBinaryOperator(List<LogicNode> nodes, boolean start, BinaryOperator<Boolean> operator)
    {
        this.nodes = nodes;
        this.start = start;
        this.operator = operator;
    }

    @Override
    public boolean isConstant()
    {
        return this.nodes.stream()
                .map(LogicNode::isConstant)
                .reduce(true, (a, b) -> a && b);
    }

    @Override
    public boolean fold()
    {
        return this.nodes.stream()
                .map(LogicNode::fold)
                .reduce(this.start, this.operator);
    }

    @Override
    public String toString()
    {
        return getClass().getSimpleName() + this.nodes;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LogicBinaryOperator that = (LogicBinaryOperator) o;
        if (this.nodes.size() != that.nodes.size())
            return false;
        boolean[] marked = new boolean[this.nodes.size()];
        for (int i = 0; i < this.nodes.size(); i++)
        {
            for (int k = 0; k < that.nodes.size(); k++)
            {
                if (!marked[k] && this.nodes.get(i).equals(that.nodes.get(k)))
                {
                    marked[k] = true;
                }
            }
        }
        for (boolean b : marked)
        {
            if (!b)
            {
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(nodes, start, operator);
    }

    public List<LogicNode> getNodes()
    {
        return this.nodes;
    }
}
