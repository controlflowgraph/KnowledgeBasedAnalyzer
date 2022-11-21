package kba.model.numeric;

public class Inverse extends NumericUnaryOperator
{
    protected Inverse(NumericNode node)
    {
        super(node, v -> 1.0 / v);
    }

    @Override
    public NumericNode normalize()
    {
        NumericNode normalized = this.node.normalize();
        return normalized.invert();
    }

    @Override
    public NumericNode invert()
    {
        return this.node;
    }

    @Override
    public NumericNode negate()
    {
        return new Inverse(this.node.negate());
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Inverse inverse = (Inverse) o;
        return this.node.equals(inverse.node);
    }

    @Override
    public int hashCode()
    {
        return this.node.hashCode();
    }
}
