import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.jaaga.learning.*;

public class LearnServlet extends HttpServlet implements InteractionInterface {
	Learning learning;
	HttpServletResponse currentResponse;
	PrintWriter out;
	ArrayList<String> chatList = new ArrayList<>();
	String answer = null;

	public LearnServlet() {
		learning = new Learning(this, new ChatBot(), new DB());
	}

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws IOException, ServletException {
		
		currentResponse = response;
        out = response.getWriter();

		String answer = request.getParameter("answer");
		out.println("<style>body {font: normal 10px Verdana, Arial, sans-serif;}" +  
							".chat-me {border-bottom-right-radius: 30px;" +
							"width: 150px; padding-left: 100px" +
							"height: 20px; background-color: #ffdd00;}" +
					"</style>");	
		
        out.println("<head>");
        out.println("<title>LEARN SERVLET</title>");
        out.println("</head>");
		out.println("<body>");
		out.println("<h1>Welcome to Jaaga Learning!</h1><p>");


		
		if (answer == null) {
			learning.start();
			inputButton();
			learning.onResponse(answer);
			chatList.add("<p class=" + "\"chat-me\"" + "> Me: " + answer + "</p>");
		} else {
			chatList.add("<p class=" + "\"chat-me\"" + "> Me: " + answer + "</p>");
		printList(chatList);
		learning.onResponse(answer);
		inputButton();
		}
		out.println("</body>");
    }
	
	public void send(ChatItem item) {
		try {
			PrintWriter out = currentResponse.getWriter();
			if (answer != null){
				chatList.add("<p class=" + "\"chat-me\"" + "> Me: " + answer + "</p>");
			} else {
				out.println("Srinivas: "+ item.getMessage()+"<p>");
			}
			chatList.add("Srinivas: "+ item.getMessage()+"<p>");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void inputButton(){
		out.print("<form action=\"");
        out.print("LearnServlet\" ");
        out.println("<br>");
        out.println("<input type=text size=20 name=answer placeholder=" + "\"Type a answer/command\"" + ">");
        out.println("<br>");
        out.println("</form> <p>");		
	}

	public void printList(ArrayList<String> input){
		if (!input.isEmpty()){
			for (int i = 0; i < input.size(); i++){
				out.println(input.get(i));
			}
		} else {out.println("<p class=" + "\"chat-me\"" + "> Empty " + "</p>");}
	}
}



