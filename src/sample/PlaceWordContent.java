package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class PlaceWordContent
{

    private AnchorPane content;
    public AnchorPane getPlaceWordContent() throws IOException
    {
        try
        {
            FXMLLoader fxmlLoader = new FXMLLoader( getClass().getResource( "placeWordContent.fxml" ) );

            content = fxmlLoader.load();
        }
        catch (IOException e)
        {
            System.out.println(" please check FxmlLoader.");
        }

        return content;

    }
}
