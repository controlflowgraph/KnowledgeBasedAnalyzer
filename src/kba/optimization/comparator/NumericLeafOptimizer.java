package kba.optimization.comparator;

import kba.KnowledgeBasedAnalyzer;
import kba.model.comparator.Comparator;
import kba.model.comparator.ComparatorFactory;
import kba.model.logic.LogicNode;
import kba.model.numeric.NumericNode;
import kba.optimization.numeric.ConstantExpressionOptimizer;
import kba.optimization.numeric.DuplicateAdditionOptimizer;
import kba.optimization.numeric.NumericOptimizerFactory;
import kba.optimization.numeric.NumericUnfoldOptimizer;

import java.util.List;
import java.util.Optional;

public class NumericLeafOptimizer extends ComparatorOptimizer
{
    @Override
    protected Optional<LogicNode> optimizeNode(Comparator c, ComparatorFactory factory)
    {
        List<NumericOptimizerFactory> factories = List.of(
                NumericUnfoldOptimizer::new,
                ConstantExpressionOptimizer::new,
                DuplicateAdditionOptimizer::new
        );

        NumericNode left = KnowledgeBasedAnalyzer.optimize(c.getLeft(), factories);
        NumericNode right = KnowledgeBasedAnalyzer.optimize(c.getRight(), factories);

        if(!left.equals(c.getLeft()) || !right.equals(c.getRight()))
            return Optional.of(factory.create(left, right));
        return noChange();
    }
}
