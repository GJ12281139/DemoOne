import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 打开浏览器输入：localhost:8080/JavaWeb/HelloWorld即可查看servlet运行状态了.
 */
public class HelloWorld extends HttpServlet {
    private String message;

    @Override
    public void init(){
        message = "Hello, this message is from servlet.";
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        PrintWriter out = response.getWriter();
        out.println("<h1>" + message + "</h1>");
    }

    @Override
    public void destroy(){
        super.destroy();
    }
}
