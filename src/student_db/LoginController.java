package student_db;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author vishaak
 */
public class LoginController implements Initializable {
    @FXML
    private JFXButton btn_login,btn_close;
    @FXML
    private JFXTextField tf_user;
    @FXML
    private JFXPasswordField pf_pass;
    @FXML
    private Label error_msg;
    private double xOffset = 0;
    private double yOffset = 0;
    private String adminId,adminPass;
            
    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException,SQLException {
       if(event.getSource() == btn_login){
            adminId = tf_user.getText();
            adminPass = pf_pass.getText();
            if(adminId == null || adminPass == null )
                error_msg.setVisible(true);
            else{
                Connection db = getConnection();
                Statement st = db.createStatement();
                ResultSet result = st.executeQuery("SELECT admin_id,admin_pass FROM administrator WHERE admin_id='"+adminId+"' AND admin_pass='"+adminPass+"'");
                
                if(result.next()){
                    db.close();
                    Parent mainPage = FXMLLoader.load(getClass().getResource("Main.fxml"));
                    Scene mainScene = new Scene(mainPage);
                    Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    
                    //Screen Movement
                    mainPage.setOnMousePressed(new EventHandler<MouseEvent>(){
                        @Override
                        public void handle(MouseEvent event){
                            xOffset = event.getSceneX();
                            yOffset = event.getSceneY();
                        }

                    });
                    mainPage.setOnMouseDragged(new EventHandler<MouseEvent>(){
                        @Override
                        public void handle(MouseEvent event){
                            appStage.setX(event.getScreenX() - xOffset);
                            appStage.setY(event.getScreenY() - yOffset);
                        }
                    });
                 
                    mainScene.setFill(Color.TRANSPARENT);
                    //appStage.initStyle(StageStyle.TRANSPARENT);
                    appStage.setScene(mainScene);
                    appStage.show();
                }
                else{
                    tf_user.clear();
                    pf_pass.clear();
                    error_msg.setVisible(true);
                }
            }
        
            
        }
        else if(event.getSource() == btn_close){
            System.exit(0);
        }
            
    } 
    
    private Connection getConnection(){
        Connection con;
        try{
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/student_dbms", "root", "");
            return con;
        }catch(Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Connection Error");
            alert.setContentText(e.getMessage());
            TextArea area = new TextArea(e.toString());
            alert.getDialogPane().setExpandableContent(area);
            alert.showAndWait();
            return null;
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }    
    
}
