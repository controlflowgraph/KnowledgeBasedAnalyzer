package kba.model.logic;

public class Not extends LogicUnaryOperator
{
    protected Not(LogicNode node)
    {
        super(node, v -> !v);
    }

    @Override
    public LogicNode normalize()
    {
        return this.node.negate().normalize();
    }

    @Override
    public LogicNode negate()
    {
        return this.node;
    }
}
