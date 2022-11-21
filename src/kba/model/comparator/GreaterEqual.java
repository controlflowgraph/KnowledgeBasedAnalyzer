package kba.model.comparator;

import kba.model.logic.LogicNode;
import kba.model.numeric.NumericNode;

public class GreaterEqual extends Comparator
{
    public GreaterEqual(NumericNode left, NumericNode right)
    {
        super((a, b) -> a >= b, left, right, GreaterEqual::new);
    }

    @Override
    public LogicNode negate()
    {
        return new Less(this.left, this.right);
    }

    @Override
    public LogicNode flip()
    {
        return new LessEqual(this.right, this.left);
    }
}
