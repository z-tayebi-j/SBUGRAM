package Model.common;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
  a util class for getting Time
  it give time in 2 formats
  1 : long : milliseconds from 1/1/1970
  2 : string: data and time in a beautiful way
**/
public class Time {
  private static final DateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
  private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

  /**
      first methid: it return a readable string containing time and date
      it formatted very beautifully
  **/
  public static String getTime(){
    LocalDateTime now = LocalDateTime.now();
    return DATE_TIME_FORMATTER.format(now);
  }

  /**
    second Method
    if give you time in milliseconds from 1910/1/1 in long integer format

  **/
  public static Long getMilli(){
    return Instant.now().toEpochMilli();
  }
}
