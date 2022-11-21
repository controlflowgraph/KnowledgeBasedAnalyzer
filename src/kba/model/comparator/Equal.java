package kba.model.comparator;

import kba.model.logic.LogicNode;
import kba.model.numeric.NumericNode;

import java.util.Objects;

public class Equal extends Comparator
{
    public Equal(NumericNode left, NumericNode right)
    {
        super(Objects::equals, left, right, Equal::new);
    }

    @Override
    public LogicNode negate()
    {
        return new Unequal(this.left, this.right);
    }

    @Override
    public LogicNode flip()
    {
        return new Equal(this.right, this.left);
    }
}
