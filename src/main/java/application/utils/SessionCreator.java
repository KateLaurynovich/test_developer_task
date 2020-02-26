package application.utils;

import application.models.SessionModel;
import application.dataBase.DataBaseResult;
import framework.browsers.BrowserActions;

public class SessionCreator {

    public static SessionModel createSession() {
        SessionModel sessionModel = new SessionModel(BrowserActions.getSession());
        sessionModel.setId(DataBaseResult.getSessionID(sessionModel));
        sessionModel.setCreatedTime(DataBaseResult.getSessionTime(sessionModel));
        return sessionModel;
    }
}
