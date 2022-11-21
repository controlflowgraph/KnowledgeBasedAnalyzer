package kba.model.numeric;

import kba.optimization.Optimizer;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class NumericNodeVisitor implements Optimizer<NumericNode>
{

    @Override
    public Optional<NumericNode> optimize(NumericNode value)
    {
        return walk(value);
    }
    public Optional<NumericNode> walk(NumericNode node)
    {
        return switch (node)
                {
                    case Add a -> walkOperator(Add::new, a);
                    case Multiply m -> walkOperator(Multiply::new, m);
                    default -> visitUnknown(node);
                };
    }

    private Optional<NumericNode> walkOperator(Function<List<NumericNode>, NumericNode> creator, NumericBinaryOperator original)
    {
        Optional<List<NumericNode>> contained = walkList(original.getNodes());
        if(contained.isPresent())
        {
            NumericNode changed = creator.apply(contained.get());
            Optional<NumericNode> visit = visitUnknown(changed);
            if(visit.isEmpty())
                return Optional.of(changed);
            return visit;
        }
        else
        {
            return visitUnknown(original);
        }
    }

    private Optional<List<NumericNode>> walkList(List<NumericNode> current)
    {
        List<NumericNode> nodes = new ArrayList<>();
        boolean changed = false;
        for (NumericNode logicNode : current)
        {
            Optional<NumericNode> logicNode1 = walk(logicNode);
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

    private Optional<NumericNode> visitUnknown(NumericNode node)
    {
        return switch (node)
                {
                    case Add a -> visit(a);
                    case Multiply o -> visit(o);
                    case Variable v -> visit(v);
                    case Value v -> visit(v);
                    case Inverse i -> visit(i);
                    default -> throw new RuntimeException("Unable to walk unknown node type! " + node.getClass());
                };
    }

    protected Optional<NumericNode> visit(Add a)
    {
        return noChange();
    }

    protected Optional<NumericNode> visit(Multiply m)
    {
        return noChange();
    }

    protected Optional<NumericNode> visit(Negate n)
    {
        return noChange();
    }

    protected Optional<NumericNode> visit(Inverse i)
    {
        return noChange();
    }

    protected Optional<NumericNode> visit(Variable v)
    {
        return noChange();
    }

    protected Optional<NumericNode> visit(Value v)
    {
        return noChange();
    }

    protected Optional<NumericNode> noChange()
    {
        return Optional.empty();
    }
}
