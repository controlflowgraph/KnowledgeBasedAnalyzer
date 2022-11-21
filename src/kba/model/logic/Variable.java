package kba.model.logic;

import java.util.Objects;

public class Variable implements LogicNode
{
    private final boolean negated;
    private final String name;

    public Variable(String name)
    {
        this(false, name);
    }

    public Variable(boolean negated, String name)
    {
        this.negated = negated;
        this.name = name;
    }

    @Override
    public boolean isConstant()
    {
        return false;
    }

    @Override
    public boolean fold()
    {
        throw new RuntimeException("Unable to fold variable!");
    }

    @Override
    public LogicNode normalize()
    {
        return this;
    }

    @Override
    public LogicNode negate()
    {
        return new Variable(!this.negated, this.name);
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Variable variable = (Variable) o;
        return negated == variable.negated && Objects.equals(name, variable.name);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(negated, name);
    }

    @Override
    public String toString()
    {
        return (this.negated ? "!" : "") + this.name;
    }

    public boolean isNegated()
    {
        return this.negated;
    }

    public String getName()
    {
        return this.name;
    }
}
