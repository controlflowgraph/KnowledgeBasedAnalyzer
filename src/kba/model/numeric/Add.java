package kba.model.numeric;

import java.util.ArrayList;
import java.util.List;

public class Add extends NumericBinaryOperator
{
    public Add(List<NumericNode> nodes)
    {
        super(nodes, 0.0, Double::sum);
    }

    @Override
    public NumericNode normalize()
    {
        List<NumericNode> normalized = this.nodes.stream()
                .map(NumericNode::normalize)
                .toList();
        List<NumericNode> merged = new ArrayList<>();
        for (NumericNode numericNode : normalized)
        {
            if(numericNode instanceof Add a)
            {
                merged.addAll(a.nodes);
            }
            else
            {
                merged.add(numericNode);
            }
        }
        return new Add(merged);
    }

    @Override
    public NumericNode invert()
    {
        return new Inverse(this);
    }

    @Override
    public NumericNode negate()
    {
        return new Add(this.nodes.stream()
                .map(NumericNode::negate)
                .toList());
    }
}
