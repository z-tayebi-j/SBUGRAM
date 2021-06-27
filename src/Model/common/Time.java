package Model.common;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Time {
  private static final DateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
  private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");


  public static String getTime(){
    LocalDateTime now = LocalDateTime.now();
    return DATE_TIME_FORMATTER.format(now);
  }


  public static Long getMilli(){
    return Instant.now().toEpochMilli();
  }
}
