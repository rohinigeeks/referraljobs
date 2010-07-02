package com.appspot.rfrrljbs;

import java.io.IOException;
import javax.jdo.PersistenceManager;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

import com.google.gdata.client.authn.oauth.*;

import com.google.gdata.client.*;
import com.google.gdata.client.contacts.*;
import com.google.gdata.data.*;
import com.google.gdata.data.contacts.*;
import com.google.gdata.data.contacts.ContactEntry;
import com.google.gdata.data.contacts.ContactFeed;
import com.google.gdata.data.extensions.*;
import com.google.gdata.util.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.appspot.rfrrljbs.UserContacts;
import com.appspot.rfrrljbs.PMF;

//[AM01]
//Desc : 	The collections library is shipped along with the gdata java client library. 
//			Please add java/deps/*.jar in the gdata client library to your classpath.
//			http://code.google.com/p/gdata-java-client/issues/detail?id=124#c5
//[AM01]

@SuppressWarnings({ "serial", "unused" })
public class GetContactsServlet extends HttpServlet {
	
	private String CALLBACK_URL = "http://rfrrljbs.appspot.com/GetContacts";
	private String CONTACT_FEED_SCOPE = "http://www.google.com/m8/feeds/";	
	String CONSUMER_KEY = "rfrrljbs.appspot.com";
	String CONSUMER_SECRET = "FGJmciXBXH+qMzg0iCbsEeKt";
	String ACCESS_TOKEN = null;
	String TOKEN_SECRET = null;
    Boolean isPostback = false;
    
    public void doPost(HttpServletRequest req, HttpServletResponse resp)   throws IOException {
	
		/*if(req.getQueryString()!=null) {
		String qs = req.getQueryString();
		if (!qs.isEmpty()) {
			CharSequence s = "OAUTH=1";					
				
			if(qs.contains(s))
				isPostback = true;
			else
				isPostback = false;
		}    		
		}
		
		if(isPostback == false) {*/
		
		//OAUTH DANCE HMAC-SHA1 START
		
		//Authenticate application to receive user private data
		//http://code.google.com/apis/gdata/docs/auth/oauth.html#Examples
		
		// 1. Fetching a request token
		GoogleOAuthParameters oauthParameters = new GoogleOAuthParameters();
		oauthParameters.setOAuthConsumerKey(CONSUMER_KEY);
		oauthParameters.setOAuthConsumerSecret(CONSUMER_SECRET);
		oauthParameters.setScope(CONTACT_FEED_SCOPE);
		oauthParameters.setOAuthCallback(CALLBACK_URL);
		
		
		GoogleOAuthHelper oauthHelper = new GoogleOAuthHelper(new OAuthHmacSha1Signer());
		try {
			
			oauthHelper.getUnauthorizedRequestToken(oauthParameters);
		} catch (OAuthException e) {			
			e.printStackTrace();
			resp.sendRedirect("/");
			resp.getWriter().println("getUnauthorizedRequestToken");
		}
		
		// 2. Authorizing a request token
		String approvalPageUrl = oauthHelper.createUserAuthorizationUrl(oauthParameters);
		resp.sendRedirect(approvalPageUrl);
		/*
		}
		else {
		try {
			doGet(req,resp);
		} catch (ServletException se) {
			se.printStackTrace();
			resp.getWriter().println("doGet");
		}
		}//Is Post back from OAUTH user auth page 
		
		resp.sendRedirect("/UserContacts.jsp");*/
} 
    
    
   
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	
    	try { //resp.getWriter().println(req.getQueryString());
    		
    		
			UserService userService = UserServiceFactory.getUserService();
			User user = userService.getCurrentUser();
			
			//GoogleOAuthParameters oauthParameters = new GoogleOAuthParameters();
			//oauthParameters.setOAuthConsumerKey(CONSUMER_KEY);
			//oauthParameters.setOAuthConsumerSecret(CONSUMER_SECRET);

			//GoogleOAuthHelper oauthHelper = new GoogleOAuthHelper(new OAuthHmacSha1Signer());
			//oauthHelper.getOAuthParametersFromCallback(req.getQueryString(), oauthParameters);
			
			   		
			
			// 3. Upgrading to an access token
			/*
			try {
				ACCESS_TOKEN = oauthHelper.getAccessToken(oauthParameters);
			} catch (OAuthException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				//resp.sendRedirect("/");
				resp.getWriter().println("getAccessToken");
			}    		

			TOKEN_SECRET = oauthParameters.getOAuthTokenSecret();
			*/
			
			String qs = req.getQueryString();
			
			if (!qs.isEmpty()) {
    			
    			String[] queryParams = qs.substring(qs.indexOf("?") + 1).split("&");    			
    			String[] accessToken = queryParams[0].split("=");
    			String[] tokenSecret = queryParams[1].split("=");
    			ACCESS_TOKEN = accessToken[1];
    			TOKEN_SECRET = tokenSecret[1];
    		} 
    		
    		//resp.getWriter().println("ACCESS_TOKEN:" + ACCESS_TOKEN + "<br>" + "TOKEN_SECRET:" + TOKEN_SECRET) ;
    		
			
			// 4. Using an access token
			GoogleOAuthParameters oauthParameters2 = new GoogleOAuthParameters();
			  oauthParameters2.setOAuthConsumerKey(CONSUMER_KEY);
			  oauthParameters2.setOAuthConsumerSecret(CONSUMER_SECRET);
			  oauthParameters2.setOAuthToken(ACCESS_TOKEN);
			  oauthParameters2.setOAuthTokenSecret(TOKEN_SECRET);

			//OAUTH DANCE HMAC-SHA1  END
			
            //[AM01]
			ContactsService client = new ContactsService("rfrrljbs.appspot.com");
			//[AM01]
			
			try {
				client.setOAuthCredentials(oauthParameters2, new OAuthHmacSha1Signer());
			} catch (OAuthException e) {				
				e.printStackTrace();
				//resp.sendRedirect("/");
				resp.getWriter().println("setOAuthCredentials");
			}
			
			URL feedUrl = new URL(CONTACT_FEED_SCOPE + "contacts/" + user.getEmail() + "/full");
			ContactFeed resultFeed=null;
			try {
				resultFeed = client.getFeed(feedUrl, ContactFeed.class);
			} catch (ServiceException e) {				
				e.printStackTrace();
				//resp.sendRedirect("/");
				resp.getWriter().println("getFeed");
			}
			if(resultFeed !=null){
				
				UserContacts userContacts = new UserContacts(user);
				
				for (int i = 0; i < resultFeed.getEntries().size(); i++) {    		        
					ContactEntry entry = resultFeed.getEntries().get(i);
					
					List<com.google.appengine.api.datastore.Email> userContactsEmail = new ArrayList<com.google.appengine.api.datastore.Email>();
					
					for (Email email : entry.getEmailAddresses()) {
						com.google.appengine.api.datastore.Email gaeDSEmail = new com.google.appengine.api.datastore.Email(email.getAddress());
						userContactsEmail.add(gaeDSEmail);
					}
					userContacts.setUserContacts(userContactsEmail);    				
					
					//Persist to GAE DataStore
			        PersistenceManager pm = PMF.get().getPersistenceManager();
			        try {
			            pm.makePersistent(userContacts);
			        } finally {
			            pm.close();
			        }
			    } 
			}
			resp.sendRedirect("/UserContacts.jsp");
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			resp.getWriter().println("<h1>JHAM HAS A NEW NAME</h1>");
			resp.getWriter().println(e.toString());
		}
	}
}
		
	 
   
    
