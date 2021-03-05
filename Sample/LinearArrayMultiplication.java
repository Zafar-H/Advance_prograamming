public class LinearArrayMultiplication
{

    public static void main(String args[])
    {
        int array1[] = {1,2,3};
        int array2[] = {4,5,6};

        //this is the start position of the array
        int startCount = 0;
        int endCount   = 3;

        //initializing the initial value
        int InitialProductValue = 0;
        System.out.println("Initialisation START...");

        System.out.println("Default Value for Product Array = [" + InitialProductValue + "]");
        System.out.println("Initial Position of Array = [" + startCount + "]");
        System.out.println("End Position of Array = [" + endCount + "]");

        System.out.println("Initialisation END.....");

        //checking if the start and end are logically correct
        if(startCount >= endCount)
        {

            System.out.println("Initial Position ->[" + startCount + "] is greater then or equal to the end position ->[" + endCount +"]");
            System.exit(-3);
        }


        //The length is computed dynamically
        int ArrayElementCount_1 = array1.length;
        int ArrayElementCount_2 = array2.length;

        System.out.println("ElementCount Array 1 = [" + ArrayElementCount_1 + "]");
        System.out.println("ElementCount Array 2 = [" + ArrayElementCount_2 + "]");


        //checking if both the arrays are of same length otherwise exit
        if(ArrayElementCount_1 != ArrayElementCount_2)
        {
            System.out.println("The array lengths are different and hence exiting");
            System.exit(-1);
        }

        //checking if the initial positon of the array is within the array size
        if(startCount > ArrayElementCount_1)
        {
            System.out.println("Array initial position ->[" + startCount + "] is greater than or equal the size of the array ->[" + ArrayElementCount_1);
            System.exit(-300);
        }

        //checking if the end positon of the array is within the array size
        if(endCount > ArrayElementCount_1)
        {
            System.out.println("Array end position ->[" + endCount + "] is greater than or equal the size of the array ->[" + ArrayElementCount_1);
            System.exit(-301);
        }


        //this holds the product of 2 arrays
        int ArrayProduct[] = new int[ArrayElementCount_1];


        //Looping constuct for finding the product of elements in the array
        //ASSUMPTION: both the arrays lengths should e same
        for(int indexOfArray = startCount; indexOfArray < endCount; indexOfArray++ )
        {
            //initialising the product array
            ArrayProduct[indexOfArray] = InitialProductValue;
            int ElementOfArray1 = array1[indexOfArray];
            int ElementOfArray2 = array2[indexOfArray];

            //displaying the value of array at the current position
            System.out.println("Element value of array 1 in the position[" + indexOfArray + "] = " + ElementOfArray1);
            System.out.println("Element value of array 2 in the position[" + indexOfArray + "] = " + ElementOfArray2);

            //computing products
            int productOfElements = ElementOfArray1 * ElementOfArray2;
            ArrayProduct[indexOfArray] = productOfElements;

            System.out.println("Product value at position [" + indexOfArray + "] = " + ArrayProduct[indexOfArray]);
        }

    }
}
