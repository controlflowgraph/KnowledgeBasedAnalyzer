package kba.optimization.numeric;

import kba.model.numeric.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DuplicateAdditionOptimizer extends NumericNodeVisitor
{
    private record Entry(NumericNode node, int count)
    {
        public Entry(NumericNode node)
        {
            this(node, 1);
        }

        public Entry increase()
        {
            return new Entry(this.node, this.count + 1);
        }
    }

    @Override
    protected Optional<NumericNode> visit(Add a)
    {
        List<NumericNode> nodes = a.getNodes();
        List<Entry> entries = new ArrayList<>();
        for (NumericNode node : nodes)
        {
            boolean found = false;
            for (int i = 0; i < entries.size(); i++)
            {
                Entry entry = entries.get(i);
                if(entry.node.equals(node))
                {
                    entries.set(i, entry.increase());
                    found = true;
                }
            }

            if(!found)
            {
                entries.add(new Entry(node));
            }
        }

        if(entries.size() != nodes.size())
            return Optional.of(new Add(entries.stream()
                    .map(e -> e.count > 1 ? new Multiply(List.of(new Value(e.count), e.node)) : e.node)
                    .toList()));
        return noChange();
    }
}
