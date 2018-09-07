package student_db;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;

/**
 * FXML Controller class
 *
 * @author vishaak
 */
public class MainController implements Initializable {
    @FXML
    private JFXButton btn_close,btn_add_op1,btn_add_op2,btn_update_op,btn_view_op;
    @FXML
    private AnchorPane Anchor,pn_home,pn_admin,pn_insert1,pn_insert2,pn_update,pn_view,pn_upCon,pn_stUpdate,pn_deUpdate,pn_sbUpdate,pn_atUpdate,pn_exUpdate;
    
    //Admin elements
    @FXML
    private JFXTextField tf_adminid,tf_adminname,tf_adminpass;
    @FXML
    private JFXButton btn_adminInsert,btn_admin;
    
    //Insert1 elements
    @FXML
    private JFXTextField tf_insert1usn,tf_insert1name,tf_insert1add,tf_insert1con,tf_insert1scode,tf_insert1sname,tf_insert1branch,tf_insert1dno;
    @FXML
    private JFXButton btn_stInsert1,btn_sbInsert1;
    
    //Insert2 elements
    @FXML
    private JFXTextField tf_insert2dno,tf_insert2dname,tf_insert2ausn,tf_insert2ascode,tf_insert2aperc,tf_insert2eusn,tf_insert2escode,tf_insert2edno,tf_insert2t1,tf_insert2t2,tf_insert2t3,tf_insert2ext;
    @FXML
    private JFXButton btn_deInsert2,btn_atInsert2,btn_exInsert2;
    
    //View elements
    @FXML
    private JFXTextField tf_searchView;
    @FXML
    private JFXButton btn_stView,btn_deView,btn_subView,btn_atView,btn_exView;
    @FXML
    private TableView<Student> tv_view;
    @FXML
    private TableColumn<Student,String> tc_col1,tc_col2,tc_col3,tc_col4,tc_col5,tc_col6,tc_col7;
    
    //Update Elements
    @FXML
    private JFXTextField tf_updateusn,tf_updatename,tf_updateadd,tf_updatecon,tf_updatescode,tf_updatesname,tf_updatebranch,tf_updatedno;
    @FXML
    private JFXButton btn_stUpdate,btn_sbUpdate,btn_deUpdate,btn_atUpdate,btn_exUpdate,btn_stDelete,btn_sbDelete,btn_deDelete,btn_atDelete,btn_exDelete,btn_Upclose;
    @FXML
    private JFXTextField tf_updatededno,tf_updatedname,tf_updateausn,tf_updateascode,tf_updateaperc,tf_updateeusn,tf_updateescode,tf_updateedno,tf_updatet1,tf_updatet2,tf_updatet3,tf_updateext;
    @FXML
    private JFXTextField tf_searchUp;
    @FXML
    private JFXButton btn_stUp,btn_deUp,btn_sbUp,btn_atUp,btn_exUp;
    @FXML
    private TableView<Student> tv_Up;
    @FXML
    private TableColumn<Student,String> tc_col1Up,tc_col2Up,tc_col3Up,tc_col4Up,tc_col5Up,tc_col6Up,tc_col7Up;
    
    //Misc elements
    private ObservableList<Student> data;
    private int tableChoice = 0;
    private boolean searchBool = false;
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        
        //Option Panes
        pn_upCon.setVisible(false);    
        pn_admin.setVisible(false);
        
        //Search TF
        tf_searchView.setEditable(false);
        tf_searchUp.setEditable(false);
        
        //Table Columns
        tc_col1.setVisible(false);
        tc_col2.setVisible(false);
        tc_col3.setVisible(false);
        tc_col4.setVisible(false);
        tc_col5.setVisible(false);
        tc_col6.setVisible(false);
        tc_col7.setVisible(false);
        tc_col1Up.setVisible(false);
        tc_col2Up.setVisible(false);
        tc_col3Up.setVisible(false);
        tc_col4Up.setVisible(false);
        tc_col5Up.setVisible(false);
        tc_col6Up.setVisible(false);
        tc_col7Up.setVisible(false);
        
