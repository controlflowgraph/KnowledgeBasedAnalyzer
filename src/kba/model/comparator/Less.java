package kba.model.comparator;

import kba.model.logic.LogicNode;
import kba.model.numeric.NumericNode;

public class Less extends Comparator
{
    public Less(NumericNode left, NumericNode right)
    {
        super((a, b) -> a < b, left, right, Less::new);
    }

    @Override
    public LogicNode negate()
    {
        return new GreaterEqual(this.left, this.right);
    }

    @Override
    public LogicNode flip()
    {
        return new Greater(this.right, this.left);
    }
}
