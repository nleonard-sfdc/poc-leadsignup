/*
 * Copyright, 1999-2017, salesforce.com All Rights Reserved Company Confidential
 */
package servlet;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @author nleonard
 * @since 2018-08
 */
public class Signup {
    String formName;
    SignupStatus signupStatus = SignupStatus.SIGNING_UP;
    int signupRequestId;
    int attempt;
    String userEmail;
    String userFirstName;
    String userLastName;
    String companyCountry;
    String ipAddress;
    String confPage;
    String timeSubmitted;

    public Signup(HttpServletRequest httpServletRequest){
        formName = httpServletRequest.getParameter("formName");
        userEmail = httpServletRequest.getParameter("UserEmail");
        signupRequestId = userEmail.hashCode();
        attempt = 1;
        userFirstName = httpServletRequest.getParameter("UserFirstName");
        userLastName = httpServletRequest.getParameter("UserLastName");
        companyCountry = httpServletRequest.getParameter("CompanyCountry");
        ipAddress = httpServletRequest.getHeader("True-Client-IP");
        confPage = httpServletRequest.getParameter("confPage");
        timeSubmitted = (new Date()).toString();

    }

    @Override public String toString() {
        return "Lead{" +
            "userEmail='" + userEmail + '\'' +
            ", formName='" + formName + '\'' +
            ", time= '" + timeSubmitted + '\'' +
            ", status= '" + signupStatus + '\'' +
            ", signupRequestId= '" + signupRequestId + '\'' +
            ", userFirstName='" + userFirstName + '\'' +
            ", userLastName='" + userLastName + '\'' +
            ", companyCountry='" + companyCountry + '\'' +
            ", ipAddress='" + ipAddress + '\'' +
            ", confPage='" + confPage + '\'' +
            '}';
    }

    public String getFormName() {
        return formName;
    }

    public void setFormName(String formName) {
        this.formName = formName;
    }

    public SignupStatus getSignupStatus() {
        return signupStatus;
    }

    public void setSignupStatus(SignupStatus signupStatus) {
        this.signupStatus = signupStatus;
    }

    public int getSignupRequestId() {
        return signupRequestId;
    }

    public void setSignupRequestId(int signupRequestId) {
        this.signupRequestId = signupRequestId;
    }

    public int getAttempt() {
        return attempt;
    }

    public void setAttempt(int attempt) {
        this.attempt = attempt;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserFirstName() {
        return userFirstName;
    }

    public void setUserFirstName(String userFirstName) {
        this.userFirstName = userFirstName;
    }

    public String getUserLastName() {
        return userLastName;
    }

    public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;
    }

    public String getCompanyCountry() {
        return companyCountry;
    }

    public void setCompanyCountry(String companyCountry) {
        this.companyCountry = companyCountry;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getConfPage() {
        return confPage;
    }

    public void setConfPage(String confPage) {
        this.confPage = confPage;
    }
}
