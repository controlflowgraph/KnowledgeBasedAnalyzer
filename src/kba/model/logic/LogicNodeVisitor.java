package kba.model.logic;

import kba.model.comparator.*;
import kba.optimization.Optimizer;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public abstract class LogicNodeVisitor implements Optimizer<LogicNode>
{
    @Override
    public Optional<LogicNode> optimize(LogicNode value)
    {
        return walk(value);
    }

    public Optional<LogicNode> walk(LogicNode node)
    {
        return switch (node)
                {
                    case And a -> walkOperator(And::new, a);
                    case Or o -> walkOperator(Or::new, o);
                    default -> visitUnknown(node);
                };
    }

    private Optional<LogicNode> walkOperator(Function<List<LogicNode>, LogicBinaryOperator> creator, LogicBinaryOperator original)
    {
        Optional<List<LogicNode>> contained = walkList(original.getNodes());
        if(contained.isPresent())
        {
            LogicBinaryOperator changed = creator.apply(contained.get());
            Optional<LogicNode> visit = visitUnknown(changed);
            if(visit.isEmpty())
                return Optional.of(changed);
            return visit;
        }
        else
        {
            return visitUnknown(original);
        }
    }

    private Optional<LogicNode> visitUnknown(LogicNode node)
    {
        return switch (node)
                {
                    case And a -> visit(a);
                    case Or o -> visit(o);
                    case Variable v -> visit(v);
                    case Value v -> visit(v);
                    case Less l -> visit(l);
                    case LessEqual l -> visit(l);
                    case Greater g -> visit(g);
                    case GreaterEqual g -> visit(g);
                    case Equal e -> visit(e);
                    case Unequal u -> visit(u);
                    default -> throw new RuntimeException("Unable to walk unknown node type! " + node.getClass());
                };
    }

    private Optional<List<LogicNode>> walkList(List<LogicNode> current)
    {
        List<LogicNode> nodes = new ArrayList<>();
        boolean changed = false;
        for (LogicNode logicNode : current)
        {
            Optional<LogicNode> logicNode1 = walk(logicNode);
            if(logicNode1.isPresent())
            {
                nodes.add(logicNode1.get());
                changed = true;
            }
            else
            {
                nodes.add(logicNode);
            }
        }
        if(changed)
            return Optional.of(nodes);
        return Optional.empty();
    }

    protected Optional<LogicNode> visit(And a)
    {
        return noChange();
    }

    protected Optional<LogicNode> visit(Or o)
    {
        return noChange();
    }

    protected Optional<LogicNode> visit(Variable v)
    {
        return noChange();
    }

    protected Optional<LogicNode> visit(Value v)
    {
        return noChange();
    }

    protected Optional<LogicNode> visit(Less l)
    {
        return noChange();
    }

    protected Optional<LogicNode> visit(LessEqual l)
    {
        return noChange();
    }

    protected Optional<LogicNode> visit(Greater g)
    {
        return noChange();
    }

    protected Optional<LogicNode> visit(GreaterEqual g)
    {
        return noChange();
    }

    protected Optional<LogicNode> visit(Equal e)
    {
        return noChange();
    }

    protected Optional<LogicNode> visit(Unequal u)
    {
        return noChange();
    }

    protected Optional<LogicNode> noChange()
    {
        return Optional.empty();
    }
}
