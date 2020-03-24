package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

/**
 * Class that loads contents of fxml files for fxml switching
 * These files are being rendered within the boardGraphic.fxml
 * On the BOTTOM of child of main BorderPane, in an AnchorPane
 */
public class FxmlLoader
{
    private AnchorPane content;

    public AnchorPane getChallengeContent() throws IOException
    {
        try
        {
            FXMLLoader fxmlLoader = new FXMLLoader( getClass().getResource( "challengeContent.fxml" ) );

            content = fxmlLoader.load();
        }
        catch (IOException e)
        {
            System.out.println(" please check FxmlLoader.");
        }

        return content;
    }

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

    public AnchorPane getHelpContent() throws IOException
    {
        try
        {
            FXMLLoader fxmlLoader = new FXMLLoader( getClass().getResource( "helpContent.fxml" ) );

            content = fxmlLoader.load();
        }
        catch (IOException e)
        {
            System.out.println(" please check FxmlLoader.");
        }

        return content;
    }

    public AnchorPane getQuitContent() throws IOException
    {
        try
        {
            FXMLLoader fxmlLoader = new FXMLLoader( getClass().getResource( "quitContent.fxml" ) );

            content = fxmlLoader.load();
        }
        catch (IOException e)
        {
            System.out.println(" please check FxmlLoader.");
        }

        return content;
    }
}
