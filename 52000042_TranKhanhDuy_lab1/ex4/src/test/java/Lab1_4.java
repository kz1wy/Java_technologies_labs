import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.net.MalformedURLException;

public class Lab1_4 {

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Please specify an URL to a file");
            return;
        }

        String urlString = args[0];
        if (!isValid(urlString)) {
            System.out.println("This is not a valid URL");
            return;
        }
        try {
            URL url = new URL(urlString);
            String[] splitUrl = urlString.split("/");
            String fileName = splitUrl[splitUrl.length - 1];

            ReadableByteChannel rbc = Channels.newChannel(url.openStream());
            FileOutputStream fos = new FileOutputStream(fileName);
            fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);

            System.out.println("OK");
        } catch (IOException e) {
            System.out.println("Something went wrong!" + e.getMessage());
        }
    }

    public static boolean isValid(String urlString) {
        try {
            URL url = new URL(urlString);
            return true;
        } catch (MalformedURLException e) {
            return false;
        }
    }
}

