<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="javax.jdo.PersistenceManager" %>
<%@ page import="com.google.appengine.api.users.User" %>
<%@ page import="com.google.appengine.api.users.UserService" %>
<%@ page import="com.google.appengine.api.users.UserServiceFactory" %>
<%@ page import="com.appspot.rfrrljbs.UserContacts" %>
<%@ page import="com.appspot.rfrrljbs.PMF" %>
<%@ page import="javax.jdo.Query" %>
<%@ page import="com.google.appengine.api.datastore.Email" %>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset=utf-8>
<meta name="google-site-verification" content="6_gVMu2uh3xUql1H3uIs4t9jzv9EhUSPcAsnweWdQhM" />
<title>Just Referral Jobs</title>
<link rel="stylesheet" href="/css/main.css" type="text/css" />

</head>
<body id="index" class="home">


<header id="banner" class="body">

<h1><a href="#">Referral Jobs <strong>Recommended by your social graph!</strong></a></h1>

<nav><ul>
        <li class="active"><a href="#">Home</a></li>
        <li><a href="#">What is ReferralJobs?</a></li>
        <li><a href="#">Blog</a></li>
        <li><a href="#">Contact</a></li>
</ul></nav>

</header>

<aside id="featured" class="body">
<article>
	<section>
		<%
		    UserService userService = UserServiceFactory.getUserService();
		    User user = userService.getCurrentUser();
		    if (user != null) {
		%>
		<p>Hello, <%= user.getNickname() %>! (You can
		<a href="<%= userService.createLogoutURL(request.getRequestURI()) %>">sign out</a>.)</p>
		<%
		    } else {
		%>
		<p>Hello!
		<a href="<%= userService.createLoginURL(request.getRequestURI()) %>">Sign in</a>
		to include your name with greetings you post.</p>
		<%
		    }
		%>			
	</section>
	<hgroup>
		<h2>College friend, former colleague, relative may want to work with you. </h2>
		<h3><a href="#"></a></h3>
	</hgroup>

	<p>
	 <ul>
	 <li>Stay informed about referral jobs within your social graph</li>
	 <li>Work with friends and aquaintances who recommend you</li>
	 <li>Post referral jobs to your friends and contacts</li>
	 </ul>
	 </p>


</article>
</aside>
<%
    PersistenceManager pm = PMF.get().getPersistenceManager();
   // Query query = pm.newQuery("select from UserContacts " +
     //       "where user == userParam " +
       //    "parameters String userParam ");
   Query query = pm.newQuery(UserContacts.class);
   query.setFilter("user.equals(userParam)");
   query.declareParameters("User userParam");
           
         
    List<UserContacts> results = (List<UserContacts>) query.execute(user);
    if (results.iterator().hasNext()) {
    	for (UserContacts uc : results) {
    
        
%>
        	<section id="content" class="body">

        	<ol id="posts-list" class="hfeed">
        	<% for (com.google.appengine.api.datastore.Email email : uc.getUserContacts()) {%>
        		<li>
        	 		<%email.getEmail();%>
        	 	</li>
        	<% } %>
        	</ol>
        </section>
<%
    	}
    }
    pm.close();
%>



<footer id="contentinfo" class="body">
	<address id="about" class="vcard body">
		<span class="primary">
			<strong><a href="#" class="fn url">Referral Jobs</a></strong>
			<span class="role">Recommended by your social graph!</span>
		</span>
		<span class="bio">Referral Jobs is a website for recommending your professional network to your organization. Organization look for competent and familiar people. Bring your social graph closer to work. Post, Search and Apply for jobs recommended for you.</span>
	</address>
	<p>2009-2019 <a href="http://referraljobs.appspot.com">Referral Jobs</a>.</p>
</footer>
</body>
</html>