package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;

import java.net.URL;

public class FxmlLoader
{
    private VBox content;

    public VBox getPage(String fileName)
    {
        try
        {
            URL fileUrl = MainFXMLController.class.getResource( "/sample/" + fileName + ".fxml" );
            if ( fileUrl == null )
            {
                throw new java.io.FileNotFoundException( "FXML file cannot be found" );
            }

            content = FXMLLoader.load( fileUrl );

        }
        catch(Exception e)
        {
            System.out.println("No page " + fileName + " please check FxmlLoader.");
        }
        return content;
    }
}
