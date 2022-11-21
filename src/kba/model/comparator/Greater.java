package kba.model.comparator;

import kba.model.logic.LogicNode;
import kba.model.numeric.NumericNode;

public class Greater extends Comparator
{
    public Greater(NumericNode left, NumericNode right)
    {
        super((a, b) -> a > b, left, right, Greater::new);
    }

    @Override
    public LogicNode negate()
    {
        return new LessEqual(this.left, this.right);
    }

    @Override
    public LogicNode flip()
    {
        return new Less(this.right, this.left);
    }
}
