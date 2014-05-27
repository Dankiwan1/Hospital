/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hospital;

/**
 *
 * @author Dankiwan
 */


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class CalendarTest {
  public static void main(String[] args) {
    // Constructor allows to set year, month and date
    Calendar cal1 = new GregorianCalendar(2008, 01, 01);
    // Constructor could also be empty
    // Calendar cal2 = new GregorianCalendar();
    // Change the month
    cal1.set(Calendar.MONTH, Calendar.MAY);

    System.out.println("Year: " + cal1.get(Calendar.YEAR));
    System.out.println("Month: " + (cal1.get(Calendar.MONTH) + 1));
    System.out.println("Days: " + cal1.get(Calendar.DAY_OF_MONTH));

    // format the output with leading zeros for days and month
    SimpleDateFormat date_format = new SimpleDateFormat("yyyy/MM/dd");
    System.out.println(date_format.format(cal1.getTime()));

  }
} 