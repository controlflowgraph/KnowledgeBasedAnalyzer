package kba.optimization.logic;

import kba.model.logic.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class ConstantIdempotentOptimizer extends LogicNodeVisitor
{
    @Override
    protected Optional<LogicNode> visit(And and)
    {
        return removeValue(true, And::new, and.getNodes());
    }

    @Override
    protected Optional<LogicNode> visit(Or or)
    {
        return removeValue(false, Or::new, or.getNodes());
    }

    private Optional<LogicNode> removeValue(boolean value, Function<List<LogicNode>, LogicNode> creator, List<LogicNode> current)
    {
        List<LogicNode> nodes = new ArrayList<>();
        boolean found = false;
        for (LogicNode node : current)
        {
            if(node instanceof Value v && v.getValue() == value)
            {
                found = true;
            }
            else
            {
                nodes.add(node);
            }
        }
        if(found)
            return Optional.of(creator.apply(nodes));
        return noChange();
    }
}
