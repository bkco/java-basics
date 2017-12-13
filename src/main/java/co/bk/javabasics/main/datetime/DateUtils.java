package co.bk.javabasics.main.datetime;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * Utility class that makes it easier to create, modify and manage dates.
 * 
 * @author Brian Kelly
 * @see http://joda-time.sourceforge.net/quickstart.html
 */
public final class DateUtils {
	
	
	/**
	 * Get current datetime.
	 * <p>
	 * Date format returned mirrors precision stored by MySQL database.
	 * 
	 * @return java.util.Date
	 */
	public static Date getDatetimeNow() {
		DateTime dt = new DateTime(); //Represents system clock time (Joda date). Has an implicit timezone.
		return dt.toDate();
	}
	
	/**
	 * Get current datetime. 
	 * <p>
	 * Date format returned mirrors precision stored by MySQL datetime field... namely yyyy-MM-dd HH:mm:ss
	 * 
	 * @return date represented in format yyyy-MM-dd HH:mm:ss
	 */
	public static String getDatetimeNowString() {
		return getSystemDateAsString("yyyy-MM-dd HH:mm:ss");
	}	
	
	/**
	 * Method gets the system date (current date on the machine) represented in the specified dateFormat style.
	 * <p>
	 * Any <CODE>org.joda.time.format.DateTimeFormat</CODE> compatible date format is accepted.
	 * <p>
	 * For example: yyyy-MM-dd HH:mm:ss OR yyyy-MM-dd'T'HH:mm:ssZ
	 * <p>
	 * EPIC standards require the use of the format yyyy-MM-dd HH:mm:ss
	 * @param dateFormat
	 * @return
	 */
    public static String getSystemDateAsString(String dateFormat) {
    	
    	String today = null;
    	try {
    		/*
    		 * Represents system clock time (Joda date). Has an implicit timezone.
    		 */
    		DateTime dt = new DateTime();
    		
    		//Convert to JDK date
    		//Date jdkDate = dt.toDate();
    		today = formatDateTime(dateFormat, dt);
        	
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	return today;
    }
	
    /** 
     * Transform Calendar to ISO 8601 strings of the format:
     * "2008-03-01T13:00:00+01:00". It also supports parsing the "Z" timezone. 
     * @return ISO formatted date e.g &quot;2008-03-01T13:00:00+01:00&quot;
     */
    public static String fromCalendarToISO8601(final Calendar calendar) {
        Date date = calendar.getTime();
        String formatted = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
            .format(date);
        return formatted.substring(0, 22) + ":" + formatted.substring(22);
    }

    /** 
     * Get current date and time formatted as ISO 8601 string.
     *  
     * @return ISO formatted date e.g &quot;2008-03-01T13:00:00+01:00&quot;
     */
    public static String now() {
        return fromCalendarToISO8601(GregorianCalendar.getInstance());
    }

    /** 
     * Get Calendar object from an ISO date.
     * <p>
     * An ISO date is a date of the form &quot;yyyy-MM-dd'T'HH:mm:ssz&quot;
     * <p>
     * Get a date from a Calendar by:
     * DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
     * date = dateFormat.format(cal.getTime());
     * @param ISO date represented as string &quot;2008-03-01T13:00:00Z&quot;
     */
    public static Calendar fromISO8601ToCalendar(final String iso8601string)
            throws ParseException {
        Calendar calendar = GregorianCalendar.getInstance();
        String s = iso8601string.replace("Z", "+00:00");
        try {
            s = s.substring(0, 22) + s.substring(23);
        } catch (IndexOutOfBoundsException e) {
            throw new ParseException("Invalid length", 0);
        }
        Date date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").parse(s);
        calendar.setTime(date);
        return calendar;
    }
    
	/**
	 * Method formats a JODA date with specified date/time pattern.
	 * 
	 * @param dateFormat org.joda.time.format.DateTimeFormat pattern
	 * @param dateTime
	 * @return date represented as a string
	 */
	public static String formatDateTime(String dateFormat, DateTime dateTime) {
		 DateTimeFormatter fmt = DateTimeFormat.forPattern(dateFormat);
		 String str = fmt.print(dateTime);
		 return str;
	}

	

	
	/**
	 * Method converts a JDK java.util.Date from one timezone to another timezone.
	 * <p>
	 * For example:
	 * convertDateReturningADate(new Date(), DateTimeZone.UTC, DateTimeZone.forID("Europe/Berlin"));
	 * 
	 * @param sourceDate
	 * @param sourceTimeZone
	 * @param resultTimeZone
	 * @return java.util.Date
	 */
	public static Date convertDateReturningADate(Date sourceDate, DateTimeZone sourceTimeZone, DateTimeZone targetTimeZone) {
		
	    LocalDateTime localDateTime = new LocalDateTime(sourceDate.getTime());
	    DateTime sourceDateTime = localDateTime.toDateTime(sourceTimeZone);
	    DateTime resultDateTime = sourceDateTime.withZone(targetTimeZone);    
	    return resultDateTime.toLocalDateTime().toDateTime().toDate();
	}

	
	/**
	 * Method takes a JDK date and can adjust it to a specific timezone returning the 
	 * date in a String format (which the user has the ability to define).
	 * <p>
	 * For example:
	 * convertDate(new Date(), DateTimeZone.UTC, DateTimeZone.forID("Europe/Berlin"), "yyyy-MM-dd HH:mm:ss");
	 * 
	 * @param sourceDate
	 * @param sourceTimeZone
	 * @param resultTimeZone
	 * @param dateFormat for example &quot;yyyy-MM-dd HH:mm:ss&quot;
	 * @return string representation of the date.
	 */
	public static String convertDateReturningAString(Date sourceDate, DateTimeZone sourceTimeZone, DateTimeZone resultTimeZone, String dateFormat) {
		
		Date jdkDateWithTimezoneAdjusted = convertDateReturningADate(sourceDate, sourceTimeZone, resultTimeZone);
		String convertedDate = DateUtils.formatDateTime(dateFormat, new DateTime(jdkDateWithTimezoneAdjusted));
	    return convertedDate;
	}

	/**
	 *
	 * @param target e.g 06.10.2017
	 * @param targetDatePattern dd.MM.yyyy
	 * @param resultDatePattern yyyy-MM-dd
	 * @return 2017-10-06
	 */
	public static String convertDate(String target, String targetDatePattern, String resultDatePattern) {

		String result = null;
		try {
			DateFormat dfin = new SimpleDateFormat("dd.MM.yyyy", Locale.ENGLISH); // dd.MM.yyyy
			DateFormat dfout= new SimpleDateFormat("yyyy-MM-dd"); // yyyy-MM-dd

			Date date =  dfin.parse(target);
			result = dfout.format( date );
		} catch (java.text.ParseException pe) {
			pe.printStackTrace();
		}
		return result;

	}
	
	/**
	 * Method gets a JDK java.util.Date object
	 * <p>
	 * Uses JodaUtil <CODE>DateTimeFormat</CODE> object and all dateFormat patterns provided should be compatible with that object.
	 * 
	 * @param dateRepresentedAsString For example: 2013-07-16 17:17:17
	 * @param dateFormat For example: yyyy-MM-dd HH:mm:ss
	 * @return java.util.Date
	 */
	public static Date getDateObject(String dateRepresentedAsString, String dateFormat) {
		
		DateTimeFormatter formatter = DateTimeFormat.forPattern(dateFormat);
		DateTime dt = DateTime.parse(dateRepresentedAsString, formatter);
		return dt.toDate();
	}

    /**
     * Checks for a minimum age
     * @param minAge
     * @param birthDate
     * @return if birthDate is at least minAge
     */
    public static boolean isAgeAtLeast(int minAge, Date birthDate) {
        Calendar now = Calendar.getInstance();
        Calendar dob = Calendar.getInstance();
        dob.setTime(birthDate);
        if (dob.after(now)) {
            return false;
        }
        int year1 = now.get(Calendar.YEAR);
        int year2 = dob.get(Calendar.YEAR);
        int age = year1 - year2;
        int month1 = now.get(Calendar.MONTH);
        int month2 = dob.get(Calendar.MONTH);
        if (month2 > month1) {
            age--;
        } else if (month1 == month2) {
            int day1 = now.get(Calendar.DAY_OF_MONTH);
            int day2 = dob.get(Calendar.DAY_OF_MONTH);
            if (day2 > day1) {
                age--;
            }
        }
        return age >= minAge;
    }

}