package com.course.controller;

public class LoginController extends BaseController{

    public static final int MESSAGE_LOGIN_ADMIN            = 1;
    public static final int MESSAGE_ADMIN_CHANGE_PASSWWORD = 2;


    private Object model;
    private ControllerState messageState;

    protected void setMessageState(ControllerState messageState)
    {
        if (this.messageState != null)
        {
            this.messageState.dispose();
        }
        this.messageState = messageState;
    }

    public LoginController(Object model)
    {
        this.model = model; // LoginDataModel
        // Tell the LOginState to talk back to us..
        messageState = new LoginState(this);
    }

    public Object getModel()
    {
        return model;
    }

    @Override
    public boolean handleMessage(int what)
    {
        return messageState.handleMessage(what);
    }

    @Override
    public boolean handleMessage(int what, Object data)
    {
        return messageState.handleMessage(what, data);
        // messsageState is loginState object..
    }

}
