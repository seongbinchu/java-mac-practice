package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.io.*;

public final class list_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("    \r\n");
 request.setCharacterEncoding("EUC-KR"); 
      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("<meta charset=\"EUC-KR\">\r\n");
      out.write("<title>list.jsp</title>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("\r\n");

	char[] buff = new char[128];
	int len = -1;
	String filePath = "C:\\Users\\GGG\\Desktop\\eclipse\\web\\12_04\\WebContent\\data\\comments.txt";
	FileInputStream fis = null;
	InputStreamReader isr = null;
	try{
		fis = new FileInputStream(filePath);
		isr = new InputStreamReader(fis);
		while( (len = isr.read(buff))!=-1){
			out.print(new String(buff,0,len));
		}
	}catch(IOException ex){
		out.println("익셉션 발생 :" +ex.getMessage());
	}finally{
		try{
			isr.close();
		}catch(Exception e){}
		try{
			fis.close();
		}catch(Exception e){}
	}

      out.write("\r\n");
      out.write("\r\n");

	char[] temp = new char[128];
	int len2 = -1;
	String filePath2 = "C:\\Users\\GGG\\Desktop\\eclipse\\web\\12_04\\WebContent\\data\\comments.txt";
	FileOutputStream fos = null;
	OutputStreamWriter osw = null;
	try{
		fos = new FileOutputStream(filePath2);
		osw = new OutputStreamWriter(fos);
		while( (len2 = isr.read(buff))!=-1){
			out.print(new String(buff,0,len));
		}
	}catch(IOException ex){
		out.println("익셉션 발생 :" +ex.getMessage());
	}finally{
		try{
			isr.close();
		}catch(Exception e){}
		try{
			fis.close();
		}catch(Exception e){}
	}

      out.write("\r\n");
      out.write("\r\n");
      out.write("\t");

		String writer = request.getParameter("writer");
		String comment = request.getParameter("comment");
	
      out.write("\r\n");
      out.write("\t<h1>list.jsp</h1>\r\n");
      out.write("\t<hr>\r\n");
      out.write("\t\t<a href=\"write.jsp\">코멘트 등록</a>\r\n");
      out.write("\t<hr>\r\n");
      out.write("\t<table border=\"1\" align=\"center\" width=\"90%\">\r\n");
      out.write("\t\t<caption>등록된 코멘트</caption>\r\n");
      out.write("\t\t<thead>\r\n");
      out.write("\t\t\t<tr>\r\n");
      out.write("\t\t\t\t<th width=\"20%\">작성자</th>\r\n");
      out.write("\t\t\t\t<th>코멘트</th>\r\n");
      out.write("\t\t\t\t<th width=\"20%\">작성일</th>\r\n");
      out.write("\t\t\t</tr>\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t\t<tr>\r\n");
      out.write("\t\t\t\t<td align=\"center\">");
      out.print(writer);
      out.write("</td>\r\n");
      out.write("\t\t\t\t<td align=\"center\">");
      out.print(comment);
      out.write("</td>\r\n");
      out.write("\t\t\t\t<td>2025.12.04</td>\r\n");
      out.write("\t\t\t</tr>\r\n");
      out.write("\t\t</thead>\r\n");
      out.write("\t</table>\r\n");
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
