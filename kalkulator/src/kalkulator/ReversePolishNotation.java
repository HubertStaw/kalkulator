package kalkulator;

import org.apache.commons.lang3.math.NumberUtils;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 *Klasa do obs³ugi Odwrotnej Notacji Polskiej
 */
public class ReversePolishNotation 
{
	
    private String infiks;
    private String postfiks;
    private Double result;
    private Boolean check = true;

    
    /**
     * Konstruktor
     * @param entry dzia³anie do obliczenia
     */
    public ReversePolishNotation(String entry)
    {
        infiks = entry;
        postfiks = "";
        result = 0.;
        infiksToPostfiks();
        if (check)
        {
        	result = count();
        }
    }
    
    /**
     * @return wyra¿enie postfiksowe
     */
    public String getPostfiks()
    {
    	return postfiks;
    }
    
    /**
     * @return wyra¿enie infiksowe
     */
    public String getInfiks()
    {
    	return infiks;
    }
    
    /**
     * @return wynik dzia³ania
     */
    public Double getResult()
    {
    	return result;
    }
    
    /**
     * zamienia wyra¿enie infiksowe na postfiksowe
     */
    private void infiksToPostfiks()
    {
        Stack<String> stack = new Stack<String>();
        StringTokenizer stringTkn = new StringTokenizer(infiks, "+-*/()", true);
        
        while(stringTkn.hasMoreTokens() && check)
        {
            String element = stringTkn.nextToken();
            if(element.equals("+") || element.equals("*") || element.equals("-") || element.equals("/"))
            {
                while(!stack.empty() && priority(stack.peek()) >= priority(element))
                {
                    postfiks += stack.pop() + " ";
                }
                stack.push(element);
            }
            else if(!NumberUtils.isCreatable(element))
            {
            	System.out.println("Niepoprawny ci¹g znaków.");
            	check = false;
            	break;
            }
            else if(element.equals("(")) 
            {
            	stack.push(element);
            }
            else if(element.equals(")"))
            {
            	while(!stack.peek().equals("(")) 
            	{
            		postfiks += stack.pop() + " ";
            	}
            	stack.pop();
            }
            else
            {
            	postfiks += element + " ";
            }
        }
        while(!stack.empty() && check)
        {
        	postfiks += stack.pop() + " ";
        }
    }
    
    
    /**
     * @param operator znak dzia³ania +*-/
     * @return priorytet operatora
     */
    public static int priority(String operator)
    {
        if(operator.equals("+") || operator.equals("-"))
            return 1;
        else if(operator.equals("*") || operator.equals("/"))
            return 2;
        else 
            return 0;
    }

    /**
     * Metoda oblicza na bazie wyra¿enia postfiks
     * @return wynik dzia³ania
     */
    private Double count()
    {
    	Stack<Double> stack = new Stack<Double>();
        StringTokenizer stringTkn = new StringTokenizer(postfiks," ");
        
        while(stringTkn.hasMoreTokens())
        {
        	String element = stringTkn.nextToken();
            if(!element.equals("+") && !element.equals("*") && !element.equals("-") && !element.equals("/"))
            {
            	Double value = Double.parseDouble(element);
            	stack.push(value);
            }
            else
            {
            	Double value1 = stack.pop(), value2 = stack.pop();
            	switch(element.charAt(0))
            	{
            	case '*':
            		stack.push(value2 * value1);
            		break;
            	case '/':
            		stack.push(value2 / value1);
            		break;
            	case '+':
            		stack.push(value2 + value1);
            		break;
            	case '-':
            		stack.push(value2 - value1);
            		break;
            	}
            }
        }        
    	return stack.pop();
    }
}