package kba.model.numeric;

import java.util.Objects;

public class Variable implements NumericNode
{
    private final boolean inverted;
    private final boolean negated;
    private final String name;

    public Variable(String name)
    {
        this(false, false, name);
    }

    public Variable(boolean inverted, boolean negated, String name)
    {
        this.inverted = inverted;
        this.negated = negated;
        this.name = name;
    }

    @Override
    public boolean isConstant()
    {
        return false;
    }

    @Override
    public double fold()
    {
        throw new RuntimeException("Unable to fold numeric variable!");
    }

    @Override
    public NumericNode normalize()
    {
        return this;
    }

    @Override
    public NumericNode invert()
    {
        return new Variable(!this.inverted, this.negated, this.name);
    }

    @Override
    public NumericNode negate()
    {
        return new Variable(this.inverted, !this.negated, this.name);
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Variable variable = (Variable) o;
        return inverted == variable.inverted && negated == variable.negated && Objects.equals(name, variable.name);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(inverted, negated, name);
    }

    @Override
    public String toString()
    {
        return (this.negated ? "-" : "") + (this.inverted ? "1.0/" : "") + this.name;
    }
}
