import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

public class DateTime {
	public static String getDate() {

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		LocalDateTime now = LocalDateTime.now();
		return dtf.format(now);

	}

	public static String getTime() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		return dtf.format(now);
	}

//	public static void main(String[] args) {
//		System.out.println(getDate());
//		System.out.println(getTime());
//	}
}
