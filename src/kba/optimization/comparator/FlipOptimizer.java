package kba.optimization.comparator;

import kba.model.comparator.Comparator;
import kba.model.logic.And;
import kba.model.logic.LogicNode;
import kba.model.logic.LogicNodeVisitor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FlipOptimizer extends LogicNodeVisitor
{
    @Override
    protected Optional<LogicNode> visit(And a)
    {
        List<LogicNode> nodes = a.getNodes();
        List<LogicNode> changed = new ArrayList<>();
        for (LogicNode logicNode : nodes)
        {
            boolean found = false;
            for (LogicNode node1 : changed)
            {
                if(logicNode.equals(node1) || node1 instanceof Comparator c && logicNode.equals(c.flip()))
                {
                    found = true;
                }
            }
            if(!found)
            {
                changed.add(logicNode);
            }
        }
        if(nodes.size() != changed.size())
            return Optional.of(new And(changed));
        return Optional.empty();
    }
}
