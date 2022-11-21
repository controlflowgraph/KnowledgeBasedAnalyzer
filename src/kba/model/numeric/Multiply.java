package kba.model.numeric;

import java.util.ArrayList;
import java.util.List;

public class Multiply extends NumericBinaryOperator
{
    public Multiply(List<NumericNode> nodes)
    {
        super(nodes, 1.0, (a, b) -> a * b);
    }

    @Override
    public NumericNode normalize()
    {
        List<NumericNode> current = this.nodes.stream()
                .map(NumericNode::normalize)
                .toList();
        List<Add> adds = new ArrayList<>();
        List<NumericNode> rest = new ArrayList<>();
        for (NumericNode l : current)
        {
            switch (l)
            {
                case Add o -> adds.add(o);
                case Multiply a -> rest.addAll(a.nodes);
                default -> rest.add(l);
            }
        }

        if(adds.isEmpty())
            return new Multiply(rest);
        return new Add(expand(rest, adds));
    }

    @Override
    public NumericNode invert()
    {
        return new Multiply(this.nodes.stream()
                .map(NumericNode::invert)
                .toList());
    }

    @Override
    public NumericNode negate()
    {
        // TODO: should i really just apply it to the first?
        //       or should i leave it?
        List<NumericNode> nodes = new ArrayList<>();
        nodes.add(this.nodes.get(0).negate());
        nodes.addAll(this.nodes.subList(1, this.nodes.size()));
        return new Multiply(nodes);
    }

    private List<NumericNode> expand(List<NumericNode> rest, List<Add> lst)
    {
        List<List<NumericNode>> everything = new ArrayList<>(List.of(rest));
        for (Add add : lst)
        {
            List<List<NumericNode>> iteration = new ArrayList<>();
            for (NumericNode node : add.nodes)
            {
                for (List<NumericNode> logicNodes : everything)
                {
                    List<NumericNode> copy = new ArrayList<>(logicNodes);
                    copy.add(node);
                    iteration.add(copy);
                }
            }
            everything = iteration;
        }

        return everything.stream()
                .map(Multiply::new)
                .map(NumericNode.class::cast)
                .toList();
    }
}
