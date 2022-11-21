package kba.model.numeric;

import java.util.List;
import java.util.Objects;
import java.util.function.BinaryOperator;

public abstract class NumericBinaryOperator implements NumericNode
{
    protected final List<NumericNode> nodes;
    private final double start;
    private final BinaryOperator<Double> operator;

    protected NumericBinaryOperator(List<NumericNode> nodes, double start, BinaryOperator<Double> operator)
    {
        this.nodes = nodes;
        this.start = start;
        this.operator = operator;
    }

    @Override
    public boolean isConstant()
    {
        return this.nodes.stream()
                .map(NumericNode::isConstant)
                .reduce(true, (a, b) -> a && b);
    }

    @Override
    public double fold()
    {
        return this.nodes.stream()
                .map(NumericNode::fold)
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
        NumericBinaryOperator that = (NumericBinaryOperator) o;
        if(this.nodes.size() != that.nodes.size())
            return false;
        boolean[] marked = new boolean[this.nodes.size()];
        for (int i = 0; i < this.nodes.size(); i++)
        {
            for (int k = 0; k < that.nodes.size(); k++)
            {
                if(!marked[k] && this.nodes.get(i).equals(that.nodes.get(k)))
                {
                    marked[k] = true;
                }
            }
        }
        for (boolean b : marked)
        {
            if(!b)
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

    public List<NumericNode> getNodes()
    {
        return this.nodes;
    }
}
