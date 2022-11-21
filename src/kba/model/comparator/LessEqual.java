package kba.model.comparator;

import kba.model.logic.LogicNode;
import kba.model.numeric.NumericNode;

public class LessEqual extends Comparator
{
    public LessEqual(NumericNode left, NumericNode right)
    {
        super((a, b) -> a <= b, left, right, LessEqual::new);
    }

    @Override
    public LogicNode negate()
    {
        return new Greater(this.left, this.right);
    }

    @Override
    public LogicNode flip()
    {
        return new GreaterEqual(this.right, this.left);
    }
}
