package kba.optimization.comparator;

import kba.model.comparator.Comparator;
import kba.model.comparator.ComparatorFactory;
import kba.model.logic.LogicNode;
import kba.model.logic.Value;

import java.util.Optional;

public class ConstantComparatorOptimizer extends ComparatorOptimizer
{
    @Override
    protected Optional<LogicNode> optimizeNode(Comparator c, ComparatorFactory factory)
    {
        if(c.isConstant())
            return Optional.of(new Value(c.fold()));
        return noChange();
    }
}
