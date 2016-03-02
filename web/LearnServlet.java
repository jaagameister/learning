import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.json.*;

import in.jaaga.learning.*;
import in.jaaga.learning.missions.*;

public class LearnServlet extends HttpServlet implements InteractionInterface {
	
	Learning learning;
	HttpServletResponse currentResponse;
	PrintWriter out;
	ArrayList<String> chatList = new ArrayList<>();
	String answer = null;
	String message;
	Session session = new Session();

	public LearnServlet() {
		Session.setDevice("CLI");
		
		MissionLibrary ml = new MissionLibrary();
		ml.addMission("math", new MathMission());
		ml.addMission("easy", new Easy());
		ml.addMission("negative", new NegativeNumbers());

		LearningContext learningContext = new LearningContext(this, session, new ChatBot(session),
		        ml, new DB());
		learning = new Learning(learningContext);

		learning.start();
		
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
					//output = learning.getSessionName();
					break;
				case "setName":
					//String name = request.getParameter("username");
					//session.setName(name);
				case "getResponse":
				//	String input = request.getParameter("input");
					output = "hp";//learning.onResponse(input);
					return;
				//	addLine(answer);				
				default: break; 
			}
		if (true){//answer != learningContext.getSessionName()) {
			chatList.clear();
//			learning = new Learning(this, new Session(), new ChatBot(), new DB());
			//learning.onResponse(answer);
			//addLine(answer);	
		} else {
			
			addLine(answer);
			//printList(chatList learning.onResponse(answer);
			
		}
		out.println(output);
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