package Course2.Week3;
/**
 * Write a description of class LogAnalyzer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class LogAnalyzer
{
     private ArrayList<LogEntry> records;
     
     public LogAnalyzer() {
         // complete constructor
         records = new ArrayList<>();
     }
        
     public void readFile(String filename) {
         // complete method
         FileResource resource = new FileResource(filename);
         for (String line: resource.lines()){

             LogEntry entry = WebLogParser.parseEntry(line);
             records.add(entry);
         }

     }

     public ArrayList<String> getUniqueIPs(ArrayList<LogEntry> entry){
         ArrayList<String> uniqueIPs = new ArrayList<>();
         for (LogEntry le: entry){
             String ipAddr = le.getIpAddress();
             if (!uniqueIPs.contains(ipAddr)){
                 uniqueIPs.add(ipAddr);
             }
         }
         return uniqueIPs;
     }


     public int countUniqueIPs(){
         ArrayList<String> uniqueIPs = getUniqueIPs(records);
         return uniqueIPs.size();
     }



     public void printAllHigherThanNumber(int num){
         for (LogEntry le : records){
             if (le.getStatusCode() > num){
                 System.out.println(le);
             }
         }
     }

     public ArrayList<String> uniqueIPVisitsOnDay(String someday){

         ArrayList<LogEntry> uniqueVisits = new ArrayList<>();

         for (LogEntry entry: records){
             String dateAccessed = entry.getAccessTime().toString();
             if (dateAccessed.contains(someday)){
                 uniqueVisits.add(entry);
             }
         }
         return getUniqueIPs(uniqueVisits);
     }

     public HashMap<String, Integer> countVisitsPerIP(){
         HashMap<String, Integer> counts = new HashMap<>();
         for (LogEntry entry: records){

             String ipAddr = entry.getIpAddress();
             if (!counts.containsKey(ipAddr)){
                 counts.put(ipAddr,1);
             } else {
                 counts.put(ipAddr, counts.get(ipAddr)+1);
             }
         }
         return counts;
     }

     public int mostNumberVisitsByIP(HashMap<String, Integer> map){
         int currentMax=0;
         for (int mapValue : map.values()){
             if(currentMax<mapValue){
                 currentMax=mapValue;
             }
         }
         return currentMax;
     }

     public ArrayList<String> iPsMostVisits(HashMap<String, Integer> map){
         ArrayList<String> mostVisits = new ArrayList<>();
         int maxNumberOfEntries = mostNumberVisitsByIP(map);
         for (String key: map.keySet()){
             if (map.get(key) == maxNumberOfEntries) mostVisits.add(key);
         }
         return mostVisits;
     }

     public int countUniqueIPsInRange(int low, int high){

         ArrayList<LogEntry> ipsInRange = new ArrayList<>();

         for (LogEntry entry: records){
             int statusCode = entry.getStatusCode();
             if (statusCode <= high && statusCode>=low){

                 ipsInRange.add(entry);
             }
         }
         ArrayList<String> uniqueIPsInRange = getUniqueIPs(ipsInRange);
         return uniqueIPsInRange.size();
     }
     public void printAll() {
         for (LogEntry le : records) {
             System.out.println(le);
         }
     }
     
     
}
