package kba;

import kba.model.comparator.Comparator;
import kba.model.logic.And;
import kba.model.logic.LogicNode;
import kba.model.numeric.NumericNode;
import kba.optimization.Optimizer;
import kba.optimization.common.AndVisitor;
import kba.optimization.comparator.ConstantComparatorOptimizer;
import kba.optimization.comparator.FlipOptimizer;
import kba.optimization.comparator.NumericLeafOptimizer;
import kba.optimization.comparator.SimpleCommonAdditiveOptimizer;
import kba.optimization.logic.*;
import kba.optimization.numeric.NumericOptimizerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static kba.model.logic.LogicBuilder.and;
import static kba.model.numeric.NumericBuilder.*;

public class KnowledgeBasedAnalyzer
{
    public static void main(String[] args)
    {
        //System.out.println(less(var("x"), var("y")).equals(less(var("y"), var("x"))));
//        LogicNode l = and(less(add(num(5), num(10)), num(20)), greater(var("y"), add(var("x"), var("x"))));
        LogicNode l = greater(var("y"), add(var("x"), var("y"), var("x")));
        analyze(l);

        //LogicNode l = and(or(val("x"), val("y")), not(val("x")));
//        LogicNode l = or(not(val("b")), val("a"), val("b"));
//        LogicNode l = and(or(val("x"), val("y")), not(val("x")));
        //LogicNode l = or(val("x"), val("x"));
//        LogicNode l = and(val(true), val("x"), val("x"));
//        List<OptimizerFactory> factories = List.of(
//                ContradictionOptimizer::new,
//                TautologyOptimizer::new,
//                IdempotentOptimizer::new,
//                ConstantIdempotentOptimizer::new,
//                UnfoldOptimizer::new
//        );
//        System.out.println(l);
//        LogicNode optimized = optimize(l, factories);
//        System.out.println(optimized);

//        List<NumericOptimizerFactory> factories = List.of(
//                NumericUnfoldOptimizer::new,
//                ConstantExpressionOptimizer::new
//        );
////        NumericNode mul = inv(mul(add(var("x"), var("y")), var("z")));
//        NumericNode mul = div(num(10), num(20));
//        System.out.println(mul);
//        NumericNode optimized = optimize(mul, factories);
//        System.out.println(optimized);
    }

    public static void analyze(LogicNode node)
    {
        LogicNode normalized = node.normalize();
        List<LogicOptimizerFactory> factories = List.of(
                ContradictionOptimizer::new,
                TautologyOptimizer::new,
                IdempotentOptimizer::new,
                ConstantIdempotentOptimizer::new,
                UnfoldOptimizer::new,
                FlipOptimizer::new,
                NumericLeafOptimizer::new,
                ConstantComparatorOptimizer::new,
                SimpleCommonAdditiveOptimizer::new
        );
        LogicNode optimized = optimize(normalized, factories);
        System.out.println(optimized);
    }

    public static LogicNode optimize(LogicNode node, List<LogicOptimizerFactory> optimizers)
    {
        List<Optimizer<LogicNode>> factories = optimizers.stream()
                .map(LogicOptimizerFactory::create)
                .toList();
        LogicNode normalize = node.normalize();
        return optimizeNode(normalize, factories);
    }

    public static NumericNode optimize(NumericNode node, List<NumericOptimizerFactory> optimizers)
    {
        List<Optimizer<NumericNode>> factories = optimizers.stream()
                .map(NumericOptimizerFactory::create)
                .toList();
        NumericNode normalize = node.normalize();
        return optimizeNode(normalize, factories);
    }

    public static <T> T optimizeNode(T node, List<Optimizer<T>> optimizers)
    {
        T current = node;
        boolean changed = true;
        while (changed)
        {
            changed = false;
            for (Optimizer<T> visitor : optimizers)
            {
                Optional<T> walk = visitor.optimize(current);
                if(walk.isPresent())
                {
                    changed = true;
                    current = walk.get();
                }
            }
        }
        return current;
    }
}