        if(event.getSource() == btn_admin){
            pn_home.toFront();
            pn_upCon.setVisible(true);
            pn_admin.setVisible(true);
            pn_deUpdate.setVisible(false);
            pn_stUpdate.setVisible(false);
            pn_sbUpdate.setVisible(false);
            pn_atUpdate.setVisible(false);
            pn_exUpdate.setVisible(false);
        }
        else if(event.getSource() == btn_adminInsert){
            adminInsert();
        }
        else if(event.getSource() == btn_add_op1){
            pn_insert1.toFront();
        }
        else if(event.getSource() == btn_add_op2){
            pn_insert2.toFront();
        }
        else if(event.getSource() == btn_update_op){
            pn_update.toFront();
        }
        else if(event.getSource() == btn_view_op){
            pn_view.toFront();
        }
        else if(event.getSource() == btn_close){
            System.exit(0);
        }
        else if(event.getSource() == btn_stInsert1){
            insertStudent1();
            //Clear Text
            tf_insert1usn.clear();
            tf_insert1name.clear();
            tf_insert1add.clear();
            tf_insert1con.clear();
        }
        else if(event.getSource() == btn_sbInsert1){
            insertSubject1();
            //Clear Text
            tf_insert1scode.clear();
            tf_insert1sname.clear();
            tf_insert1branch.clear();
            tf_insert1dno.clear();
        }
        else if(event.getSource() == btn_deInsert2){
            insertDepartment2();
            //Clear Text
            tf_insert2dno.clear();
            tf_insert2dname.clear();
        }
        else if(event.getSource() == btn_atInsert2){
            insertAttendance2();
            //Clear Text
            tf_insert2ausn.clear();
            tf_insert2ascode.clear();
            tf_insert2aperc.clear();
        }
        else if(event.getSource() == btn_exInsert2){
            insertExam2();
            //Clear Text
            tf_insert2eusn.clear();
            tf_insert2escode.clear();
            tf_insert2edno.clear();
            tf_insert2t1.clear();
            tf_insert2t2.clear();
            tf_insert2t3.clear();
            tf_insert2ext.clear();
        }
        else if(event.getSource() == btn_stView){
            viewStudent();
        }
        else if(event.getSource() == btn_deView){
            viewDepartment();
        }
        else if(event.getSource() == btn_subView){
            viewSubject();
        }
        else if(event.getSource() == btn_atView){
            viewAttendance();
        }
        else if(event.getSource() == btn_exView){
            viewExam();
        }
        else if(event.getSource() == btn_Upclose){
            pn_upCon.setVisible(false);
            //Clear Text
            tf_updateusn.clear();
            tf_updatename.clear();
            tf_updateadd.clear();
            tf_updatecon.clear();
            tf_updatescode.clear();
            tf_updatesname.clear();
            tf_updatebranch.clear();
            tf_updatedno.clear();
            tf_updatededno.clear();
            tf_updatedname.clear();
            tf_updateausn.clear();
            tf_updateascode.clear();
            tf_updateaperc.clear();
            tf_updateeusn.clear();
            tf_updateescode.clear();
            tf_updateedno.clear();
            tf_updatet1.clear();
            tf_updatet2.clear();
            tf_updatet3.clear();
            tf_updateext.clear();
        }
        else if(event.getSource() == btn_stUp){
            updateViewStudent();           
        }
        else if(event.getSource() == btn_stUpdate){
            updateStudent();
        }
        else if(event.getSource() == btn_deUp){
            updateViewDepartment();            
        }
        else if(event.getSource() == btn_deUpdate){
            updateDepartment();
        }        
        else if(event.getSource() == btn_sbUp){
            updateViewSubject();         
        }
        else if(event.getSource() == btn_sbUpdate){
            updateSubject();
        }
        else if(event.getSource() == btn_atUp){
            updateViewAttendance();
        }
        else if(event.getSource() == btn_atUpdate){
            updateAttendance();
        }      
        else if(event.getSource() == btn_exUp){
            updateViewExam();            
        }
        else if(event.getSource() == btn_exUpdate){
            updateExam();
        }
        else if(event.getSource() == btn_stDelete){
            deleteValues();
        }
        else if(event.getSource() == btn_deDelete){
            deleteValues();
        }
        else if(event.getSource() == btn_sbDelete){
            deleteValues();
        }
        else if(event.getSource() == btn_atDelete){
            deleteValues();
        }
        else if(event.getSource() == btn_exDelete){
            deleteValues();
        }
        
        
        tf_searchView.setOnKeyReleased(new EventHandler<KeyEvent>() {
            public void handle(KeyEvent ke) {
                viewSearch(tableChoice);
            }
        });
        tf_searchUp.setOnKeyReleased(new EventHandler<KeyEvent>() {
            public void handle(KeyEvent ke) {
                updateSearch(tableChoice);
            }
        });
        
