SRC="/home/freeman/projects/math-app/learning"
LEARN_SRC=$SRC"/app/src/main/java/in/jaaga/learning"
SERVLET_SRC=$SRC"/web/LearnServlet.java"
SERVLET_LIB="/home/freeman/apache-tomcat-8.0.30/lib/servlet-api.jar"
SERVLET_DESTINATION="/home/freeman/apache-tomcat-8.0.30/webapps/examples"

javac -d $SERVLET_DESTINATION -cp $SERVLET_LIB:$SERVLET_DESTINATION $LEARN_SRC/problems/*.java $LEARN_SRC/missions/*.java $LEARN_SRC/cli/*.java $LEARN_SRC/*.java $SERVLET_SRC

