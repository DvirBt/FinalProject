package RealEstate.Strategy;

/**
 * This class gets a different strategy each time and executes it when the function executeStrategy is called.
 */
public class Search {

    private IteratorStrategy strategy;

    public Search(IteratorStrategy strategy)
    {
        this.strategy = strategy;
    }

    public double executeStrategy()
    {
        return strategy.search();
    }
}