        tv_Up.setOnMouseClicked((MouseEvent mevent) -> {
            if (mevent.getClickCount() > 1) {
                onTableRowClick();
            }
        });

    }
    
    private void onTableRowClick() {
        if (tv_Up.getSelectionModel().getSelectedItem() != null) {
            Student row = tv_Up.getSelectionModel().getSelectedItem();
            switch (tableChoice) {
                case 1:
                    tf_updateusn.setText(row.getUsn());
                    tf_updatename.setText(row.getName());
                    tf_updateadd.setText(row.getAddr());
                    tf_updatecon.setText(row.getCon());
                    pn_upCon.setVisible(true);
                    pn_stUpdate.setVisible(true);
                    pn_deUpdate.setVisible(false);
                    pn_sbUpdate.setVisible(false);
                    pn_atUpdate.setVisible(false);
                    pn_exUpdate.setVisible(false);
                    pn_admin.setVisible(false);
                    break;
                case 2:
                    tf_updatededno.setText(Integer.toString(row.getDno()));
                    tf_updatedname.setText(row.getDname());
                    pn_upCon.setVisible(true);
                    pn_deUpdate.setVisible(true);
                    pn_stUpdate.setVisible(false);
                    pn_sbUpdate.setVisible(false);
                    pn_atUpdate.setVisible(false);
                    pn_exUpdate.setVisible(false);
                    pn_admin.setVisible(false);
                    break;
                case 3:
                    tf_updatescode.setText(row.getScode());
                    tf_updatesname.setText(row.getSname());
                    tf_updatebranch.setText(row.getBranch());
                    tf_updatedno.setText(Integer.toString(row.getDno()));
                    pn_upCon.setVisible(true);
                    pn_sbUpdate.setVisible(true);
                    pn_stUpdate.setVisible(false);
                    pn_deUpdate.setVisible(false);
                    pn_atUpdate.setVisible(false);
                    pn_exUpdate.setVisible(false);
                    pn_admin.setVisible(false);
                    break;
                case 4:
                    tf_updateausn.setText(row.getUsn());
                    tf_updateascode.setText(row.getScode());
                    tf_updateaperc.setText(Double.toString(row.getPerc()));
                    pn_upCon.setVisible(true);
                    pn_atUpdate.setVisible(true);
                    pn_stUpdate.setVisible(false);
                    pn_deUpdate.setVisible(false);
                    pn_sbUpdate.setVisible(false);
                    pn_exUpdate.setVisible(false);
                    pn_admin.setVisible(false);
                    break;
                case 5:
                    tf_updateeusn.setText(row.getUsn());
                    tf_updateescode.setText(row.getScode());
                    tf_updateedno.setText(Integer.toString(row.getDno()));
                    tf_updatet1.setText(Integer.toString(row.getT1()));
                    tf_updatet2.setText(Integer.toString(row.getT2()));
                    tf_updatet3.setText(Integer.toString(row.getT3()));
                    tf_updateext.setText(Integer.toString(row.getExt()));
                    pn_upCon.setVisible(true);
                    pn_exUpdate.setVisible(true);
                    pn_stUpdate.setVisible(false);
                    pn_deUpdate.setVisible(false);
                    pn_atUpdate.setVisible(false);
                    pn_sbUpdate.setVisible(false);
                    pn_admin.setVisible(false);
                    break;
                default:
                    break;
            }
        }
}
    
    private void adminInsert(){
        String aid = tf_adminid.getText();
        String aname = tf_adminname.getText();
        String apass = tf_adminpass.getText();
        
        if(!"".equals(aid) && !"".equals(aname) && !"".equals(apass)){
            Connection db = getConnection();
            try{
                CallableStatement stmt = db.prepareCall("{call admin_insert(?, ?, ?)}");
                stmt.setString(1, aid);
                stmt.setString(2, aname);
                stmt.setString(3, apass);
                stmt.execute();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Insert Successful");
                alert.setContentText("User Added Successfully");
                alert.showAndWait();
            }catch(SQLException sqle){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Insertion Error");
                alert.setContentText(sqle.getMessage());
                TextArea area = new TextArea(sqle.toString());
                alert.getDialogPane().setExpandableContent(area);
                alert.showAndWait();
            }catch(Exception e){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Error !!!");
                alert.setContentText(e.getMessage());
                TextArea area = new TextArea(e.toString());
                alert.getDialogPane().setExpandableContent(area);
                alert.showAndWait();
            }    
        }
        else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Warning");
            alert.setHeaderText("Insertion not possble");
            alert.setContentText("Text Fileds cannot be empty ");
            alert.showAndWait();
        }
        
    }
    
    private void insertStudent1(){
        String usn,name,add,con;
        usn = tf_insert1usn.getText();
        name = tf_insert1name.getText();
        add = tf_insert1add.getText();
        con = tf_insert1con.getText();
        
        String query = "INSERT INTO `student` (`usn`, `name`, `address`, `contact`) VALUES ('"+usn+"', '"+name+"', '"+add+"', '"+Integer.parseInt(con)+"')";
        insert(query);
    }
    
    private void insertSubject1(){
        String scode,sname,branch,dno;
        scode = tf_insert1scode.getText();
        sname = tf_insert1sname.getText();
        branch = tf_insert1branch.getText();
        dno = tf_insert1dno.getText();
        
        String query = "INSERT INTO `subject` (`scode`, `sname`, `branch`, `dno`) VALUES ('"+scode+"', '"+sname+"', '"+branch+"', '"+Integer.parseInt(dno)+"')";
        insert(query);
    }
    
    private void insertDepartment2(){
        String dno,dname;
        dno = tf_insert2dno.getText();
        dname = tf_insert2dname.getText();
      
        String query = "INSERT INTO `department` (`dno`, `dname`) VALUES ('"+Integer.parseInt(dno)+"', '"+dname+"')";
        insert(query);
    }
    
    private void insertAttendance2(){
        String usn,scode,perc;
        usn = tf_insert2ausn.getText();
        scode = tf_insert2ascode.getText();
        perc = tf_insert2aperc.getText();
             
        String query = "INSERT INTO `attendance` (`usn`, `scode`, `perc`) VALUES ('"+usn+"', '"+scode+"', '"+perc+"')";
        insert(query);
    }
    
    private void insertExam2(){
        String usn,scode,dno,t1,t2,t3,ext;
        usn = tf_insert2eusn.getText();
        scode = tf_insert2escode.getText();
        dno = tf_insert2edno.getText();
        t1 = tf_insert2t1.getText();
        t2 = tf_insert2t2.getText();
        t3 = tf_insert2t3.getText();
        ext = tf_insert2ext.getText();
                     
        String query = "INSERT INTO `exam` (`usn`, `scode`, `dno`, `t1`, `t2`, `t3`, `ext`) VALUES ('"+usn+"', '"+scode+"', '"+Integer.parseInt(dno)+"', '"+Integer.parseInt(t1)+"', '"+Integer.parseInt(t2)+"', '"+Integer.parseInt(t3)+"', '"+Integer.parseInt(ext)+"')";
        insert(query);
    }
    
    private void insert(String Query){
        Connection db = getConnection();
        
        try{  
            Statement st = db.createStatement();
            st.executeUpdate(Query);
            st.close();
            db.close();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Insert Successful");
            alert.setContentText("Details Added Successfully");
            alert.showAndWait();
        }catch(SQLException sqle){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Insertion Error");
            alert.setContentText(sqle.getMessage());
            TextArea area = new TextArea(sqle.toString());
            alert.getDialogPane().setExpandableContent(area);
            alert.showAndWait();
        }catch(Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error !!!");
            alert.setContentText(e.getMessage());
            TextArea area = new TextArea(e.toString());
            alert.getDialogPane().setExpandableContent(area);
            alert.showAndWait();
        }
    }
    
    private void viewStudent(){
        tf_searchView.setEditable(true);
        tc_col1.setText("USN");
        tc_col2.setText("Name");
        tc_col3.setText("Address");
        tc_col4.setText("Contact");
        tc_col1.setVisible(true);
        tc_col2.setVisible(true);
        tc_col3.setVisible(true);
        tc_col4.setVisible(true);
        tc_col5.setVisible(false);
        tc_col6.setVisible(false);
        tc_col7.setVisible(false);
        tc_col1.setCellValueFactory(new PropertyValueFactory<>("usn"));
        tc_col2.setCellValueFactory(new PropertyValueFactory<>("name"));
        tc_col3.setCellValueFactory(new PropertyValueFactory<>("addr"));
        tc_col4.setCellValueFactory(new PropertyValueFactory<>("con"));
        
        tableChoice = 1;
        data = FXCollections.observableArrayList();
        
        Connection db = getConnection();
        try{
           Statement st = db.createStatement();
           String query = "SELECT usn,name,address,contact FROM student";
           if(searchBool){
                query = "SELECT usn,name,address,contact FROM student WHERE usn Like '%"+tf_searchView.getText()+"%' OR name Like '%"+tf_searchView.getText()+"%' OR address Like '%"+tf_searchView.getText()+"%' OR contact Like '%"+tf_searchView.getText()+"%'";               
                searchBool = false;
           }
           ResultSet rs = st.executeQuery(query);
           
           while(rs.next()){
               Student s = new Student(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4));
               
               data.add(s);
            }
            tv_view.setItems(data);
            st.close();
            db.close();
        }catch(SQLException sqle){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("View Error");
            alert.setContentText(sqle.getMessage());
            TextArea area = new TextArea(sqle.toString());
            alert.getDialogPane().setExpandableContent(area);
            alert.showAndWait();
        }      
    }
    
    private void viewDepartment(){
        tf_searchView.setEditable(true);
        tc_col1.setText("Department No.");
        tc_col2.setText("Department Name");
        tc_col1.setVisible(true);
        tc_col2.setVisible(true);
        tc_col3.setVisible(false);
        tc_col4.setVisible(false);
        tc_col5.setVisible(false);
        tc_col6.setVisible(false);
        tc_col7.setVisible(false);
        tc_col1.setCellValueFactory(new PropertyValueFactory<>("dno"));
        tc_col2.setCellValueFactory(new PropertyValueFactory<>("dname"));
        
        tableChoice = 2;
        data = FXCollections.observableArrayList();
        
        Connection db = getConnection();
        try{
           Statement st = db.createStatement();
           String query = "SELECT dno,dname FROM department";
           if(searchBool){
                query = "SELECT dno,dname FROM department WHERE dno Like '%"+tf_searchView.getText()+"%' OR dname Like '%"+tf_searchView.getText()+"%'";               
                searchBool = false;
           }
           ResultSet rs = st.executeQuery(query);
           
           while(rs.next()){
               Student s = new Student(rs.getInt(1),rs.getString(2));
               
               data.add(s);
            }
            tv_view.setItems(data);
            st.close();
            db.close();
        }catch(SQLException sqle){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("View Error");
            alert.setContentText(sqle.getMessage());
            TextArea area = new TextArea(sqle.toString());
            alert.getDialogPane().setExpandableContent(area);
            alert.showAndWait();
        }      
    }
    
    private void viewSubject(){
        tf_searchView.setEditable(true);
        tc_col1.setText("Subject Code");
        tc_col2.setText("Subject Name");
        tc_col3.setText("Branch");
        tc_col4.setText("Department No.");
        tc_col1.setVisible(true);
        tc_col2.setVisible(true);
        tc_col3.setVisible(true);
        tc_col4.setVisible(true);
        tc_col5.setVisible(false);
        tc_col6.setVisible(false);
        tc_col7.setVisible(false);
        tc_col1.setCellValueFactory(new PropertyValueFactory<>("scode"));
        tc_col2.setCellValueFactory(new PropertyValueFactory<>("sname"));
        tc_col3.setCellValueFactory(new PropertyValueFactory<>("branch"));
        tc_col4.setCellValueFactory(new PropertyValueFactory<>("dno"));
        
        tableChoice = 3;
        data = FXCollections.observableArrayList();
        
        Connection db = getConnection();
        try{
           Statement st = db.createStatement();
           String query = "SELECT scode,sname,branch,dno FROM subject";
           if(searchBool){
                query = "SELECT scode,sname,branch,dno FROM subject WHERE scode Like '%"+tf_searchView.getText()+"%' OR sname Like '%"+tf_searchView.getText()+"%' OR branch Like '%"+tf_searchView.getText()+"%' OR dno Like '%"+tf_searchView.getText()+"%'";               
                searchBool = false;
           }
           ResultSet rs = st.executeQuery(query);
           
           while(rs.next()){
               Student s = new Student(rs.getString(1),rs.getString(2),rs.getString(3),rs.getInt(4));
               
               data.add(s);
            }
            tv_view.setItems(data);
            st.close();
            db.close();
        }catch(SQLException sqle){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("View Error");
            alert.setContentText(sqle.getMessage());
            TextArea area = new TextArea(sqle.toString());
            alert.getDialogPane().setExpandableContent(area);
            alert.showAndWait();
        }      
    }
    
    private void viewAttendance(){
        tf_searchView.setEditable(true);
        tc_col1.setText("USN");
        tc_col2.setText("Subject Code");
        tc_col3.setText("Percentage");
        tc_col1.setVisible(true);
        tc_col2.setVisible(true);
        tc_col3.setVisible(true);
        tc_col4.setVisible(false);
        tc_col5.setVisible(false);
        tc_col6.setVisible(false);
        tc_col7.setVisible(false);
        tc_col1.setCellValueFactory(new PropertyValueFactory<>("usn"));
        tc_col2.setCellValueFactory(new PropertyValueFactory<>("scode"));
        tc_col3.setCellValueFactory(new PropertyValueFactory<>("perc"));
        
        tableChoice = 4;
        data = FXCollections.observableArrayList();
        
        Connection db = getConnection();
        try{
           Statement st = db.createStatement();
           String query = "SELECT usn,scode,perc FROM attendance";
           if(searchBool){
                query = "SELECT usn,scode,perc FROM attendance WHERE usn Like '%"+tf_searchView.getText()+"%' OR scode Like '%"+tf_searchView.getText()+"%' OR perc Like '%"+tf_searchView.getText()+"%'";               
                searchBool = false;
           }
           ResultSet rs = st.executeQuery(query);
           
           while(rs.next()){
               Student s = new Student(rs.getString(1),rs.getString(2),rs.getDouble(3));
               
               data.add(s);
            }
            tv_view.setItems(data);
            st.close();
            db.close();
        }catch(SQLException sqle){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("View Error");
            alert.setContentText(sqle.getMessage());
            TextArea area = new TextArea(sqle.toString());
            alert.getDialogPane().setExpandableContent(area);
            alert.showAndWait();
        }      
    }
    
    private void viewExam(){
        tf_searchView.setEditable(true);
        tc_col1.setText("USN");
        tc_col2.setText("Subject Code");
        tc_col3.setText("Department No.");
        tc_col4.setText("Test 1");
        tc_col5.setText("Test 2");
        tc_col6.setText("Test 3");
        tc_col7.setText("External");
        tc_col1.setVisible(true);
        tc_col2.setVisible(true);
        tc_col3.setVisible(true);
        tc_col4.setVisible(true);
        tc_col5.setVisible(true);
        tc_col6.setVisible(true);
        tc_col7.setVisible(true);
        tc_col1.setCellValueFactory(new PropertyValueFactory<>("usn"));
        tc_col2.setCellValueFactory(new PropertyValueFactory<>("scode"));
        tc_col3.setCellValueFactory(new PropertyValueFactory<>("dno"));
        tc_col4.setCellValueFactory(new PropertyValueFactory<>("t1"));
        tc_col5.setCellValueFactory(new PropertyValueFactory<>("t2"));
        tc_col6.setCellValueFactory(new PropertyValueFactory<>("t3"));
        tc_col7.setCellValueFactory(new PropertyValueFactory<>("ext"));
        
        tableChoice = 5;
        data = FXCollections.observableArrayList();
        
        Connection db = getConnection();
        try{
           Statement st = db.createStatement();
           String query = "SELECT usn,scode,dno,t1,t2,t3,ext FROM exam";
           if(searchBool){
                query = "SELECT usn,scode,dno,t1,t2,t3,ext FROM exam WHERE usn Like '%"+tf_searchView.getText()+"%' OR scode Like '%"+tf_searchView.getText()+"%' OR dno Like '%"+tf_searchView.getText()+"%' OR t1 Like '%"+tf_searchView.getText()+"%' OR t2 Like '%"+tf_searchView.getText()+"%' OR t3 Like '%"+tf_searchView.getText()+"%' OR ext Like '%"+tf_searchView.getText()+"%'";               
                searchBool = false;
           }
           ResultSet rs = st.executeQuery(query);
           
           while(rs.next()){
               Student s = new Student(rs.getString(1),rs.getString(2),rs.getInt(3),rs.getInt(4),rs.getInt(5),rs.getInt(6),rs.getInt(7));
               
               data.add(s);
            }
            tv_view.setItems(data);
            st.close();
            db.close();
        }catch(SQLException sqle){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("View Error");
            alert.setContentText(sqle.getMessage());
            TextArea area = new TextArea(sqle.toString());
            alert.getDialogPane().setExpandableContent(area);
            alert.showAndWait();
        }      
    }
    
    private void viewSearch(int choice){
        switch (choice) {
            case 1: viewStudent();
                    searchBool = true;
                break;
            case 2: viewDepartment();
                    searchBool = true;
                break;
            case 3: viewSubject();
                    searchBool = true;
                break;
            case 4: viewAttendance();
                    searchBool = true;
                break;
            case 5: viewExam();
                    searchBool = true;
                break;
            default:
                break;
        }
    }
    
    private void updateSearch(int choice){
        switch (choice) {
            case 1: updateViewStudent();
                    searchBool = true;
                break;
            case 2: updateViewDepartment();
                    searchBool = true;
                break;
            case 3: updateViewSubject();
                    searchBool = true;
                break;
            case 4: updateViewAttendance();
                    searchBool = true;
                break;
            case 5: updateViewExam();
                    searchBool = true;
                break;
            default:
                break;
        }
    }
    
    private void updateViewStudent(){
        tf_searchUp.setEditable(true);
        tc_col1Up.setText("USN");
        tc_col2Up.setText("Name");
        tc_col3Up.setText("Address");
        tc_col4Up.setText("Contact");
        tc_col1Up.setVisible(true);
        tc_col2Up.setVisible(true);
        tc_col3Up.setVisible(true);
        tc_col4Up.setVisible(true);
        tc_col5Up.setVisible(false);
        tc_col6Up.setVisible(false);
        tc_col7Up.setVisible(false);
        tc_col1Up.setCellValueFactory(new PropertyValueFactory<>("usn"));
        tc_col2Up.setCellValueFactory(new PropertyValueFactory<>("name"));
        tc_col3Up.setCellValueFactory(new PropertyValueFactory<>("addr"));
        tc_col4Up.setCellValueFactory(new PropertyValueFactory<>("con"));
        
        tableChoice = 1;
        data = FXCollections.observableArrayList();
        
        Connection db = getConnection();
        try{
           Statement st = db.createStatement();
           String query = "SELECT usn,name,address,contact FROM student";
           if(searchBool){
                query = "SELECT usn,name,address,contact FROM student WHERE usn Like '%"+tf_searchUp.getText()+"%' OR name Like '%"+tf_searchUp.getText()+"%' OR address Like '%"+tf_searchUp.getText()+"%' OR contact Like '%"+tf_searchUp.getText()+"%'";               
                searchBool = false;
           }
           ResultSet rs = st.executeQuery(query);
           
           while(rs.next()){
               Student s = new Student(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4));
               
               data.add(s);
            }
            tv_Up.setItems(data);
            st.close();
            db.close();
        }catch(SQLException sqle){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("View Error");
            alert.setContentText(sqle.getMessage());
            TextArea area = new TextArea(sqle.toString());
            alert.getDialogPane().setExpandableContent(area);
            alert.showAndWait();
        }      
    }
    
    private void updateViewDepartment(){
        tf_searchUp.setEditable(true);
        tc_col1Up.setText("Department No.");
        tc_col2Up.setText("Department Name");
        tc_col1Up.setVisible(true);
        tc_col2Up.setVisible(true);
        tc_col3Up.setVisible(false);
        tc_col4Up.setVisible(false);
        tc_col5Up.setVisible(false);
        tc_col6Up.setVisible(false);
        tc_col7Up.setVisible(false);
        tc_col1Up.setCellValueFactory(new PropertyValueFactory<>("dno"));
        tc_col2Up.setCellValueFactory(new PropertyValueFactory<>("dname"));
        
        tableChoice = 2;
        data = FXCollections.observableArrayList();
        
        Connection db = getConnection();
        try{
           Statement st = db.createStatement();
           String query = "SELECT dno,dname FROM department";
           if(searchBool){
                query = "SELECT dno,dname FROM department WHERE dno Like '%"+tf_searchUp.getText()+"%' OR dname Like '%"+tf_searchUp.getText()+"%'";               
                searchBool = false;
           }
           ResultSet rs = st.executeQuery(query);
           
           while(rs.next()){
               Student s = new Student(rs.getInt(1),rs.getString(2));
               
               data.add(s);
            }
            tv_Up.setItems(data);
            st.close();
            db.close();
        }catch(SQLException sqle){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("View Error");
            alert.setContentText(sqle.getMessage());
            TextArea area = new TextArea(sqle.toString());
            alert.getDialogPane().setExpandableContent(area);
            alert.showAndWait();
        }      
    }
    
    private void updateViewSubject(){
        tf_searchUp.setEditable(true);
        tc_col1Up.setText("Subject Code");
        tc_col2Up.setText("Subject Name");
        tc_col3Up.setText("Branch");
        tc_col4Up.setText("Department No.");
        tc_col1Up.setVisible(true);
        tc_col2Up.setVisible(true);
        tc_col3Up.setVisible(true);
        tc_col4Up.setVisible(true);
        tc_col5Up.setVisible(false);
        tc_col6Up.setVisible(false);
        tc_col7Up.setVisible(false);
        tc_col1Up.setCellValueFactory(new PropertyValueFactory<>("scode"));
        tc_col2Up.setCellValueFactory(new PropertyValueFactory<>("sname"));
        tc_col3Up.setCellValueFactory(new PropertyValueFactory<>("branch"));
        tc_col4Up.setCellValueFactory(new PropertyValueFactory<>("dno"));
        
        tableChoice = 3;
        data = FXCollections.observableArrayList();
        
        Connection db = getConnection();
        try{
           Statement st = db.createStatement();
           String query = "SELECT scode,sname,branch,dno FROM subject";
           if(searchBool){
                query = "SELECT scode,sname,branch,dno FROM subject WHERE scode Like '%"+tf_searchUp.getText()+"%' OR sname Like '%"+tf_searchUp.getText()+"%' OR branch Like '%"+tf_searchUp.getText()+"%' OR dno Like '%"+tf_searchUp.getText()+"%'";               
                searchBool = false;
           }
           ResultSet rs = st.executeQuery(query);
           
           while(rs.next()){
               Student s = new Student(rs.getString(1),rs.getString(2),rs.getString(3),rs.getInt(4));
               
               data.add(s);
            }
            tv_Up.setItems(data);
            st.close();
            db.close();
        }catch(SQLException sqle){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("View Error");
            alert.setContentText(sqle.getMessage());
            TextArea area = new TextArea(sqle.toString());
            alert.getDialogPane().setExpandableContent(area);
            alert.showAndWait();
        }      
    }
    
    private void updateViewAttendance(){
        tf_searchUp.setEditable(true);
        tc_col1Up.setText("USN");
        tc_col2Up.setText("Subject Code");
        tc_col3Up.setText("Percentage");
        tc_col1Up.setVisible(true);
        tc_col2Up.setVisible(true);
        tc_col3Up.setVisible(true);
        tc_col4Up.setVisible(false);
        tc_col5Up.setVisible(false);
        tc_col6Up.setVisible(false);
        tc_col7Up.setVisible(false);
        tc_col1Up.setCellValueFactory(new PropertyValueFactory<>("usn"));
        tc_col2Up.setCellValueFactory(new PropertyValueFactory<>("scode"));
        tc_col3Up.setCellValueFactory(new PropertyValueFactory<>("perc"));
        
        tableChoice = 4;
        data = FXCollections.observableArrayList();
        
        Connection db = getConnection();
        try{
           Statement st = db.createStatement();
           String query = "SELECT usn,scode,perc FROM attendance";
           if(searchBool){
                query = "SELECT usn,scode,perc FROM attendance WHERE usn Like '%"+tf_searchUp.getText()+"%' OR scode Like '%"+tf_searchUp.getText()+"%' OR perc Like '%"+tf_searchUp.getText()+"%'";               
                searchBool = false;
           }
           ResultSet rs = st.executeQuery(query);
           
           while(rs.next()){
               Student s = new Student(rs.getString(1),rs.getString(2),rs.getDouble(3));
               
               data.add(s);
            }
            tv_Up.setItems(data);
            st.close();
            db.close();
        }catch(SQLException sqle){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("View Error");
            alert.setContentText(sqle.getMessage());
            TextArea area = new TextArea(sqle.toString());
            alert.getDialogPane().setExpandableContent(area);
            alert.showAndWait();
        }      
    }
    
    private void updateViewExam(){
        tf_searchUp.setEditable(true);
        tc_col1Up.setText("USN");
        tc_col2Up.setText("Subject Code");
        tc_col3Up.setText("Department No.");
        tc_col4Up.setText("Test 1");
        tc_col5Up.setText("Test 2");
        tc_col6Up.setText("Test 3");
        tc_col7Up.setText("External");
        tc_col1Up.setVisible(true);
        tc_col2Up.setVisible(true);
        tc_col3Up.setVisible(true);
        tc_col4Up.setVisible(true);
        tc_col5Up.setVisible(true);
        tc_col6Up.setVisible(true);
        tc_col7Up.setVisible(true);
        tc_col1Up.setCellValueFactory(new PropertyValueFactory<>("usn"));
        tc_col2Up.setCellValueFactory(new PropertyValueFactory<>("scode"));
        tc_col3Up.setCellValueFactory(new PropertyValueFactory<>("dno"));
        tc_col4Up.setCellValueFactory(new PropertyValueFactory<>("t1"));
        tc_col5Up.setCellValueFactory(new PropertyValueFactory<>("t2"));
        tc_col6Up.setCellValueFactory(new PropertyValueFactory<>("t3"));
        tc_col7Up.setCellValueFactory(new PropertyValueFactory<>("ext"));
        
        tableChoice = 5;
        data = FXCollections.observableArrayList();
        
        Connection db = getConnection();
        try{
           Statement st = db.createStatement();
           String query = "SELECT usn,scode,dno,t1,t2,t3,ext FROM exam";
           if(searchBool){
                query = "SELECT usn,scode,dno,t1,t2,t3,ext FROM exam WHERE usn Like '%"+tf_searchUp.getText()+"%' OR scode Like '%"+tf_searchUp.getText()+"%' OR dno Like '%"+tf_searchUp.getText()+"%' OR t1 Like '%"+tf_searchUp.getText()+"%' OR t2 Like '%"+tf_searchUp.getText()+"%' OR t3 Like '%"+tf_searchUp.getText()+"%' OR ext Like '%"+tf_searchUp.getText()+"%'";               
                searchBool = false;
           }
           ResultSet rs = st.executeQuery(query);
           
           while(rs.next()){
               Student s = new Student(rs.getString(1),rs.getString(2),rs.getInt(3),rs.getInt(4),rs.getInt(5),rs.getInt(6),rs.getInt(7));
               
               data.add(s);
            }
            tv_Up.setItems(data);
            st.close();
            db.close();
        }catch(SQLException sqle){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("View Error");
            alert.setContentText(sqle.getMessage());
            TextArea area = new TextArea(sqle.toString());
            alert.getDialogPane().setExpandableContent(area);
            alert.showAndWait();
        }      
    }
    
    private void updateStudent(){
        String usn,name,add,con;
        usn = tf_updateusn.getText();
        name = tf_updatename.getText();
        add = tf_updateadd.getText();
        con = tf_updatecon.getText();
        
        Connection db = getConnection();
        
        try{  
            Statement st = db.createStatement();
            String query = "UPDATE `student` SET `name`='"+name+"',`address`='"+add+"',`contact`='"+con+"' WHERE `usn`='"+usn+"'";
            st.executeUpdate(query);
            st.close();
            db.close();
            updateViewStudent();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Update Successful");
            alert.setContentText("Student details updated Successfully");
            alert.showAndWait();
        }catch(SQLException sqle){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Insertion Error");
            alert.setContentText(sqle.getMessage());
            TextArea area = new TextArea(sqle.toString());
            alert.getDialogPane().setExpandableContent(area);
            alert.showAndWait();
        }       
    }
    
    private void updateDepartment(){
        String dno,dname;
        dno = tf_updatededno.getText();
        dname = tf_updatedname.getText();
             
        Connection db = getConnection();
        
        try{  
            Statement st = db.createStatement();
            String query = "UPDATE `department` SET `dname`='"+dname+"' WHERE `dno`='"+Integer.parseInt(dno)+"'";
            st.executeUpdate(query);
            st.close();
            db.close();
            updateViewDepartment();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Update Successful");
            alert.setContentText("Department details updated Successfully");
            alert.showAndWait();
        }catch(SQLException sqle){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Insertion Error");
            alert.setContentText(sqle.getMessage());
            TextArea area = new TextArea(sqle.toString());
            alert.getDialogPane().setExpandableContent(area);
            alert.showAndWait();
        }       
    }
    
    private void updateSubject(){
        String scode,sname,branch,dno;
        scode = tf_updatescode.getText();
        sname = tf_updatesname.getText();
        branch = tf_updatebranch.getText();
        dno = tf_updatedno.getText();
        
        Connection db = getConnection();
        
        try{           
            Statement st = db.createStatement();
            String query = "UPDATE `subject` SET `sname`='"+sname+"',`branch`='"+branch+"',`dno`='"+Integer.parseInt(dno)+"' WHERE `scode`='"+scode+"'";
            st.executeUpdate(query);
            st.close();
            db.close();
            updateViewSubject();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Update Successful");
            alert.setContentText("Subject details updated Successfully");
            alert.showAndWait();
        }catch(SQLException sqle){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Insertion Error");
            alert.setContentText(sqle.getMessage());
            TextArea area = new TextArea(sqle.toString());
            alert.getDialogPane().setExpandableContent(area);
            alert.showAndWait();
        }
    }
    
    private void updateAttendance(){
        String usn,scode,perc;
        usn = tf_updateausn.getText();
        scode = tf_updateascode.getText();
        perc = tf_updateaperc.getText();
             
        Connection db = getConnection();
        
        try{  
            Statement st = db.createStatement();
            String query = "UPDATE `attendance` SET `scode`='"+scode+"',`perc`='"+perc+"' WHERE `usn`='"+usn+"'";
            st.executeUpdate(query);
            st.close();
            db.close();
            updateViewAttendance();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Update Successful");
            alert.setContentText("Attendance details updated Successfully");
            alert.showAndWait();
        }catch(SQLException sqle){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Insertion Error");
            alert.setContentText(sqle.getMessage());
            TextArea area = new TextArea(sqle.toString());
            alert.getDialogPane().setExpandableContent(area);
            alert.showAndWait();
        }       
    }
    
    private void updateExam(){
        String usn,scode,dno,t1,t2,t3,ext;
        usn = tf_updateeusn.getText();
        scode = tf_updateescode.getText();
        dno = tf_updateedno.getText();
        t1 = tf_updatet1.getText();
        t2 = tf_updatet2.getText();
        t3 = tf_updatet3.getText();
        ext = tf_updateext.getText();
             
        Connection db = getConnection();
        
        try{  
            Statement st = db.createStatement();
            String query = "UPDATE `exam` SET `dno`="+Integer.parseInt(dno)+",`t1`="+Integer.parseInt(t1)+",`t2`="+Integer.parseInt(t2)+",`t3`="+Integer.parseInt(t3)+",`ext`="+Integer.parseInt(ext)+" WHERE `usn`='"+usn+"' AND `scode`='"+scode+"'";    
            st.executeUpdate(query);
            st.close();
            db.close();
            updateViewExam();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Update Successful");
            alert.setContentText("Exam details updated Successfully");
            alert.showAndWait();
        }catch(SQLException sqle){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Insertion Error");
            alert.setContentText(sqle.getMessage());
            TextArea area = new TextArea(sqle.toString());
            alert.getDialogPane().setExpandableContent(area);
            alert.showAndWait();
        }       
    }
    
    private void deleteValues(){
        String usnDel,scodeDel,dnoDel,query = null;
        switch (tableChoice) {
            case 1:
                usnDel = tf_updateusn.getText();
                query = "DELETE FROM `student` WHERE `usn`='"+usnDel+"'";
                break;
            case 2:
                dnoDel = tf_updatededno.getText();
                query = "DELETE FROM `department` WHERE `dno`='"+dnoDel+"'";
                break;
            case 3:
                scodeDel = tf_updatescode.getText();
                query = "DELETE FROM `subject` WHERE `scode`='"+scodeDel+"'";
                break;
            case 4:
                usnDel = tf_updateausn.getText();
                query = "DELETE FROM `attendance` WHERE `usn`='"+usnDel+"'";
                break;
            case 5:
                usnDel = tf_updateeusn.getText();
                scodeDel = tf_updateescode.getText();
                query = "DELETE FROM `exam` WHERE `usn`='"+usnDel+"' AND `scode`='"+scodeDel+"'";
                break;
            default:
                break;
        }
        
        Connection db = getConnection();
        try{  
            Statement st = db.createStatement();
            st.executeUpdate(query);
            st.close();
            db.close();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Delete Successful");
            alert.setContentText("Selected row deleted Successfully");
            alert.showAndWait();
        }catch(SQLException sqle){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Insertion Error");
            alert.setContentText(sqle.getMessage());
            TextArea area = new TextArea(sqle.toString());
            alert.getDialogPane().setExpandableContent(area);
            alert.showAndWait();
        }
    }
    
    private Connection getConnection(){
        Connection con;
        try{
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/student_dbms", "root", "");
            return con;
        }catch(SQLException e){
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
        Anchor.setBackground(Background.EMPTY);
    }       
}