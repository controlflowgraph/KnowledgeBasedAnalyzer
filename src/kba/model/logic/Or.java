package kba.model.logic;

import java.util.ArrayList;
import java.util.List;

public class Or extends LogicBinaryOperator
{
    public Or(List<LogicNode> nodes)
    {
        super(nodes, false, (a, b) -> a || b);
    }

    @Override
    public LogicNode normalize()
    {
        List<LogicNode> next = new ArrayList<>();
        List<LogicNode> current = this.nodes.stream()
                .map(LogicNode::normalize)
                .toList();
        for (LogicNode l : current)
        {
            if (l instanceof Or o)
            {
                next.addAll(o.nodes);
            }
            else
            {
                next.add(l);
            }
        }
        return new Or(next);
    }

    @Override
    public LogicNode negate()
    {
        return new And(this.nodes.stream()
                .map(LogicNode::negate)
                .toList());
    }
}
