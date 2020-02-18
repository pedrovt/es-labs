Some quick notes on projects:


gs-consuming-rest
	
	Adapted version of complete project from 
	GETTING STARTED Consuming a RESTful Web Service
	https://spring.io/guides/gs/consuming-rest/
	
		Spring boot application with a scheduleTasks that calls REST source http://gturnquist-quoters.cfapps.io/api/random. for quotes
		
		Uses RestTemplate a @bean for retrieving the REST contentes
	
	
TestJersey

	based on http://www.vogella.com/tutorials/REST/article.html 
	The example uses as REST source  http://gturnquist-quoters.cfapps.io/api/random.
	
	Uses a standard Jersey call
	
	

aWebJerseyClient

	Using a the same quotes REST it has the following components
	@Startup

		AutomaticTimerBean as		@Singleton
			has a time that calls "printDate" periodically and counts the times it is called
			stores a quoters
		
		ARestClient as @Singleton
			wraps call to jersey as in TestJersey example. returns quotes through "read()" method
			
		QuotesServlet as  servlet
			
			that shows a quote and stores it in AutomaticTimerBean singleton
			
		TimerServlet as servlet
		
			that prints out the contents of AutomaticTimerBean singleton i.e. counter and last quote stored
	
