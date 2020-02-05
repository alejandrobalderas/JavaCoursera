package Course2.Week3;
/**
 * Write a description of class Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class Tester
{
    public void testLogEntry() {
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }
    
    public void testLogAnalyzer() {
        // complete method
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("./src/Course2/Week3/weblog2_log");
        HashMap<String, Integer> counts = la.countVisitsPerIP();
        HashMap<String, ArrayList<String>> ips = la.iPsForDays();



        System.out.println(la.countUniqueIPs());
        ArrayList<String> uniqueIPs = la.uniqueIPVisitsOnDay("Sep 24");
        System.out.println(uniqueIPs.size());

        int countIps = la.countUniqueIPsInRange(400, 499);
        System.out.println(countIps);
        int i = la.mostNumberVisitsByIP(counts);
        ArrayList<String> strings = la.iPsMostVisits(counts);

        System.out.println(i);
        System.out.println(strings);

        String s = la.dayWithMostIPVisits();
        System.out.println(s);

        ArrayList<String> strings1 = la.iPsWithMostVisitsOnDay("Sep-29");
        System.out.println(strings1);

    }
}
