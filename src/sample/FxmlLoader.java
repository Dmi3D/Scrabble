package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

// class loading files and their content based on fxml file
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







}
