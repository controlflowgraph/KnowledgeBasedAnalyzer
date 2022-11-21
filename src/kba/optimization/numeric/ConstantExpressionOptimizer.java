package kba.optimization.numeric;

import kba.model.numeric.*;

import java.util.Optional;

public class ConstantExpressionOptimizer extends NumericNodeVisitor
{
    @Override
    protected Optional<NumericNode> visit(Add a)
    {
        return foldIfConstant(a);
    }

    @Override
    protected Optional<NumericNode> visit(Multiply m)
    {
        return foldIfConstant(m);
    }

    @Override
    protected Optional<NumericNode> visit(Negate n)
    {
        return foldIfConstant(n);
    }

    @Override
    protected Optional<NumericNode> visit(Inverse i)
    {
        return foldIfConstant(i);
    }

    private Optional<NumericNode> foldIfConstant(NumericNode node)
    {
        if(node.isConstant())
            return Optional.of(new Value(node.fold()));
        return noChange();
    }
}
