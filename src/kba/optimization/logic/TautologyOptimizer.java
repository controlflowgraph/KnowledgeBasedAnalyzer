package kba.optimization.logic;

import kba.model.logic.LogicNode;
import kba.model.logic.Or;
import kba.model.logic.Value;

import java.util.Optional;

public class TautologyOptimizer extends ComplementOptimizer
{
    @Override
    protected Optional<LogicNode> visit(Or or)
    {
        boolean exists = hasValue(true, or.getNodes()) || hasOpposite(or.getNodes());
        if(exists)
            return Optional.of(new Value(true));
        return noChange();
    }
}
