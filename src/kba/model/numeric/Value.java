package kba.model.numeric;

import java.util.Objects;

public class Value implements NumericNode
{
    private final double value;

    public Value(double value)
    {
        this.value = value;
    }

    @Override
    public boolean isConstant()
    {
        return true;
    }

    @Override
    public double fold()
    {
        return this.value;
    }

    @Override
    public NumericNode normalize()
    {
        return this;
    }

    @Override
    public NumericNode invert()
    {
        return new Value(1.0 / this.value);
    }

    @Override
    public NumericNode negate()
    {
        return new Value(-this.value);
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Value value1 = (Value) o;
        return Double.compare(value1.value, value) == 0;
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(value);
    }

    @Override
    public String toString()
    {
        return Double.toString(this.value);
    }
}
