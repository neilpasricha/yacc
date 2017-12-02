public class Env {
    // You have to re-implement this to make your SemanticChecker works correct
    private java.util.HashMap<String, Object> table = new java.util.HashMap<String, Object>();
    public Env(Env prev)
    {
        // You have to re-implement this to make your SemanticChecker works correct
    }
    public void Put(String name, Object value)
    {
        // You have to re-implement this to make your SemanticChecker works correct
        assert name != null;
        assert value != null;
        table.put(name, value);
    }
    public Object Get(String name)
    {
        // You have to re-implement this to make your SemanticChecker works correct
        return table.get(name);
    }
}
