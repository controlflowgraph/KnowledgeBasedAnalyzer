package kba.optimization.common;

import kba.model.logic.And;
import kba.model.logic.LogicNode;
import kba.model.logic.LogicNodeVisitor;

import java.util.Optional;
import java.util.function.Function;

public class AndVisitor extends LogicNodeVisitor
{
    private final Function<And, Optional<LogicNode>> callback;

    public AndVisitor(Function<And, Optional<LogicNode>> callback)
    {
        this.callback = callback;
    }

    @Override
    protected Optional<LogicNode> visit(And a)
    {
        return this.callback.apply(a);
    }
}
