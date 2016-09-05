package songm.account.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {
	public static Date parseDate(String param, String f) {
		if ((param == null) || (param.trim().equals(""))) {
			return null;
		}
		DateFormat format = new SimpleDateFormat(f);
		Date date = null;
		try {
			date = format.parse(param);
		} catch (ParseException localParseException) {
		}
		return date;
	}

	public static Date parseDate(String param) {
		return parseDate(param, "yyyy-MM-dd HH:mm:ss");
	}

	public static String getTimeStr(Date date) {
		return getTimeStr(date, "yyyy-MM-dd HH:mm:ss");
	}

	public static String getTimeStr(Date date, String f) {
		if (date == null) {
			return null;
		}
		DateFormat format = new SimpleDateFormat(f);
		return format.format(date);
	}
	
	public static void main(String[] args) {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.YEAR, 1982);
		c.set(Calendar.MONTH, 8);
		c.set(Calendar.DAY_OF_MONTH, 20);
		c.add(Calendar.DAY_OF_MONTH, -100);
		Date d = c.getTime();
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		System.out.println(format.format(d));
	}
}