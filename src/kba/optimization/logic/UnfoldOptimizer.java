package kba.optimization.logic;

import kba.model.logic.*;

import java.util.List;
import java.util.Optional;

public class UnfoldOptimizer extends LogicNodeVisitor
{
    @Override
    protected Optional<LogicNode> visit(And and)
    {
        return getIfLast(and);
    }

    @Override
    protected Optional<LogicNode> visit(Or or)
    {
        return getIfLast(or);
    }

    private Optional<LogicNode> getIfLast(LogicBinaryOperator op)
    {
        List<LogicNode> nodes = op.getNodes();
        if(nodes.size() == 1)
            return Optional.of(nodes.get(0));
        return noChange();
    }
}
