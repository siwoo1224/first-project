package kr.co.samjo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
@Controller
public class indexCont {
	public indexCont() {
		System.out.println("-----indexCont() 객체 생성됨");
	}//end
	
	//결과확인 http://localhost:9095/index.do
	
	//요청명령어 등록하고, 실행의 주체는 메소드(함수)
	@RequestMapping("/index.do")
	public ModelAndView index() {
		ModelAndView mav=new ModelAndView();
		// applicaion.properties환경설정 파일 참조
		// /WEB-INF/views/index.jsp
		mav.setViewName("index");
		return mav;
	}//index() end
}
