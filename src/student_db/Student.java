/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package student_db;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
/**
 *
 * @author vishaak
 */
public class Student {
    //Student Table
    private final SimpleStringProperty usn;
    private final SimpleStringProperty name;
    private final SimpleStringProperty addr;
    private final SimpleStringProperty con;
    //Department Table
    private final SimpleIntegerProperty dno;
    private final SimpleStringProperty dname;
    //Subject Table
    private final SimpleStringProperty scode;
    private final SimpleStringProperty sname;
    private final SimpleStringProperty branch;
    //Attendance Table
    private final SimpleDoubleProperty perc;
    //Exam Table
    private final SimpleIntegerProperty t1;
    private final SimpleIntegerProperty t2;
    private final SimpleIntegerProperty t3;
    private final SimpleIntegerProperty ext;

    public Student(String usn, String name, String addr, String con) {
        //Student
        this.usn = new SimpleStringProperty(usn);
        this.name = new SimpleStringProperty(name);
        this.addr = new SimpleStringProperty(addr);
        this.con = new SimpleStringProperty(con);
        //Department
        this.dno = null;
        this.dname = null;
        //Subject
        this.scode = null;
        this.sname = null;
        this.branch = null;
        //Attendance
        this.perc = null;
        //Exam
        this.t1 = null;
        this.t2 = null;
        this.t3 = null;
        this.ext = null;
    }

    public Student(int dno, String dname) {
        //Student
        this.usn = null;
        this.name = null;
        this.addr = null;
        this.con = null;
        //Department
        this.dno = new SimpleIntegerProperty(dno);
        this.dname = new SimpleStringProperty(dname);
        //Subject
        this.scode = null;
        this.sname = null;
        this.branch = null;
        //Attendance
        this.perc = null;
        //Exam
        this.t1 = null;
        this.t2 = null;
        this.t3 = null;
        this.ext = null;
    }
    
    public Student(String scode, String sname, String branch, int dno) {
        //Student
        this.usn = null;
        this.name = null;
        this.addr = null;
        this.con = null;
        //Department
        this.dno = new SimpleIntegerProperty(dno);
        this.dname = null;
        //Subject
        this.scode = new SimpleStringProperty(scode);
        this.sname = new SimpleStringProperty(sname);
        this.branch = new SimpleStringProperty(branch);
        //Attendance
        this.perc = null;
        //Exam
        this.t1 = null;
        this.t2 = null;
        this.t3 = null;
        this.ext = null;
    }
    
    public Student(String usn, String scode, double perc) {
        //Student
        this.usn = new SimpleStringProperty(usn);
        this.name = null;
        this.addr = null;
        this.con = null;
        //Department
        this.dno = null;
        this.dname = null;
        //Subject
        this.scode = new SimpleStringProperty(scode);
        this.sname = null;
        this.branch = null;
        //Attendance
        this.perc = new SimpleDoubleProperty(perc);
        //Exam
        this.t1 = null;
        this.t2 = null;
        this.t3 = null;
        this.ext = null;
    }

    public Student(String usn, String scode, int dno, int t1, int t2, int t3, int ext) {
        //Student
        this.usn = new SimpleStringProperty(usn);
        this.name = null;
        this.addr = null;
        this.con = null;
        //Department
        this.dno = new SimpleIntegerProperty(dno);
        this.dname = null;
        //Subject
        this.scode = new SimpleStringProperty(scode);
        this.sname = null;
        this.branch = null;
        //Attendance
        this.perc = null;
        //Exam
        this.t1 = new SimpleIntegerProperty(t1);
        this.t2 = new SimpleIntegerProperty(t2);
        this.t3 = new SimpleIntegerProperty(t3);
        this.ext = new SimpleIntegerProperty(ext);
    }
    
    public String getUsn() {
        return usn.get();
    }

    public String getName() {
        return name.get();
    }

    public String getAddr() {
        return addr.get();
    }

    public String getCon() {
        return con.get();
    }
    
    public int getDno() {
        return dno.get();
    }

    public String getDname() {
        return dname.get();
    }
    
    public String getScode() {
        return scode.get();
    }

    public String getSname() {
        return sname.get();
    }

    public String getBranch() {
        return branch.get();
    }

    public double getPerc() {
        return perc.get();
    }

    public int getT1() {
        return t1.get();
    }

    public int getT2() {
        return t2.get();
    }

    public int getT3() {
        return t3.get();
    }

    public int getExt() {
        return ext.get();
    }
    
}
