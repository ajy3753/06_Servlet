package controller;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RequestPostServlet
 */
public class RequestPostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RequestPostServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// POST 방식의 경우, 데이터를 추출하기 전에 인코딩 형식을 먼저 설정해줘야 한다.
		request.setCharacterEncoding("UTF-8");
		
		// 요청 시 전달된 값들은 request의 parameter 영역에 담겨있다
		String name = request.getParameter("name");
		String gender = request.getParameter("gender");
		int age = Integer.parseInt(request.getParameter("age"));
		String city = request.getParameter("city");
		double height = Double.parseDouble(request.getParameter("height"));
		
		// 체크박스와 같이 복수의 value값들을 추출하고자 할 때
		String[] foods = request.getParameterValues("food");
		
		System.out.println("name :" + name);
		System.out.println("gender :" + gender);
		System.out.println("age :" + age);
		System.out.println("city :" + city);
		System.out.println("height :" + height);
		
		if(foods == null) {
			System.out.println("foods : 없음 ");
		} else {
			System.out.println("foods : " + String.join(" ", foods));
		}
		
		//> service > dao > db
		/*
		 * int result = new MemberService().insertMember(name, gender...);
		 * if (result > 0) {
		 *	//성공
		 * } else {
		 * 	//실패
		 * }
		 */
		
		// 순수 Servlet 방식 : Java 코드 내에 html을 기술
		// JSP(Java Server Page) 방식 : html 내에 Java 코드를 쓸 수 있음
		
		// 응답 페이지를 만드는 과정을 jsp에게 위임
		// 단, 응답화면에서 필요로 하는 데이터들을 차곡차곡 담아서 전달해줘야 한다.
		// 데이터들을 담기위한 공간 == request의 attribute 영역
		// request.setAttribute("키", "값")
		
		request.setAttribute("name", name);
		request.setAttribute("age", age);
		request.setAttribute("height", height);
		request.setAttribute("gender", gender);
		request.setAttribute("foods", foods);
		
		// 응답하고자 하는 뷰(jsp)를 선택해서 값 전달 -> RequestDispaatcher 객체 생성
		RequestDispatcher view = request.getRequestDispatcher("/views/responsePage.jsp");
		view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
