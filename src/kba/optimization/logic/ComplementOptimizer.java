package kba.optimization.logic;

import kba.model.logic.LogicNode;
import kba.model.logic.Value;
import kba.model.logic.LogicNodeVisitor;

import java.util.List;

public abstract class ComplementOptimizer extends LogicNodeVisitor
{
    protected boolean hasOpposite(List<LogicNode> nodes)
    {
        for (LogicNode node : nodes)
        {
            LogicNode negated = node.negate();
            for (LogicNode logicNode : nodes)
            {
                if(negated.equals(logicNode))
                {
                    return true;
                }
            }
        }
        return false;
    }

    protected boolean hasValue(boolean value, List<LogicNode> nodes)
    {
        for (LogicNode node : nodes)
        {
            if(node instanceof Value v && v.getValue() == value)
            {
                return true;
            }
        }
        return false;
    }
}
