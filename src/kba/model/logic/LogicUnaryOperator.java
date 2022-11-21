package kba.model.logic;

import java.util.function.Function;
import java.util.function.UnaryOperator;

public abstract class LogicUnaryOperator implements LogicNode
{
    protected final LogicNode node;
    private final Function<Boolean, Boolean> operator;

    protected LogicUnaryOperator(LogicNode node, UnaryOperator<Boolean> operator)
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
    public boolean fold()
    {
        return this.operator.apply(this.node.fold());
    }

    @Override
    public String toString()
    {
        return getClass().getSimpleName() + "[" + this.node + "]";
    }
}
