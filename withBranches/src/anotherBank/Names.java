package anotherBank;

import java.util.Random;

/**
 * Created by krikun on 24.07.2015.
 */
public class Names {
    private static String[] firstNames = {"Ulrike", "Raymond", "Andria", "Euna", "Marvin", "Debrah", "Mirian", "Jamaal", "Crystal", "Irene", "Quinton", "Wilton", "Jeanice", "Ira", "Deadra", "Layla", "Timothy", "Blondell", "Kali", "Adrianna", "Mollie", "Neil", "Aline", "Santos", "Shameka", "Marcene", "Homer", "Pete", "Eun", "Ruben"};

    private static String[] lastNames = {"Mendoza", "Horton", "Cherry", "Banks", "Osborn", "Schmitt", "Mullen", "Sweeney", "Buchanan", "Mclaughlin", "Ballard", "Winters", "Haley", "Patton", "Andersen", "Daniel", "Henry", "Chang", "Villegas", "Allison", "Mckinney", "Ramirez", "Cline", "Harmon", "Salazar", "Vasquez", "Haas", "Rocha", "Moss", "Kidd"};

    public static String getRandomFirstName() {
        Random random = new Random();
        return firstNames[random.nextInt(firstNames.length)];
    }

    public static String getRandomLastName() {
        Random random = new Random();
        return lastNames[random.nextInt(lastNames.length)];
    }
}
