package com.hopenshare.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.extensions.surf.FrameworkUtil;
import org.springframework.extensions.surf.RequestContext;
import org.springframework.extensions.surf.support.ThreadLocalRequestContext;
import org.springframework.extensions.webscripts.Cache;
import org.springframework.extensions.webscripts.DeclarativeWebScript;
import org.springframework.extensions.webscripts.Status;
import org.springframework.extensions.webscripts.WebScriptRequest;
import org.springframework.extensions.webscripts.connector.Connector;
import org.springframework.extensions.webscripts.connector.ConnectorContext;
import org.springframework.extensions.webscripts.connector.HttpMethod;
import org.springframework.extensions.webscripts.connector.Response;

public class CompletedTask extends DeclarativeWebScript {
	String title;
	String dueDate;
	String message;

	@SuppressWarnings("deprecation")
	@Override
	protected Map<String, Object> executeImpl(WebScriptRequest req, Status status, Cache cache) {
		RequestContext context = (RequestContext) ThreadLocalRequestContext.getRequestContext();
		Connector conn;
		List<Map<String, Object>> tasksList = new ArrayList<Map<String, Object>>();
		try {
			// initialize connection
			conn = FrameworkUtil.getConnector(context, "alfresco");
			ConnectorContext c = new ConnectorContext(HttpMethod.GET);
			c.setContentType("application/json");
			// it's for connecting to the url
			// http://localhost:8080/alfresco/service/api/task-instances
			Response res = conn.call("/api/task-instances", c, null);
			System.out.println(res.getStatus().getCode());
			if (Status.STATUS_OK == res.getStatus().getCode()) {
				System.out.println(res.getResponse());
				JSONObject globalResponse = new JSONObject(res.getResponse());
				JSONArray tasksArray = globalResponse.getJSONArray("data");
				
				for(int i=0; i< tasksArray.length(); i++){
					Map<String, Object> taskModel = new HashMap<String, Object>();
					JSONObject taskObject = tasksArray.getJSONObject(i);
					//TODO charge the task model with the desired properties
					taskModel.put("id", taskObject.getString("id"));
					taskModel.put("url", taskObject.getString("url"));
					taskModel.put("name", taskObject.getString("name"));
					taskModel.put("title", taskObject.getString("title"));
					taskModel.put("state", taskObject.getString("state"));					
					tasksList.add(taskModel);
				}

			} else {
				System.out.println("Not fetched " + res.getStatus().getCode());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Map<String, Object> fmModel = new HashMap<String, Object>();
		fmModel.put("tasks", tasksList);
		//fmModel.put("fromJava", "HelloFromJava");
		return fmModel;

	}
}
