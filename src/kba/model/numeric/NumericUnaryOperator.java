package kba.model.numeric;

import java.util.function.Function;
import java.util.function.UnaryOperator;

public abstract class NumericUnaryOperator implements NumericNode
{
    protected final NumericNode node;
    private final Function<Double, Double> operator;

    protected NumericUnaryOperator(NumericNode node, UnaryOperator<Double> operator)
    {
        this.node = node;
        this.operator = operator;
    }

    @Override
    public boolean isConstant()
    {
        return this.node.isConstant();
    }

    @Override
    public double fold()
    {
        return this.operator.apply(this.node.fold());
    }

    @Override
    public String toString()
    {
        return getClass().getSimpleName() + "[" + this.node + "]";
    }
}
