package kba.model.logic;

import java.util.ArrayList;
import java.util.List;

public class And extends LogicBinaryOperator
{
    public And(List<LogicNode> nodes)
    {
        super(nodes, true, (a, b) -> a && b);
    }

    @Override
    public LogicNode normalize()
    {
        List<LogicNode> current = this.nodes.stream()
                .map(LogicNode::normalize)
                .toList();
        List<Or> ors = new ArrayList<>();
        List<LogicNode> rest = new ArrayList<>();
        for (LogicNode l : current)
        {
            switch (l)
            {
                case Or o -> ors.add(o);
                case And a -> rest.addAll(a.nodes);
                default -> rest.add(l);
            }
        }

        if(ors.isEmpty())
            return new And(rest);
        return new Or(expand(rest, ors));
    }

    @Override
    public LogicNode negate()
    {
        return new Or(this.nodes.stream()
                .map(LogicNode::negate)
                .toList());
    }

    private List<LogicNode> expand(List<LogicNode> rest, List<Or> lst)
    {
        List<List<LogicNode>> everything = new ArrayList<>(List.of(rest));
        for (Or or : lst)
        {
            List<List<LogicNode>> iteration = new ArrayList<>();
            for (LogicNode node : or.nodes)
            {
                for (List<LogicNode> logicNodes : everything)
                {
                    List<LogicNode> copy = new ArrayList<>(logicNodes);
                    copy.add(node);
                    iteration.add(copy);
                }
            }
            everything = iteration;
        }

        return everything.stream()
                .map(And::new)
                .map(LogicNode.class::cast)
                .toList();
    }
}
