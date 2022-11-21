package kba.optimization.logic;

import kba.model.logic.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class IdempotentOptimizer extends LogicNodeVisitor
{
    @Override
    protected Optional<LogicNode> visit(And and)
    {
        return removeDuplicates(And::new, and.getNodes());
    }

    @Override
    protected Optional<LogicNode> visit(Or or)
    {
        return removeDuplicates(Or::new, or.getNodes());
    }

    private Optional<LogicNode> removeDuplicates(Function<List<LogicNode>, LogicNode> creator, List<LogicNode> current)
    {
        List<LogicNode> nodes = new ArrayList<>();
        boolean found = false;
        for (LogicNode node : current)
        {
            boolean exists = false;
            for (LogicNode logicNode : nodes)
            {
                if (node.equals(logicNode))
                {
                    exists = true;
                    break;
                }
            }
            if(!exists)
            {
                nodes.add(node);
            }
            else
            {
                found = true;
            }
        }
        if(found)
            return Optional.of(creator.apply(nodes));
        return noChange();
    }

}
