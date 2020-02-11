import java.util.Scanner;
import java.util.Arrays;
/**
 * Write a description of class Peaks here.
 *
 * @author Jordan Breen
 * @version 02/09/2020
 */
public class Peaks
{
    public static boolean isPeak(int pos, int heights[], int size)
    {
        if((pos >= 1) && (pos < size -1)) // If the pos ins't the first of last element in heights
        {
            if((heights[pos] >= heights[pos - 1]) && (heights[pos] >= heights[pos + 1]))
            return true;
            else
            return false;
        }
        else if(pos == 0)
        {
            if(heights[pos] >= heights[pos + 1])
            return true;
            else
            return false;
        }
        else if(pos == size - 1)
        {
            if(heights[pos] >= heights[pos - 1])
            return true;
            else
            return false;
        }
        else
        {
            System.out.println
            ("Error: Parameter pos in function call "       +
            "\"isPeak(int pos, int heights[], int size)\" " +
            "is outside of index range of heights[]");
            return false;
        }
    }
    
    public static void main(String[] args)
    {
        // Scanner Object: //
        Scanner scnr = new Scanner(System.in);
        
        // Gloabal Variables and Arrays: //
        final int MAX_HEIGHTS = 50;
        int[] heights;
        int size;
        
        System.out.println("Please enter up to 50 integer values: ");
        
        String inHeightStr = scnr.nextLine(); 
        String[] inHeightStrArr = inHeightStr.split(" ");
        size = inHeightStrArr.length;
        heights = new int[size];
        
        for(int i = 0; i < size; i++)
        {
            heights[i] = Integer.parseInt(inHeightStrArr[i]);
        }
        
        System.out.print("Peak Values: ");
        for(int i = 0; i < size; i++)
        {
            if(isPeak(i, heights, size))
            {
                System.out.print(heights[i] + " ");
            }
        }
    }
    
}
