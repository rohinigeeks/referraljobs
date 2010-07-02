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

@SuppressWarnings({ "serial", "unused" })
public class GetContactsServlet extends HttpServlet {
	
	private String CALLBACK_URL = "http://rfrrljbs.appspot.com/GetContacts?OAUTH=1";
	private String CONTACT_FEED_SCOPE = "http://www.google.com/m8/feeds/";	
	String CONSUMER_KEY = "rfrrljbs.appspot.com";
	String CONSUMER_SECRET = "FGJmciXBXH+qMzg0iCbsEeKt";
    Boolean isPostback = false;
    
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
                throws IOException {
    	    	
    	if(req.getQueryString()!=null) {
    		String qs = req.getQueryString();
    		if (!qs.isEmpty()) {
    			CharSequence s = "OAUTH=1";					
					
    			if(qs.contains(s))
    				isPostback = true;
    			else
    				isPostback = false;
    		}    		
    	}
    	
    	if(isPostback == false) {
    		
	        //OAUTH DANCE HMAC-SHA1 START
	        
	        //Authenticate application to receive user private data
	        //http://code.google.com/apis/gdata/docs/auth/oauth.html#Examples
	        
	        // 1. Fetching a request token
    		 //#DEBUG
	        resp.setContentType("text/plain");
            resp.getWriter().println("Instantiating GoogleOAuthParameters");
	        //#DEBUG
	        GoogleOAuthParameters oauthParameters = new GoogleOAuthParameters();
	        oauthParameters.setOAuthConsumerKey(CONSUMER_KEY);
	        oauthParameters.setOAuthConsumerSecret(CONSUMER_SECRET);
	        oauthParameters.setScope(CONTACT_FEED_SCOPE);
	        oauthParameters.setOAuthCallback(CALLBACK_URL);
	        
	        //#DEBUG	        
            resp.getWriter().println("Instantiating oauthHelper");
	        //#DEBUG
            
            GoogleOAuthHelper oauthHelper = new GoogleOAuthHelper(new OAuthHmacSha1Signer());
	        try {
	        	//#DEBUG
		        
	            resp.getWriter().println("Calling getUnauthorizedRequestToken");
		        //#DEBUG
				oauthHelper.getUnauthorizedRequestToken(oauthParameters);
			} catch (OAuthException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				resp.sendRedirect("/");
				resp.getWriter().println("getUnauthorizedRequestToken");
			}
		
			// 2. Authorizing a request token
			String approvalPageUrl = oauthHelper.createUserAuthorizationUrl(oauthParameters);
			resp.sendRedirect(approvalPageUrl);
    	}
    	else {
    		try {
				doGet(req,resp);
			} catch (ServletException se) {
				se.printStackTrace();
				resp.getWriter().println("doGet");
			}
    	}//Is Post back from OAUTH user auth page 
    	
        resp.sendRedirect("/UserContacts.jsp");
    }
    
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	
    	UserService userService = UserServiceFactory.getUserService();
        User user = userService.getCurrentUser();
        
		GoogleOAuthParameters oauthParameters = new GoogleOAuthParameters();
		oauthParameters.setOAuthConsumerKey(CONSUMER_KEY);
		oauthParameters.setOAuthConsumerSecret(CONSUMER_SECRET);

		GoogleOAuthHelper oauthHelper = new GoogleOAuthHelper(new OAuthHmacSha1Signer());
		oauthHelper.getOAuthParametersFromCallback(req.getQueryString(), oauthParameters);
		
		// 3. Upgrading to an access token
		String ACCESS_TOKEN=null;
		try {
			ACCESS_TOKEN = oauthHelper.getAccessToken(oauthParameters);
		} catch (OAuthException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			//resp.sendRedirect("/");
			resp.getWriter().println("getAccessToken");
		}    		

		String TOKEN_SECRET = oauthParameters.getOAuthTokenSecret();
		
		// 4. Using an access token
		GoogleOAuthParameters oauthParameters2 = new GoogleOAuthParameters();
		  oauthParameters2.setOAuthConsumerKey(CONSUMER_KEY);
		  oauthParameters2.setOAuthConsumerSecret(CONSUMER_SECRET);
		  oauthParameters2.setOAuthToken(ACCESS_TOKEN);
		  oauthParameters2.setOAuthTokenSecret(TOKEN_SECRET);
	
		//OAUTH DANCE HMAC-SHA1  END
		  
		ContactsService client = new ContactsService("referjobs-rfrrljbs-1");
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
	}
}
		
	 
   
    
