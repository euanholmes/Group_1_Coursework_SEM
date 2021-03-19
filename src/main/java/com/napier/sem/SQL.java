package com.napier.sem;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class SQL {
    Connection con;

    public SQL(Connection con) {
        this.con = con;
    }

    /**
     * Gets all countries in the world in order of largest to smallest population..
     */
    public void report1() {
        try
        {
            System.out.println("Report 1: A report showing all countries in the world in order of largest to smallest population.");
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT country.code, country.name, country.continent, country.region, country.population, city.name "
                            + "FROM country, city "
                            + "WHERE country.capital = city.ID "
                            + "ORDER BY country.population DESC ";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Extract country information
            while (rset.next())
            {
                Country coun = new Country();
                coun.code = rset.getString("country.code");
                coun.name = rset.getString("country.name");
                coun.continent = rset.getString("country.continent");
                coun.region = rset.getString("country.region");
                coun.population = rset.getInt("country.population");
                coun.capital = rset.getString("city.name");

                System.out.println(coun);
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get population details");
        }
    }

    /**
     * Gets A list of country populations in Africa.
     */
    public void report2(){
        try
        {
            System.out.println("Report #2, A list of country populations in Africa");
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT country.code, country.name, country.continent, country.region, country.population, city.name "
                            + "FROM country, city "
                            + "WHERE country.capital = city.ID AND country.continent = 'Africa' "
                            + "ORDER BY country.population DESC ";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Extract country information
            while (rset.next())
            {
                Country coun = new Country();
                coun.code = rset.getString("country.code");
                coun.name = rset.getString("country.name");
                coun.continent = rset.getString("country.continent");
                coun.region = rset.getString("country.region");
                coun.population = rset.getInt("country.population");
                coun.capital = rset.getString("city.name");
                System.out.println(coun);
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get population details");
        }
    }

    /**
     * Gets the population of the countries in the Caribbean in order of largest to smallest.
     */
    public void report3() {
        try
        {
            System.out.println("Report 3: A report showing the population of the countries in the Caribbean in order of largest to smallest.");
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT country.code, country.name, country.continent, country.region, country.population, city.name "
                            + "FROM country, city "
                            + "WHERE country.capital = city.ID AND country.region = 'Caribbean' "
                            + "ORDER BY country.population DESC ";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Extract country information
            while (rset.next())
            {
                Country coun = new Country();
                coun.code = rset.getString("country.code");
                coun.name = rset.getString("country.name");
                coun.continent = rset.getString("country.continent");
                coun.region = rset.getString("country.region");
                coun.population = rset.getInt("country.population");
                coun.capital = rset.getString("city.name");
                System.out.println(coun);
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get population details");
        }
    }

    /*
        Leave Room For 4-6
     */

    /**
     * Prints the report showing all the cities in the world organised by largest population to smallest.
     */
    public void report7(){
        try
        {
            System.out.println("Report 7: All the cities in the world organised by largest population to smallest.");

            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT ID, name, CountryCode, district, population "
                            + "FROM city "
                            + "ORDER BY population DESC";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);

            while (rset.next())
            {
                City city = new City();
                city.ID = rset.getInt("ID");
                city.name = rset.getString("name");
                city.country = rset.getString("CountryCode");
                city.district = rset.getString("district");
                city.population = rset.getInt("population");

                System.out.println(city);

            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get population details");
        }
    }

    /**
     * Returns the population of each city in a continent (Asia) ordered from largest population to smallest.
     */
    public void report8(){
        try
        {
            System.out.println("Report 8: The population of each city in Asia ordered from largest population to smallest.");
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT city.ID, city.name, city.CountryCode, city.district, city.population, country.region "
                            + "FROM city "
                            + "INNER JOIN country ON city.CountryCode = country.Code "
                            + "WHERE country.continent = 'Asia' "
                            + "GROUP BY city.ID "
                            + "ORDER BY city.population DESC";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);

            // Print header
            System.out.println(String.format("%-8s %-25s %-8s %-25s %-10s", "City ID", "City Name", "Country", "District", "Population"));

            while (rset.next())
            {
                City city = new City();
                city.ID = rset.getInt("city.ID");
                city.name = rset.getString("city.name");
                city.country = rset.getString( "city.CountryCode");
                city.district = rset.getString("city.district");
                city.population = rset.getInt("city.population");
                System.out.println(city);
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get population details");
        }

    }

    /**
     * Returns the population of each city in a region (The Caribbean) ordered from largest population to smallest.
     */
    public void report9(){
        try
        {
            System.out.println("Report 9: The population of each city in The Caribbean ordered from largest population to smallest.");
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT city.ID, city.name, city.CountryCode, city.district, city.population, country.region "
                            + "FROM city "
                            + "INNER JOIN country ON city.CountryCode = country.Code "
                            + "WHERE country.region = 'Caribbean' "
                            + "GROUP BY city.ID "
                            + "ORDER BY city.population DESC";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);

            while (rset.next())
            {
                City city = new City();
                city.ID = rset.getInt("city.ID");
                city.name = rset.getString("city.name");
                city.country = rset.getString( "city.CountryCode");
                city.district = rset.getString("city.district");
                city.population = rset.getInt("city.population");
                System.out.println(city);
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get population details");
        }
    }

    /**
     * Returns the population of each city in Scotland ordered from largest population to smallest.
     */
    public void report10(){
        try
        {
            System.out.println("Report 10: The population of each city in Scotland ordered from largest population to smallest.");
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT city.ID, city.name, city.CountryCode, city.district, city.population, country.region "
                            + "FROM city "
                            + "INNER JOIN country ON city.CountryCode = country.Code "
                            + "WHERE country.name = 'France' "
                            + "GROUP BY city.ID "
                            + "ORDER BY city.population DESC";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);

            // Print header
            System.out.println(String.format("%-8s %-25s %-8s %-25s %-10s", "City ID", "City Name", "Country", "District", "Population"));

            while (rset.next())
            {
                City city = new City();
                city.ID = rset.getInt("city.ID");
                city.name = rset.getString("city.name");
                city.country = rset.getString( "city.CountryCode");
                city.district = rset.getString("city.district");
                city.population = rset.getInt("city.population");
                System.out.println(city);
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get population details");
        }

    }

    /**
     * Gets a list of the population of each city in (District) Noord_Brabant
     */
    public void report11() {
        try
        {
            System.out.println("Report 11: The population of each city in Noord_Brabant ordered from largest population to smallest.");
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT ID, name, CountryCode, district, population "
                            + "FROM city "
                            + "WHERE district = 'Noord-Brabant' "
                            + "ORDER BY population DESC ";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);

            while (rset.next())
            {
                City city = new City();
                city.ID = rset.getInt("city.ID");
                city.name = rset.getString("city.name");
                city.country = rset.getString( "city.CountryCode");
                city.district = rset.getString("city.district");
                city.population = rset.getInt("city.population");
                System.out.println(city);

            }

        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get population details");


        }
    }

    /*
        Leave Room For 12-16
     */

    /**
     * This function prints the report showing all the capital cities in the world organised by largest population to smallest.
     */
    public void report17(){
        try
        {
            System.out.println("Report 17: All the capital cities in the world organised by largest population to smallest.");

            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT city.ID, city.name, city.CountryCode, city.district, city.population "
                            + "FROM city "
                            + "INNER JOIN country ON city.CountryCode = country.Code "
                            + "WHERE city.ID = country.capital "
                            + "GROUP BY city.ID "
                            + "ORDER BY city.population DESC";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);

            while (rset.next())
            {
                City city = new City();
                city.ID = rset.getInt("city.ID");
                city.name = rset.getString("city.name");
                city.country = rset.getString("city.CountryCode");
                city.district = rset.getString("city.district");
                city.population = rset.getInt("city.population");

                System.out.println(city);

            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get population details");
        }
    }

    /**
     * Returns a list of all Capital Cities in a given Continent (Europe) ordered by population from largest to smallest
     */
    public void report18(){
        try{

            System.out.println("Report #18: All the capital cities in a continent organised by largest population to smallest");
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT city.name, city.CountryCode, country.name, city.population, country.code "
                            + "FROM city "
                            + "INNER JOIN country ON city.CountryCode = country.code "
                            + "WHERE country.continent = 'Europe' "
                            + "ORDER BY city.population DESC ";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);

            while (rset.next()) {
                CapitalCity city = new CapitalCity();
                city.name = rset.getString("city.name");
                city.country = rset.getString("country.name");
                city.population = rset.getInt("city.population");
                String CapitalCity_string =
                        String.format("%-30s %-35s %-15s",
                                city.name, city.country, city.population);
                System.out.println(CapitalCity_string);


            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get population details.");
        }
    }

    /**
     * Returns a list of all Capital Cities in a given Region (Caribbean) ordered by population from largest to smallest
     */
    public void report19(){
        try{

            System.out.println("Report #19: All the capital cities in a region organised by largest to smallest.");
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT city.name, city.CountryCode, country.name, city.population, country.code "
                            + "FROM city "
                            + "INNER JOIN country ON city.CountryCode = country.code "
                            + "WHERE country.region = 'Caribbean' "
                            + "ORDER BY city.population DESC ";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);

            while (rset.next()) {
                CapitalCity city = new CapitalCity();
                city.name = rset.getString("city.name");
                city.country = rset.getString("country.name");
                city.population = rset.getInt("city.population");
                String CapitalCity_string =
                        String.format("%-30s %-35s %-15s",
                                city.name, city.country, city.population);
                System.out.println(CapitalCity_string);


            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get population details.");
        }
    }
}
