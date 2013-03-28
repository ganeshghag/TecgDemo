package com.ghag.rnd.google.appengine.tecgdemo;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.Query;

@SuppressWarnings("serial")
public class TecgDemoServlet extends HttpServlet {
	
	static String[] colnames = {"First Name","Last Name","Address","Email Address", "Voice Contact"};
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		resp.setContentType("text/html");
		String screencontent = "";
		screencontent += "<table width='100%' border='1'>";
		screencontent += "<th>";
		
		
		for(int i = 0 ; i < 5 ; i++)
			screencontent += "<td>" + colnames[i] +"</td>";
		screencontent += "</th>";
		
		List<UserProfile> users = getAllUsers();
		for(int i = 0 ; i < users.size() ; i++){
			screencontent += "<tr>";
			UserProfile user = users.get(i);
			
			String dispdate = new SimpleDateFormat("dd-MMM-yy HH:mm:ss",Locale.getDefault()).format(new Date(user.getUserId()));
			screencontent += "<td>" + dispdate +"</td>";
			screencontent += "<td>" + user.getFirstName() +"</td>";
			screencontent += "<td>" + user.getLastName() +"</td>";
			screencontent += "<td>" + user.getAddress() +"</td>";
			screencontent += "<td>" + user.getEmail() +"</td>";
			screencontent += "<td>" + user.getVoiceContact() +"</td>";
			screencontent += "</tr>";
		}
		screencontent += "</table>";
		
		screencontent += "<p></p>";
		
		screencontent += "<form method='POST'>";
		screencontent += "<table>";
		screencontent += "<tr><td>First Name: </td><td><input type='text' name='FIRST_NAME'/> </td></tr>";
		screencontent += "<tr><td>Last Name: </td><td><input type='text' name='LAST_NAME'/> </td></tr>";
		screencontent += "<tr><td>Address: </td><td><input type='text' name='ADDRESS'/> </td></tr>";
		screencontent += "<tr><td>Email: </td><td><input type='text' name='EMAIL'/> </td></tr>";
		screencontent += "<tr><td>Voice Contact: </td><td><input type='text' name='VOICE_CONTACT'/> </td></tr>";
		screencontent += "<tr><td> <input type='submit'/></td></tr>";
		screencontent += "</table>";
		screencontent += "</form>";
		
		resp.getWriter().println(screencontent);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		UserProfile user = new UserProfile(req);
		saveUser(user);
		System.out.println("new user added "+user);
		resp.sendRedirect("tecgdemo");
	}
	
	private void saveUser(UserProfile user){
		Key userKey = KeyFactory.createKey("tecgdemo", "UsersKey");
        Entity userEntity = new Entity("UserProfile", userKey);
        
        userEntity.setProperty("userId", user.getUserId());
        userEntity.setProperty("firstName", user.getFirstName());
        userEntity.setProperty("lastName", user.getLastName());
        userEntity.setProperty("address", user.getAddress());
        userEntity.setProperty("email", user.getEmail());
        userEntity.setProperty("voiceContact", user.getVoiceContact());

        DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
        datastore.put(userEntity);
	}
	
	private List<UserProfile> getAllUsers(){
		
		List<UserProfile> users = new ArrayList<UserProfile>();
		
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
	    Key userKey = KeyFactory.createKey("tecgdemo", "UsersKey");
	    Query query = new Query("UserProfile", userKey);
	    List<Entity> entities = datastore.prepare(query).asList(FetchOptions.Builder.withLimit(20));
		for (Iterator iterator = entities.iterator(); iterator.hasNext();) {
			Entity entity = (Entity) iterator.next();
			UserProfile user = new UserProfile();
			user.setUserId((Long)entity.getProperty("userId"));
			user.setFirstName((String)entity.getProperty("firstName"));
			user.setLastName((String)entity.getProperty("lastName"));
			user.setAddress((String)entity.getProperty("address"));
			user.setEmail((String)entity.getProperty("email"));
			user.setVoiceContact((String)entity.getProperty("voiceContact"));
			
			users.add(user);
			
		}
		
		return users;
		
	}
	
	
}
