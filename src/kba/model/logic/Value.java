package kba.model.logic;

public class Value implements LogicNode
{
    private final boolean value;

    public Value(boolean value)
    {
        this.value = value;
    }

    @Override
    public boolean isConstant()
    {
        return true;
    }

    @Override
    public boolean fold()
    {
        return this.value;
    }

    @Override
    public LogicNode normalize()
    {
        return this;
    }

    @Override
    public LogicNode negate()
    {
        return new Value(!this.value);
    }

    @Override
    public String toString()
    {
        return this.value ? "#t" : "#f";
    }

    public boolean getValue()
    {
        return this.value;
    }
}
