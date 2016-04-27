package com.hopenshare.controller;

import java.util.Map;

import org.springframework.extensions.surf.FrameworkUtil;
import org.springframework.extensions.surf.RequestContext;
import org.springframework.extensions.surf.exception.ConnectorServiceException;
import org.springframework.extensions.surf.exception.UserFactoryException;
import org.springframework.extensions.surf.support.ThreadLocalRequestContext;
import org.springframework.extensions.webscripts.Cache;
import org.springframework.extensions.webscripts.DeclarativeWebScript;
import org.springframework.extensions.webscripts.Status;
import org.springframework.extensions.webscripts.WebScriptRequest;
import org.springframework.extensions.webscripts.connector.Connector;
import org.springframework.extensions.webscripts.connector.ConnectorContext;
import org.springframework.extensions.webscripts.connector.HttpMethod;
import org.springframework.extensions.webscripts.connector.Response;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

public class CompletedTask extends DeclarativeWebScript {
	@Override
	protected Map<String, Object> executeImpl(WebScriptRequest req, Status status, Cache cache) {
		RequestContext context = (RequestContext) ThreadLocalRequestContext.getRequestContext();
		Connector conn;
		try {
			//initialize connection
			conn = FrameworkUtil.getConnector(context, "alfresco");
			ConnectorContext c = new ConnectorContext(HttpMethod.GET);
			c.setContentType("application/json");
			//it's for connecting to the url http://localhost:8080/alfresco/service/api/task-instances
			Response res = conn.call("/s/api/task-instances", c, null);
			if (Status.STATUS_OK == res.getStatus().getCode()) {
				System.out.println(res.getResponse());
				//here I'm using jackson to read from url, I tried my code with some file I took from the url above
				//and paste it in file called file1.json
				//and I add the dependency for json response
				try {
					ObjectMapper mapper = new ObjectMapper();
					//String jsonString = mapper.readTree(new File("K:\\alfresco code\\json-reader\\file1.json"))
					JsonNode jsonString = mapper.readTree(res.getResponse());
					JsonNode root = jsonString.path("data");
					if (root.isMissingNode()) {
			 			System.out.println("it's wrong");
			 		}else
			 		{
			 			int i=0;
			 			for (JsonNode node : root) {
			 				String title = node.path("title").asText();
			 				String dueDate = node.path("dueDate").asText();
			 				String message = node.path("message").asText();
			 				JsonNode Initiator = root.path("initiator");
			 				System.out.println(i+" title : " + title+",message : " + message+",dueDate : "+dueDate);
			 			}
			 		}
			 	} catch (JsonParseException e) { e.printStackTrace(); }
			      catch (JsonMappingException e) { e.printStackTrace(); }
			}
		} catch(Exception e){
			
		}
		return null;
	}
}
