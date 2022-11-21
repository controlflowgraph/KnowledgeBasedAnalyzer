package kba.optimization.comparator;

import kba.model.comparator.Comparator;
import kba.model.comparator.ComparatorFactory;
import kba.model.logic.LogicNode;
import kba.model.numeric.Add;
import kba.model.numeric.NumericNode;
import kba.model.numeric.Value;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SimpleCommonAdditiveOptimizer extends ComparatorOptimizer
{
    @Override
    protected Optional<LogicNode> optimizeNode(Comparator c, ComparatorFactory factory)
    {
        List<NumericNode> left = getExpanded(c.getLeft());
        List<NumericNode> right = getExpanded(c.getRight());
        boolean changed = true;
        boolean modification = false;
        while (changed)
        {
            changed = false;

            for (int i = 0; i < left.size(); i++)
            {
                NumericNode leftNode = left.get(i);
                for (int k = 0; k < right.size(); k++)
                {
                    NumericNode rightNode = right.get(k);
                    if(leftNode.equals(rightNode))
                    {
                        left.remove(i);
                        right.remove(k);
                        changed = modification = true;
                        break;
                    }
                }
            }
        }

        if(!modification)
            return noChange();

        NumericNode nowLeft = wrapIfRequired(left);
        NumericNode nowRight = wrapIfRequired(right);

        return Optional.of(factory.create(nowLeft, nowRight));
    }

    private NumericNode wrapIfRequired(List<NumericNode> nodes)
    {
        if(nodes.size() == 1) return nodes.get(0);
        else if(nodes.size() > 1) return new Add(nodes);
        return new Value(0.0);
    }

    private List<NumericNode> getExpanded(NumericNode node)
    {
        List<NumericNode> base = node instanceof Add add ? add.getNodes() : List.of(node);
        return new ArrayList<>(base);
    }
}
