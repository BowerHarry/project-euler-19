import java.util.ArrayList;
// The object date will have 3 properties: day; month; year.
class Date {
    int day;
    int month;
    int year;

    // Default constructor.
    public Date(int x, int y, int z) {
        day = x;
        month = y;
        year = z;
    }

    // Functions to get the day / month / year.
    int getDay(){ return day; }
    int getMonth(){ return month; }
    int getYear(){ return year; }
}

// This program is currently set up to calculate how many Tuesdays fell on the first of the month during the 20th century.
public class main {

    // Create an ArrayList for every day of the week, these will be populated with every date that falls on that day.
    private static ArrayList<Date> Monday = new ArrayList<>();
    private static ArrayList<Date> Tuesday = new ArrayList<>();
    private static ArrayList<Date> Wednesday = new ArrayList<>();
    private static ArrayList<Date> Thursday = new ArrayList<>();
    private static ArrayList<Date> Friday = new ArrayList<>();
    private static ArrayList<Date> Saturday = new ArrayList<>();
    private static ArrayList<Date> Sunday = new ArrayList<>();
    // Create an ArrayList containing every day of the week, this allows us to iterate through the days.
    private static ArrayList<ArrayList<Date>> Week = new ArrayList<>();

    // This function will populate the days of the week with every date in a given month of a given year.
    public static ArrayList<Date> populateMonth(ArrayList<Date> day, int month, int year){
        // Represents the number of days in a month.
        int m;
        // 30 days has September, April, June and November.
        if (month == 4 || month == 6 || month == 9 || month == 11) {
            m = 30;
        }
        // If the month is February...
        else if (month == 2){
            // Leap years only occur on a century if the year is divisible by 400.
            if (year % 1000 == 0){
                if (year % 400 == 0){
                    m = 29;
                }
                else {
                    m = 28;
                }
            }
            // Otherwise, leap years occur every 4 years.
            else if (year % 4 == 0){
                m = 29;
            }
            else {
                m = 28;
            }
        }
        // All the rest have 31
        else {
            m = 31;
        }
        // Find the first day of the month.
        for (int i = 0; i < 7; i++) {
            if (Week.get(i).equals(day)){
                // Iterate through every day of the month.
                int j = 1;
                while (j < m + 1){
                    // Add the date to the day it fell on.
                    Date date = new Date (j++, month, year);
                    i++;
                    if (i == 7){
                        i = 0;
                    }
                    Week.get(i).add(date);

                }
                // Return the day of the week that the next month starts on.
                return Week.get(i);
            }
        }
        return Week.get(0);
    }

    // This function will populate the days of the week with every date in a given year.
    public static ArrayList<Date> populateYear(ArrayList<Date> day, int year){
        // For every month in the year...
        for (int j=1; j<13; j++){
            // Find the day of the week that the month starts on.
            for (int i=0; i<7; i++) {
                if (Week.get(i).equals(day)) {
                    // Populate the month and store the day the next month starts on.
                    day = populateMonth(day, j, year);
                    break;
                }
            }
        }
        return day;

    }

    public static void main(String[] args) {
        // Add the days of the week to the ArrayList.
        Week.add(Monday);
        Week.add(Tuesday);
        Week.add(Wednesday);
        Week.add(Thursday);
        Week.add(Friday);
        Week.add(Saturday);
        Week.add(Sunday);
        // Starting day (eg. 1 Jan 1901 was a Tuesday)
        ArrayList<Date> day = Tuesday;
        // Populate every day between the start of 1901 and the start of 2001.
        int startYear = 1901;
        int endYear = 2001;
        for (int i = startYear; i<endYear; i++){
            day = populateYear(day, i);
        }

        // Calculate number of dates in Tuesday that have the day as the 1st of the month.
        ArrayList<Date> Day = Tuesday;
        int count = 0;
        // Loop through every date in Tuesday.
        for (int i = 0; i < Day.size(); i++){
            Date date = Day.get(i);
            // If the day is the 1st of the month...
            if (date.getDay() == 1){
                // Add 1 to count.
                count++;
            }
        }
        System.out.println(count);


    }

}
