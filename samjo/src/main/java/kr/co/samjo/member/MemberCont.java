package kr.co.samjo.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import kr.co.samjo.product.maszip.MaszipDTO;
import net.utility.UploadSaveManager;

@Controller
public class MemberCont {
	
	private MemberDAO dao=null;
	
	//mymelon프로젝트의 MediagroupCont 참조하시면 됩니다~
	public MemberCont() {
		dao=new MemberDAO();
		System.out.println("-----loginFormCont() 객체 생성됨");
	}//end
	
	
	@RequestMapping(value = "/member/loginForm.do", method=RequestMethod.GET)
	public ModelAndView loginForm_repage() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/member/loginForm");
		return mav;
	}
	
	@RequestMapping(value = "/member/loginForm.do", method = RequestMethod.POST)
	public ModelAndView loginForm(HttpServletRequest req, HttpSession session) {
		//loginForm.jsp에서 입력한 아이디/비번 가져오기		
		String user_id	 =req.getParameter("user_id").trim();
		String user_pw   =req.getParameter("user_pw").trim();
		
		//dto에 담기
		MemberDTO dto=new MemberDTO();
		dto.setUser_id(user_id);
		dto.setUser_pw(user_pw);
		
		//DB에 가서 로그인 정보 가져오기
		String user_level=dao.loginProc(dto);
		
		//세션영역에 로그인 정보 올리기
		session.setAttribute("s_id", user_id);
		session.setAttribute("s_passwd", user_pw);
		session.setAttribute("s_mlevel", user_level);		
		
		ModelAndView mav=new ModelAndView();
		mav.setViewName("/member/loginForm"); //뷰페이지 이동하기
		
		return mav;
	}//loginForm() end
	
	
	///////////////////////////////////////////////////////
	// spring03_web 프로젝트 kr.co.web.test03 팩키지 참조하면 됩니다
	///////////////////////////////////////////////////////
	
	//<form name="loginfrm" id="loginfrm" method="post" action="loginProc.do"> post방식으로 요청했을때
	@RequestMapping(value = "/member/loginProc.do", method = RequestMethod.POST)
	public ModelAndView loginProc(HttpServletRequest req, HttpSession session) {
		//loginForm.jsp에서 입력한 아이디/비번 가져오기		
		String user_id	 =req.getParameter("user_id").trim();
		String user_pw   =req.getParameter("user_pw").trim();
		
		//dto에 담기
		MemberDTO dto=new MemberDTO();
		dto.setUser_id(user_id);
		dto.setUser_pw(user_pw);
		
		//DB에 가서 로그인 정보 가져오기
		String user_level=dao.loginProc(dto);
		
		//세션영역에 로그인 정보 올리기
		session.setAttribute("s_id", user_id);
		session.setAttribute("s_passwd", user_pw);
		session.setAttribute("s_mlevel", user_level);		
		
		ModelAndView mav=new ModelAndView();
		mav.setViewName("/member/loginForm"); //뷰페이지 이동하기
		
		return mav;
	}//loginProc() end
	
	@RequestMapping("/member/agreement.do")
	public ModelAndView agreement() {
		ModelAndView mav=new ModelAndView();
		mav.setViewName("/member/agreement");
		return mav;
	}//agreement() end
	
	@RequestMapping("/member/findID.do")
	public ModelAndView findID() {
		ModelAndView mav=new ModelAndView();
		mav.setViewName("/member/findID");
		return mav;
	}//findID() end
	
	@RequestMapping("/member/idCheckForm.do")
	public ModelAndView idCheckForm() {
		ModelAndView mav=new ModelAndView();
		mav.setViewName("/member/idCheckForm");
		return mav;
	}//idCheckForm() end
	
	@RequestMapping("/member/memberForm.do")
	public ModelAndView memberForm() {
		ModelAndView mav=new ModelAndView();
		mav.setViewName("/member/memberForm");
		return mav;
	}//memberForm() end
	
	@RequestMapping("/member/memberProc.do")
	public ModelAndView memberProc(@ModelAttribute MemberDTO dto, HttpServletRequest req) {
		ModelAndView mav=new ModelAndView();
		mav.setViewName("/member/memberProc");

		return mav;
	}//memberProc() end
	
	@RequestMapping("/member/memberModify.do")
	public ModelAndView memberModify() {
		ModelAndView mav=new ModelAndView();
		mav.setViewName("/member/memberModify");
		return mav;
	}//memberModify() end
	@RequestMapping("/member/memberModifyProc.do")
	public ModelAndView memberModifyProc() {
		ModelAndView mav=new ModelAndView();
		mav.setViewName("/member/memberModifyProc");
		return mav;
	}//memberModify() end
	
	@RequestMapping("/member/findIDProc.do")
	public ModelAndView findIDProc() {
		ModelAndView mav=new ModelAndView();
		mav.setViewName("/member/findIDProc");
		return mav;
	}//findIDProc() end
	
	
	@RequestMapping("/member/logout.do")
	public ModelAndView logout() {
		ModelAndView mav=new ModelAndView();
		mav.setViewName("/member/logout");
		return mav;
	}//logout() end
	
	@RequestMapping("/member/idCheckProc.do")
	public ModelAndView idCheckProc() {
		ModelAndView mav=new ModelAndView();
		mav.setViewName("/member/idCheckProc");
		return mav;
	}//idCheckProc() end
	
	@RequestMapping("/member/emailCheckForm.do")
	public ModelAndView emailCheckForm() {
		ModelAndView mav=new ModelAndView();
		mav.setViewName("/member/emailCheckForm");
		return mav;
	}//emailCheckForm() end
	
	@RequestMapping("/member/emailCheckProc.do")
	public ModelAndView emailCheckProc() {
		ModelAndView mav=new ModelAndView();
		mav.setViewName("/member/emailCheckProc");
		return mav;
	}//emailCheckProc() end
	
	@RequestMapping("/member/memberWithdraw.do")
	public ModelAndView memberWithdraw() {
		ModelAndView mav=new ModelAndView();
		mav.setViewName("/member/memberWithdraw");
		return mav;
	}//memberWithdraw() end
	
	@RequestMapping(value = "/member/memberWithdraw_re.do", method=RequestMethod.GET)
	public ModelAndView memberWithdraw_repage() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/member/memberWithdraw_re");
		return mav;
	}
	
	@RequestMapping(value = "/member/memberWithdraw_re.do", method = RequestMethod.POST)
	public ModelAndView memberWithdraw_re(HttpServletRequest req, HttpSession session) {
		//loginForm.jsp에서 입력한 아이디/비번 가져오기		
		String user_id	 =req.getParameter("user_id").trim();
		String user_pw   =req.getParameter("user_pw").trim();
		
		//dto에 담기
		MemberDTO dto=new MemberDTO();
		dto.setUser_id(user_id);
		dto.setUser_pw(user_pw);
		
		//DB에 가서 로그인 정보 가져오기
		String user_level=dao.loginProc(dto);
		
		//세션영역에 로그인 정보 올리기
		session.setAttribute("s_id", user_id);
		session.setAttribute("s_passwd", user_pw);
		session.setAttribute("s_mlevel", user_level);		
		
		ModelAndView mav=new ModelAndView();
		mav.setViewName("/member/loginForm"); //뷰페이지 이동하기
		
		return mav;
	}//memberWithdraw_re() end
	
	@RequestMapping("/member/memberWithdraw_ok.do")
	public ModelAndView memberWithdraw_ok() {
		ModelAndView mav=new ModelAndView();
		mav.setViewName("/member/memberWithdraw_ok");
		return mav;
	}//memberWithdraw_ok() end
	
	@RequestMapping(value = "/admin/userdelete.do", method = RequestMethod.GET)
	public ModelAndView deleteForm(String id) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("member/deleteForm");
		MemberDTO dto = dao.read(id);// 수정하고자 하는 행 가져오기
		mav.addObject("dto", dto);
		return mav;
	}// deleteForm() end

	@RequestMapping(value = "/admin/userdelete.do", method = RequestMethod.POST)
	public ModelAndView deleteProc(String id, HttpServletRequest req) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("maszip/msgView");

		// 삭제하고자 하는 글정보 가져오기(storage폴더에서 삭제할 파일명을 보관하기 위해)
		MemberDTO oldDTO = dao.read(id);

		int cnt = dao.delete(id);
		if (cnt == 0) {
			String msg = "<p>회원 삭제 실패!!</p>";
			String link1 = "<input type='button' value='다시시도' onclick='javascript:history.back()'>";
			String link2 = "<input type='button' value='목록으로' onclick=\"location.href='/admin/maszip/List.do'\">";
			mav.addObject("msg", msg);
			mav.addObject("link1", link1);
			mav.addObject("link2", link2);
		} else {
			String msg = "<p>회원이 삭제되었습니다</p>";
			String link2 = "<input type='button' value='목록으로' onclick=\"location.href='/admin/maszip/List.do'\">";
			mav.addObject("msg", msg);
			mav.addObject("link2", link2);
			
		} // if end
		return mav;
	}// deleteProc() end
	

}
