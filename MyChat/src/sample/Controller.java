package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class Controller {
    @FXML
    TextArea textArea;

    @FXML
    TextField textField;

    @FXML
    Button button;

    public void sendMessage(){
        if(textField.getText().length()!=0) textArea.appendText(textField.getText()+ "\n");
        textField.setText("");
    }
}
