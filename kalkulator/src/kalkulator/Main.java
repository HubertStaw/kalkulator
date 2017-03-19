package kalkulator;

import java.util.Scanner;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Kalkulator, klasa Main, odpowiadaj¹ca za interfejs
 * @author Hubert
 *
 */
public class Main 
{
	private static String FILENAME = "test.txt";
	
    public static void main(String[] args)
    {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("KALKULATOR");
        System.out.println("1 - Czytanie z klawiatury \n2 - Czytanie z pliku");
        
        switch(keyboard.nextLine())
        {
        case "1":
        	readKeyboard(keyboard);
        	break;
        case "2":
        	readFile();
        	break;
        }     
    }
    
    /**
     * Czytanie z klawiatury
     * @param keyboard przekazanie zmiennej typu Scanner
     */
    public static void readKeyboard(Scanner keyboard)
    {
        Boolean reading = true;
        while(reading)
        {
            String line = keyboard.nextLine();
         	
            if (line.endsWith("koniec"))
            {
                reading = false;
            }
            else
            {
            	ReversePolishNotation rpn = new ReversePolishNotation(line);
            	System.out.println(rpn.getResult());
            }
        }
        System.out.println("Koniec zabawy");
    }
    
    
    /**
     * Czytanie z pliku
     */
    public static void readFile()
    {
		BufferedReader br = null;
		FileReader fr = null;
		try {

			fr = new FileReader(FILENAME);
			br = new BufferedReader(fr);

			String sCurrentLine;

			br = new BufferedReader(new FileReader(FILENAME));

			while ((sCurrentLine = br.readLine()) != null) {
				System.out.println("Twoje dzia³anie: " + sCurrentLine);
				ReversePolishNotation rpn = new ReversePolishNotation(sCurrentLine);
				System.out.println("Jego wynik: " + rpn.getResult());
			}

		} catch (IOException e) {

			e.printStackTrace();

		} finally {

			try {

				if (br != null)
					br.close();

				if (fr != null)
					fr.close();

			} catch (IOException ex) {

				ex.printStackTrace();

			}

		}
    }
}
