import javax.swing.*;
import java.util.Objects;

import static java.lang.foreign.MemorySegment.NULL;

public class InteractiveRetriever
{
    public static JPanel retrieveInteractive(String searchquerry)
    {
        if (Objects.equals(searchquerry.toLowerCase(), "golf"))
        {
            Putting_Green green = new Putting_Green();

            return green.createPuttingGreenPanel();
        }
        else if (searchquerry.equalsIgnoreCase("max verstappen"))
        {
            return Max_Verstappen_Interactive.getMaxVerstappenInteractive();
        }

        return new JPanel();
    }
}
