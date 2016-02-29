import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.json.*;

import in.jaaga.learning.*;

public class LearnServlet extends HttpServlet implements InteractionInterface {
	Learning learning;
	HttpServletResponse currentResponse;
	PrintWriter out;
	ArrayList<String> chatList = new ArrayList<>();
	String answer = null;
	String message;
	Session session = new Session();

	public LearnServlet() {
		learning = new Learning(this, session, new ChatBot(), new DB());
	}
	//(InteractionInterface minteractionInterface, Session session, ChatBot chatBot, DB db)

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {	
		fixHeaders(response);
		currentResponse = response;
        out = response.getWriter();
        String output = "";
		String answer = request.getParameter("answer");
		String action = request.getParameter("action");

		//out.println(createJson("result", ""));

		if (action == null) {action = "";}
			switch(action) {
				case "getName": 
					session.setName("matheo");	
					output = learning.getSessionName();
					break;
				case "setName":
					String name = request.getParameter("username");
					session.setName(name);	
				default: break; 
			}
		//initHTML();
		out.println("{\"response\":\"" + output + "\"}");
		//learning.start();
		//inputButton();


		/*
		if (answer != learning.getSessionName()) {
			chatList.clear();
			learning = new Learning(this, new Session(), new ChatBot(), new DB());
			//learning.onResponse(answer);
			//addLine(answer);	
		} else {*/
			/*
			addLine(answer);
			printList(chatList);
			learning.onResponse(answer);
			inputButton();*/
		//}
		//out.println("</body>");
    }
	
	public void send(ChatItem item) {
		try {
			PrintWriter out = currentResponse.getWriter();
			if (answer != null){
				chatList.add("<p class=" + "\"chat-me\"" + "> Me: " + answer + "</p>");
			} else {
				if (item.getMessage().contains("*")){
					String imageName = item.getMessage().substring(item.getMessage().indexOf("*") + 1);
					message = item.getMessage().substring(0, item.getMessage().indexOf("*"));
					out.println("<img src=\"app\\src\\main\\res\\drawable\\" + imageName.concat(".png") + "\" alt=\"Smiley face\">");
				} else {
					message = item.getMessage();
				}
				out.println("Srinivas: " + message + "<p>");
			}
			chatList.add("Srinivas: "+ message +"<p>");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void initHTML() {
		out.println("<style>body {font: normal 10px Verdana, Arial, sans-serif;}" +  
							".chat-me {border-bottom-right-radius: 30px;" +
							"width: 150px; padding-left: 100px" +
							"height: 20px; background-color: #ffdd00;}" +
					"</style>");	
		
        out.println("<head>");
        out.println("<title>Shrini</title>");
        out.println("</head>");
		out.println("<body>");
		out.println("<h1>Welcome to Shrini's Home.</h1><p>");
	}	


	public void inputButton(){
		out.print("<form action=\"");
        out.print("LearnServlet\" ");
        out.println("<br>");
        out.println("<input type=text size=20 name=answer placeholder=" + "\"Type a answer/command\"" + ">");
        out.println("<br>");
        out.println("</form> <p>");		
	}

	public void addLine(String answer) {
		chatList.add("<p class=" + "\"chat-me\"" + "> Me: " + answer + "</p>");
	}

	public void printList(ArrayList<String> input){
		if (!input.isEmpty()){
			for (int i = 0; i < input.size(); i++){
				out.println(input.get(i));
			}
		} else {out.println("<p class=" + "\"chat-me\"" + "> Empty " + "</p>");}
	}

	private void fixHeaders(HttpServletResponse response) {
	    response.addHeader("Access-Control-Allow-Origin", "*");
	    response.addHeader("Access-Control-Allow-Methods", "GET, PUT, POST, OPTIONS, DELETE");
	    response.addHeader("Access-Control-Allow-Headers", "Content-Type");
	    response.addHeader("Access-Control-Max-Age", "86400");
	}

/*
	public String createJson(String result, String error) {
		Boolean success = (error == ""); 

		JsonObject json = Json.createObjectBuilder()
     	.add("success", success)
     	.add("result", result)
     	.add("error", error)
     	.build();
     	
  		return json.toString();
	}
*/
}