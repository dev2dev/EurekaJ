package org.eurekaj.manager.servlets;

import org.eurekaj.api.datatypes.GroupedStatistics;
import org.eurekaj.manager.json.BuildJsonObjectsUtil;
import org.eurekaj.manager.json.ParseJsonObjects;
import org.eurekaj.manager.security.SecurityManager;
import org.json.JSONException;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by IntelliJ IDEA.
 * User: joahaa
 * Date: 1/20/11
 * Time: 9:16 AM
 * To change this template use File | Settings | File Templates.
 */
public class InstrumentationGroupServlet extends EurekaJGenericServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String jsonResponse = "";

        try {
            JSONObject jsonObject = BuildJsonObjectsUtil.extractRequestJSONContents(request);
            System.out.println("Accepted JSON: \n" + jsonObject);

            if (jsonObject.has("instrumentaionGroupName") && SecurityManager.isAuthenticatedAsAdmin()) {
                GroupedStatistics groupedStatistics = ParseJsonObjects.parseInstrumentationGroup(jsonObject);
                if (groupedStatistics != null && groupedStatistics.getName() != null && groupedStatistics.getName().length() > 0 && groupedStatistics.getGroupedPathList().size() > 0) {
                    getBerkeleyTreeMenuService().persistGroupInstrumentation(groupedStatistics);
                }
            }

            if (jsonObject.has("getInstrumentationGroups") && SecurityManager.isAuthenticatedAsAdmin()) {
                jsonResponse = BuildJsonObjectsUtil.generateInstrumentationGroupsJson(getBerkeleyTreeMenuService().getGroupedStatistics());
                System.out.println("Got InstrumentationGroups:\n" + jsonResponse);

            }

        } catch (JSONException jsonException) {
            throw new IOException("Unable to process JSON Request", jsonException);
        }

        PrintWriter writer = response.getWriter();
        if (jsonResponse.length() <= 2) {
            jsonResponse = "{}";
        }
        writer.write(jsonResponse);
        response.flushBuffer();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
