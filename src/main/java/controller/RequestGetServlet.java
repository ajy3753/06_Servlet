package controller;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet 같은 경우 반드시 contextPath 뒤에 작성되어야한다.
 * http:localhost:500/st/test1.do
 */

// @webServlet("/test1.do")
public class RequestGetServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RequestGetServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Get 방식으로 요청 시 해당 doGet 메소드가 저절로 호출된다.
		// System.out.println("서버에 요청이 정상적으로 도착");
		
		/*
		 * 첫 번째 매개변수인 request에는 요청 시 전달된 내용들이 담겨있다. (사용자가 입력한 값, 요청방식, 요청자의 ip 주소 등)
		 * 두 번째 매개변수인 response에는 요청 처리 후 응답에 사용되는 객ㅊ[
		 * 
		 * 요청 처리를 위해서 요청 시 전달된 값들 추출
		 * -> request의 parameter 영역 안에 존재
		 * [표현법] request.getParameter("네임명");
		 */
		
		String name = request.getParameter("name");
		String gender = request.getParameter("gender");
		int age = Integer.parseInt(request.getParameter("age"));
		String city = request.getParameter("city");
		double height = Double.parseDouble(request.getParameter("height"));
		
		// 체크박스와 같이 복수의 value값들을 추출하고자 할 때
		String[] foods = request.getParameterValues("food");
		
		System.out.println("name : " + name);
		System.out.println("gender : " + gender);
		System.out.println("city : " + city);
		System.out.println("height : " + height);
		
		if(foods == null) {
			System.out.println("foods : 없음");
		}
		else {
			System.out.println("foods : " + String.join(" , ", foods));
		}
		
		// > service > dao > db
		/*
		 * int result = new MemberService().insertMember(name, gender...);
		 * if(result > ) {
		 * 	// 성공
		 * }
		 * else {
		 * 	// 실패
		 * }
		 */
		
		// 위의 결과에 따라 응답 페이지(HTML) 만들어서 전송
		// 즉, 여기 Java 코드 내에 사용자가 보게될 응답 HTML 구문을 작성
		
		// response 객체를 통해 사용자에게 HTML(응답화면) 전달
		// 1) 선언 -> 출력할 내용: 문서형태의 html, 문자셋: UTF-8
		response.setContentType("text/html; charset=UTF-8");
		
		// 2) 응답받는 사용자(요청했던 사람)와의 스트림(클라이언트와의 연결 통로)를 생성
		PrintWriter out = response.getWriter();
		
		// 3) 위에서 가져온 스트림을 통해 응답 HTML을 한 줄씩 출력
		out.println("<html>");
		out.println("<head>");
		out.println("<style>");
		out.println("</style>");
		out.println("</head>");
		
		out.println("<body>");
		
		out.println("<h2>개인정보 응답화면<h2>");
		out.printf("<span>%s</span> 님은 ", name);
		out.printf("<span>%d</span>살이며, ", age);
		out.printf("<span>%s</span>에 삽니다. ", city);
		out.printf("성별은" );
		if(gender == null) {
			out.println("선택하지 않았습니다.");
		}
		else {
			if(gender.equals("M")) {
				out.printf("<span>남자입니다.</span>", gender);
			}
			else {
				out.printf("<span>여자입니다.</span>", gender);
				
			}
		}
		
		out.println("</body>");
		out.println("</html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
