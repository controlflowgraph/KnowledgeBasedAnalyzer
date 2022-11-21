package kba.model.comparator;

import kba.model.logic.LogicNode;
import kba.model.numeric.NumericNode;

import java.util.Objects;
import java.util.function.BiPredicate;

public abstract class Comparator implements LogicNode
{
    private final BiPredicate<Double, Double> operator;
    protected final NumericNode left;
    protected final NumericNode right;
    private final ComparatorFactory current;

    protected Comparator(BiPredicate<Double, Double> operator, NumericNode left, NumericNode right, ComparatorFactory current)
    {
        this.operator = operator;
        this.left = left;
        this.right = right;
        this.current = current;
    }

    @Override
    public boolean isConstant()
    {
        return this.left.isConstant() && this.right.isConstant();
    }

    @Override
    public boolean fold()
    {
        return this.operator.test(this.left.fold(), this.right.fold());
    }

    @Override
    public LogicNode normalize()
    {
        return this.current.create(this.left.normalize(), this.right.normalize());
    }

    public abstract LogicNode flip();

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comparator that = (Comparator) o;
        return Objects.equals(operator, that.operator) && Objects.equals(left, that.left) && Objects.equals(right, that.right) && Objects.equals(current, that.current);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(operator, left, right, current);
    }

    @Override
    public String toString()
    {
        return getClass().getSimpleName() + "[" + this.left + ", " + this.right + "]";
    }

    public NumericNode getLeft()
    {
        return this.left;
    }

    public NumericNode getRight()
    {
        return this.right;
    }
}
