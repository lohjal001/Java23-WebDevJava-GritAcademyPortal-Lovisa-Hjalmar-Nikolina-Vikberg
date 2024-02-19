package models;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

//userbean represents the connector
//user bean is transfering data between servlets
public class UserBean implements Serializable {

    private USER_TYPE userType = USER_TYPE.student;

    private STATE_TYPE stateType = STATE_TYPE.anonymous;

    private PRIVILAGE_TYPE privilageType = PRIVILAGE_TYPE.user;

    public PRIVILAGE_TYPE getPrivilageType() {
        return privilageType;
    }

    public void setPrivilageType(PRIVILAGE_TYPE privilageType) {
        this.privilageType = privilageType;
    }

    public STATE_TYPE getStateType() {
        return stateType;
    }

    public void setStateType(STATE_TYPE stateType) {
        this.stateType = stateType;
    }

    public USER_TYPE getUserType() {
        return userType;
    }

    public void setUserType(USER_TYPE userType) {
        this.userType = userType;
    }

    private String myVar = "";

    private String ID;

    public UserBean(String ID, USER_TYPE userType, PRIVILAGE_TYPE privilageType, STATE_TYPE stateType){
        this.ID=ID;
        this.userType=userType;
        this.privilageType=privilageType;
        this.stateType=stateType;
    }
    public void setID(String ID) {
        this.ID = ID;
    }
    public String getID() {
        return ID;
    }
    public String getMyVar() {
        return myVar;
    }

    public void setMyVar(String myVar) {
        this.myVar = myVar;
    }

    public enum USER_TYPE{
        //vanligtvis all caps i enum men bör matcha databasen i detta fallet
        student,
        teacher
    }

    public enum PRIVILAGE_TYPE{
        user,
        admin,
        superAdmin
    }

    public enum STATE_TYPE{
        anonymous,
        confirmed
    }



    //empty data list that are being used to store data from servlets requests
    LinkedList<String[]> data = null;

    public LinkedList getData() {
        return this.data;
    }

    public void setData(LinkedList data) {
        this.data = data;
    }
}
