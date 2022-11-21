package kba.optimization.logic;

import kba.model.logic.And;
import kba.model.logic.LogicNode;
import kba.model.logic.Value;

import java.util.Optional;

public class ContradictionOptimizer extends ComplementOptimizer
{
    protected Optional<LogicNode> visit(And and)
    {
        boolean exists = hasValue(false, and.getNodes()) || hasOpposite(and.getNodes());
        if(exists)
            return Optional.of(new Value(false));
        return noChange();
    }
}
