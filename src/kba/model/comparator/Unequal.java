package kba.model.comparator;

import kba.model.logic.LogicNode;
import kba.model.numeric.NumericNode;

import java.util.Objects;

public class Unequal extends Comparator
{
    public Unequal(NumericNode left, NumericNode right)
    {
        super((a, b) -> !Objects.equals(a, b), left, right, Unequal::new);
    }

    @Override
    public LogicNode negate()
    {
        return new Equal(this.left, this.right);
    }

    @Override
    public LogicNode flip()
    {
        return new Unequal(this.right, this.left);
    }
}
