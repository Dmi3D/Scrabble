package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class ExchangeContent
{

    private AnchorPane content;
    public AnchorPane getExchangeContent() throws IOException
    {
        try
        {
            FXMLLoader fxmlLoader = new FXMLLoader( getClass().getResource( "exchangeContent.fxml" ) );

            content = fxmlLoader.load();
        }
        catch (IOException e)
        {
            System.out.println(" please check FxmlLoader.");
        }

        return content;

    }
}
