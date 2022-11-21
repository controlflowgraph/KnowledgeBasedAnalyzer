package kba.model.numeric;

public class Negate extends NumericUnaryOperator
{
    protected Negate(NumericNode node)
    {
        super(node, v -> -v);
    }

    @Override
    public NumericNode normalize()
    {
        return this.node.negate();
    }

    @Override
    public NumericNode invert()
    {
        return new Negate(this.node.normalize().invert());
    }

    @Override
    public NumericNode negate()
    {
        return this.node;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Negate negate = (Negate) o;
        return this.node.equals(negate.node);
    }

    @Override
    public int hashCode()
    {
        return this.node.hashCode();
    }
}
