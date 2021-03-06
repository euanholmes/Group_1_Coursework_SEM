package com.napier.sem;

import java.sql.*;

/**
 * Main app class that contains the connect() and disconnect() function for our database. Also contains
 * our calls to SQL.java to do our report functions.
 * @author Euan Holmes,
 * @author Adam Riddell,
 * @author Scott Darroch
 * Date Last modified 23/3/2021
 * Last modified by: Euan
 */
public class App
{
    //Connection to the SQL Database
    private static Connection con;

    public static void main(String[] args)
    {
        // Create new Application
        App a = new App();

        // Connect to database
        a.connect();

        //Creates a new instance of the SQL reports
        SQL sql = new SQL(con);

        //Report #1: all countries in the world in order of largest to smallest population.
        sql.report1();

        //Report #2: A list of country populations in Africa
        sql.report2();

        //Report #3: Population of the countries in the Caribbean in order of largest to smallest.
        sql.report3();

        /*
         * Leave room for 4-6
         */

        //Report #7: All the cities in the world organised by largest population to smallest.
        sql.report7();

        //Report #8: Population of each city in a continent (Asia) ordered from largest population to smallest
        sql.report8();

        //report #9: Population of each city in a region (The Caribbean) ordered from largest population to smallest.
        sql.report9();

        //Report #10: Population of each city in Scotland ordered from largest population to smallest.
        sql.report10();

        //Report #11: population of each city in (District) Noord_Brabant
        sql.report11();

        /*
         * Leave room for 12 - 16
         */

        //Report #17: All the capital cities in the world organised by largest population to smallest.
        sql.report17();

        //Report #18: All the capital cities in a continent organised by largest population to smallest.
        sql.report18();

        //Report #19: All the capital cities in a region organised by largest to smallest.
        sql.report19();

        /*
         * Leave room for 20 - 22
         */

        //Report #23: population of a continent living in cities and not living in cities.
        sql.report23();

        //Report #24: Population of a region living in cities and not living in cities.
        sql.report24();

        //Report #25: Population of each country living in cities and not living in cities.
        sql.report25();

        //Report #26: The total population of the world.
        sql.report26();

        //Report #27: The total population of Europe.
        sql.report27();

        //Report #28: The population of the Caribbean.
        sql.report28();

        //Report #29: The population of the Spain.
        sql.report29();

        //Report #30: The population of New South Wales.
        sql.report30();

        //Report #31: The population of a single city (Edinburgh)
        sql.report31();

        //Report #32 :Percentage share of each language compared to world population
        sql.report32();

        System.out.println("End of Reports.");

        // Disconnect from database
        a.disconnect();
    }

    /**
     * Class that connects to the database.
     * @author Euan Holmes,
     * @author Adam Riddell,
     * @author Scott Darroch
     * Date Last modified 22/3/2021
     * Last modified by: Euan
     */
    public void connect()
    {
        try
        {
            // Load Database driver
            Class.forName("com.mysql.cj.jdbc.Driver");
        }
        catch (ClassNotFoundException e)
        {
            System.out.println("Could not load SQL driver");
            System.exit(-1);
        }

        int retries = 10;
        for (int i = 0; i < retries; ++i)
        {
            System.out.println("Connecting to database...");
            try
            {
                // Wait a bit for db to start
                Thread.sleep(30000);
                // Connect to database
                con = DriverManager.getConnection("jdbc:mysql://localhost:33060/world?allowPublicKeyRetrieval=true&useSSL=false", "root", "example");
                System.out.println("Successfully connected");
                break;
            }
            catch (SQLException sqle)
            {
                System.out.println("Failed to connect to database attempt " + i);
                System.out.println(sqle.getMessage());
            }
            catch (InterruptedException ie)
            {
                System.out.println("Thread interrupted? Should not happen.");
            }
        }
    }

    /**
     * Function that disconnects from SQL database.
     * @author Euan Holmes,
     * @author Adam Riddell,
     * @author Scott Darroch
     * Date Last modified 20/2/2021
     * Last modified by: Scott
     */
    public void disconnect()
    {
        if (con != null)
        {
            try
            {
                // Close connection
                con.close();
            }
            catch (Exception e)
            {
                System.out.println("Error closing connection to database");
            }
        }
    }

    //Establish connection.
    public Connection getCon() {
        return con;
    }

}