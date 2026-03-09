package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.io.*;

public final class applicationex1_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List _jspx_dependants;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.AnnotationProcessor _jsp_annotationprocessor;

  public Object getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_annotationprocessor = (org.apache.AnnotationProcessor) getServletConfig().getServletContext().getAttribute(org.apache.AnnotationProcessor.class.getName());
  }

  public void _jspDestroy() {
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=EUC-KR");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("<meta charset=\"EUC-KR\">\r\n");
      out.write("<title>application 기본 객체 사용하여 자원 읽기</title>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");

	String resourcePath ="/message/notice.txt";
//12_04 밑에 어플리케이션 내부 파일
// /(root)로 시작하면 절대경로 표기법

      out.write("\r\n");
      out.write("자원의 실제 경로:<br>\r\n");
      out.print( application.getRealPath(resourcePath) 
	//웹 어플리케이션 루트로 시작되면 읽고 쓰기 안됨
	//[서버에 배포하면 어디에 저장될지 모름]
	//realPath구하는이유
);
      out.write("\r\n");
      out.write("<br>\r\n");
      out.write("------------------<br>\r\n");
      out.print( resourcePath );
      out.write("의 내용 <br>\r\n");
      out.write("------------------<br>\r\n");

	char[] buff = new char[128];
	int len = -1;
	InputStream is = null;
	InputStreamReader isr = null;
	try{
		is = application.getResourceAsStream(resourcePath);
		//getResourceAsStream()읽기만할거면 파라미터 읽을 수 있는 인풋스트림 돌려줌
		isr = new InputStreamReader(is);
		while( (len = isr.read(buff))!= -1){
			out.print(new String(buff,0,len));
		}
	}catch(IOException ex){
		out.println("익셉션 발생 :"+ex.getMessage() );
	}finally{
		try{
			isr.close();
		}catch(Exception e){}
		try{
			is.close();
		}catch(Exception e){}
	}

      out.write("\r\n");
      out.write("</body>\r\n");
      out.write("</html>");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else log(t.getMessage(), t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
