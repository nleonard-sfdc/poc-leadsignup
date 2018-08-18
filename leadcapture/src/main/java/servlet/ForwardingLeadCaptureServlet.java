package servlet;/*
 * Copyright, 1999-2017, salesforce.com All Rights Reserved Company Confidential
 */

import servlet.Lead;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * @author nleonard
 * @since 2018-08
 */
public class ForwardingLeadCaptureServlet extends HttpServlet {

    ArrayBlockingQueue<Lead> leads = new ArrayBlockingQueue(100);

    @Override protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        StringBuffer sb = new StringBuffer("Leads:<br />\n");
        for(Lead lead : leads){
            sb.append(lead + "<br />\n");
        }

        resp.getWriter().print(sb.toString());
        resp.getWriter().flush();
        resp.getWriter().close();

    }

    @Override protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Lead lead = new Lead(req);
        leads.add(lead);

    }
}
