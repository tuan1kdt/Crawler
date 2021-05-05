import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Demo{
    private static String getContentFrom(String link) throws IOException {
        URL url = new URL(link);
        Scanner scanner = new Scanner(new InputStreamReader(url.openStream()));
        scanner.useDelimiter("\\\\Z");
        String content = scanner.next();
        scanner.close();
        content = content.replaceAll("\\\\R", "");
        return content;
    }
    public static void main(String[] args) throws IOException {
        String content = getContentFrom(args[0]);
        // Regex
        String url="", title="", date="", author="";
        Pattern p = Pattern.compile("<h1 class=\"title\">(.*?)</h1>");
        Matcher m = p.matcher(content);
        while (m.find()) {
            title = m.group(1);
        }
        p = Pattern.compile("<span class=\"meta\">(.*?)<a title=");
        m = p.matcher(content);
        while (m.find()) {
            date = m.group(1);
        }
        p = Pattern.compile("<em><strong>(.*?)</strong>");
        m = p.matcher(content);
        while (m.find()) {
            author = m.group(1);
        }
        System.out.println(url+ "  " + title +"  " +author+" "+ date);

    }
}