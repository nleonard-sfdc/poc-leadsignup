/*
 * Copyright, 1999-2017, salesforce.com All Rights Reserved Company Confidential
 */
package servlet;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * @author nleonard
 * @since 2018-08
 */
public class SignupServlet extends HttpServlet {

    private Map<String, Signup> signups = new LinkedHashMap<>();

    protected static final int MAX_SIGNUPS = 100;
    protected static final int MAX_ATTEMPTS = 2;

    @Override protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        StringBuffer sb = new StringBuffer("Signups:<br />\n");
        for(String email : signups.keySet()){
            sb.append(signups.get(email) + "<br />\n");
        }
        resp.getWriter().print(sb.toString());
        resp.getWriter().flush();
        resp.getWriter().close();
    }

    @Override protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("UserEmail");

        if(StringUtils.isNotEmpty(email)){
            Signup signup = updateSignup(email, req);

            if(SignupStatus.ACTIVE.equals(signup.getSignupStatus())){
                resp.getWriter().println("Sent to org for: " + email);
            }else{
                String signupPath = "/leadcapture/SignupServlet?UserEmail=" + URLEncoder.encode(email, StandardCharsets.UTF_8.toString()) + "&SignupRequestId=" + signup.getSignupRequestId();
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("<html><head>\n");
                stringBuilder.append("<meta http-equiv='refresh' content='3;url=");
                stringBuilder.append(signupPath);
                stringBuilder.append("' />");
                stringBuilder.append("<title>Signup</title></head><body>\n");
                stringBuilder.append("Retry Signup. Click <a href='");
                stringBuilder.append(signupPath);
                stringBuilder.append("'>here</a>\n</body></html>");
                resp.getWriter().println(stringBuilder.toString());
            }
            resp.getWriter().flush();
            return;
        }
    }


    public Signup updateSignup(String email, HttpServletRequest req){

        if(signups.containsKey(email)){
            Signup signup = signups.get(email);
            int attempt = signup.getAttempt();
            if(attempt < MAX_ATTEMPTS){
                signup.setAttempt(attempt+1);
            }else{
                signup.setSignupStatus(SignupStatus.ACTIVE);
            }
            signups.put(email, signup);
            return signup;
        }else {
            if (signups.size() >= MAX_SIGNUPS) {
                signups.clear();
            }
            Signup signup = new Signup(req);
            signups.put(email, signup);
            return signup;
        }
    }

}

