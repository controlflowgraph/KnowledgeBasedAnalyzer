package kba.optimization.numeric;

import kba.model.numeric.*;

import java.util.List;
import java.util.Optional;

public class NumericUnfoldOptimizer extends NumericNodeVisitor
{
    @Override
    protected Optional<NumericNode> visit(Add a)
    {
        return unfold(a);
    }

    @Override
    protected Optional<NumericNode> visit(Multiply m)
    {
        return unfold(m);
    }

    private Optional<NumericNode> unfold(NumericBinaryOperator op)
    {
        List<NumericNode> nodes = op.getNodes();
        if(nodes.size() == 1)
            return Optional.of(nodes.get(0));
        return Optional.empty();
    }
}
