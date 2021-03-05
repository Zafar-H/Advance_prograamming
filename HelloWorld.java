public class HelloWorld
{

    public static void main(String[] args)
    {
        //Accessing Query Specific Environment Variable
        for (String Parameter : args)
        {
            String value= System.getenv(Parameter);
            System.out.println("The Parameter " + Parameter + "  = [" + value + "]");
        }

    }

}
