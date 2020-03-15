package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class ChoiceButtonsContent
{
    private VBox content;
    public VBox getChoiceButtonsContent() throws IOException
    {
        try
        {
            FXMLLoader fxmlLoader = new FXMLLoader( getClass().getResource( "ChoiceButtonsContent.fxml" ) );

            content = fxmlLoader.load();
        } catch (IOException e)
        {
            System.out.println( " please check FxmlLoader." );
        }

        return content;
    }
}
