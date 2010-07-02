<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.google.appengine.api.users.User" %>
<%@ page import="com.google.appengine.api.users.UserService" %>
<%@ page import="com.google.appengine.api.users.UserServiceFactory" %>
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
		<form action="/GetContacts" method="post">    	
    		<div><input type="submit" value="My Contacts" /></div>
  		</form>		
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

<section id="content" class="body">
	<ol id="posts-list" class="hfeed">
		<li>
	 	<article class="hentry">
		<header>
			<h2 class="entry-title"><a href="#" rel="bookmark" title="Permalink to this POST TITLE">This be the title</a></h2>
		</header>
		<footer class="post-info">
			 <abbr class="published" title="2005-10-10T14:07:00-07:00"><!-- YYYYMMDDThh:mm:ss+ZZZZ -->
				10th October 2005
			</abbr>
			<address class="vcard author">
				By <a class="url fn" href="#">Enrique Ramirez</a>
			</address>
		</footer><!-- /.post-info -->
		<div class="entry-content">
				<p>
Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque venenatis nunc vitae libero iaculis elementum. Nullam et justo <a href="#">non sapien</a> dapibus blandit nec et leo. Ut ut malesuada tellus.
	 	 	 	</p>
		</div>
		</article>
	 	</li>
		<li>
	 	<article class="hentry">
		<header>
			<h2 class="entry-title"><a href="#" rel="bookmark" title="Permalink to this POST TITLE">This be the title</a></h2>
		</header>
		<footer class="post-info">
			 <abbr class="published" title="2005-10-10T14:07:00-07:00"><!-- YYYYMMDDThh:mm:ss+ZZZZ -->
				10th October 2005
			</abbr>
			<address class="vcard author">
				By <a class="url fn" href="#">Enrique Ramirez</a>
			</address>
		</footer><!-- /.post-info -->
		<div class="entry-content">
				<p>
					Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque venenatis nunc vitae libero iaculis elementum. Nullam et justo <a href="#">non sapien</a> dapibus blandit nec et leo. Ut ut malesuada tellus.
	 	 	 	</p>
		</div><!-- /.entry-content -->
		</article>
	 	</li>
	 	 <li>
	 	<article class="hentry">
		<header>
			<h2 class="entry-title"><a href="#" rel="bookmark" title="Permalink to this POST TITLE">This be the title</a></h2>
		</header>
		<footer class="post-info">
			 <abbr class="published" title="2005-10-10T14:07:00-07:00"><!-- YYYYMMDDThh:mm:ss+ZZZZ -->
				10th October 2005
			</abbr>
			<address class="vcard author">
				By <a class="url fn" href="#">Enrique Ramirez</a>
			</address>
		</footer><!-- /.post-info -->
		<div class="entry-content">
				<p>
					Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque venenatis nunc vitae libero iaculis elementum. Nullam et justo <a href="#">non sapien</a> dapibus blandit nec et leo. Ut ut malesuada tellus.
	 	 	 	</p>
		</div><!-- /.entry-content -->
		</article>
	 	</li>
	</ol>
</section>

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