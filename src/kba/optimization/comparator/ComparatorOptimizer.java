package kba.optimization.comparator;

import kba.model.comparator.*;
import kba.model.logic.LogicNode;
import kba.model.logic.LogicNodeVisitor;

import java.util.Optional;

public abstract class ComparatorOptimizer extends LogicNodeVisitor
{
    @Override
    protected Optional<LogicNode> visit(Less l)
    {
        return optimizeNode(l, Less::new);
    }

    @Override
    protected Optional<LogicNode> visit(LessEqual l)
    {
        return optimizeNode(l, LessEqual::new);
    }

    @Override
    protected Optional<LogicNode> visit(Greater g)
    {
        return optimizeNode(g, Greater::new);
    }

    @Override
    protected Optional<LogicNode> visit(GreaterEqual g)
    {
        return optimizeNode(g, GreaterEqual::new);
    }

    @Override
    protected Optional<LogicNode> visit(Equal e)
    {
        return optimizeNode(e, Equal::new);
    }

    @Override
    protected Optional<LogicNode> visit(Unequal u)
    {
        return optimizeNode(u, Unequal::new);
    }

    protected abstract Optional<LogicNode> optimizeNode(Comparator c, ComparatorFactory factory);
}
