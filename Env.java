public class Env {
    // You have to re-implement this to make your SemanticChecker works correct
    private java.util.HashMap<String, Object> table = new java.util.HashMap<String, Object>();
    public Env previous;
    public Env(Env prev)
    {
        previous=prev;
        // You have to re-implement this to make your SemanticChecker works correct
    }
    public void Put(String name, Object value)
    {
        // You have to re-implement this to make your SemanticChecker works correct
        
        table.put(name, value);
    }
    public Object Get(String name)
    {
        // You have to re-implement this to make your SemanticChecker works correct
       
        if(table.containsKey(name))
        {
            return table.get(name);
        }
        

            if(previous!=null){
                return previous.Get(name);
            }
        
        return null;
        
    }
}
