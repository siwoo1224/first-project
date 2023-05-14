package kr.co.samjo.cart;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import kr.co.samjo.res.resDAO;

@Controller
public class cartCont {
	
	cartDAO dao = null;
	resDAO dao2 = null;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy. MM. dd. a HH:mm:ss");

	public cartCont() {
		dao = new cartDAO();
		System.out.println("-----cartCont객체 생성됨");
	}
	
	//장바구니 등록
	@RequestMapping(value = "cart/addCart.do", method = RequestMethod.POST)
	public ModelAndView addCart(@ModelAttribute cartDTO dto, HttpServletRequest request, HttpServletResponse response) throws ParseException {
		HttpSession session = request.getSession();
		String user_id = (String) session.getAttribute("s_id");
		if(user_id == null) {
			user_id = "guest";
		}
				
		cartDTO cdto = new cartDTO();
		cdto.setUser_id(user_id);
		cdto.setS_code(request.getParameter("s_code"));
		cdto.setCnt(Integer.parseInt(request.getParameter("cnt")));
		cdto.setP_cnt(Integer.parseInt(request.getParameter("p_cnt")));
		cdto.setSdate(request.getParameter("sdate"));
		cdto.setFdate(request.getParameter("fdate"));
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("cart/msgView");
		int cnt = dao.create(cdto);
		/*
		if (cnt == 0) {
			String msg = "<p>장바구니 등록 실패</p>";
			String img = "<img src='../images/fail.png'>";
			String link1 = "<input type='button' value='다시시도' onclick='javascript:history.back()'>";
			String link2 = "<input type='button' value='장바구니목록' onclick='location.href=\"/cart/list.do\"'>";
			mav.addObject("msg", msg);
			mav.addObject("img", img);
			mav.addObject("link1", link1);
			mav.addObject("link2", link2);
		} else {
			String msg = "<p>장바구니 등록 성공</p>";
			String img = "<img src='../images/sound.png'>";
			String link2 = "<input type='button' value='장바구니목록' onclick='location.href=\"/cart/list.do\"'>";
			mav.addObject("msg", msg);
			mav.addObject("img", img);
			mav.addObject("link2", link2);
		}*/

	      if (cnt == 0) {
	    	  String msg = "<script type=\"text/javascript\">\r\n"
	    	      		+ "    	alert('장바구니등록실패.');\r\n"
	    	      		+ "    	location.href='list.do'"
	    	      		+ ";"
	    	      		+ "    </script>";
	    	      mav.addObject("msg", msg);
	      }else {
	    	  String msg = "<script type=\"text/javascript\">"
	    	      		+ "    	alert('장바구니에 추가됐어요.');"
	    	      		+ "    	location.href='list.do'"
	    	      		+ ";"
	    	      		+ "    </script>";
	    	      mav.addObject("msg", msg);
	      }
		return mav;
	}

	//장바구니 목록
	@RequestMapping("/cart/list.do")
	public ModelAndView list(HttpServletRequest request, HttpServletResponse response){
		HttpSession session = request.getSession();
		Map<String, Object> map = new HashMap<String, Object>();
		ModelAndView mav = new ModelAndView();
		String userId = (String) session.getAttribute("s_id");
		if(userId==null) {userId="guest";}
		ArrayList<cartDTO> list = new ArrayList<cartDTO>();
		list = dao.list(userId.trim());
		
		if(list==null) {
			map.put("list", list);
			map.put("count", 0);
			map.put("amount", 0);
		}else {
			long amount = dao.amount(list);
			map.put("list", list);
			map.put("count", list.size());
			map.put("amount", amount);
		}
		
		mav.setViewName("cart/list");
		mav.addObject("map", map);
		
		return mav;
	}// list end

	//장바구니 삭제페이지 이동
	@RequestMapping(value = "cart/delete.do", method = RequestMethod.GET)
	public ModelAndView deleteForm(int c_no) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("cart/delete");
		mav.addObject("c_no", c_no);
		return mav;
	}// deleteForm end

	//장바구니 삭제
	@RequestMapping(value = "cart/delete.do", method = RequestMethod.POST)
	public ModelAndView deleteProc(int c_no) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("cart/msgView");

		int cnt = dao.delete(c_no);
		/*
		if (cnt == 0) {
			String msg="<p>장바구니 항목 삭제 실패</p>";
			String img="<img src='../images/fail.png'>";
			String link1="<input type='button' value='다시시도' onclick='javascript:history.back()'>";
			String link2="<input type='button' value='목록으로' onclick='location.href=\"/list.do\"'>";
			mav.addObject("msg", msg);
			mav.addObject("img", img);
			mav.addObject("link1", link1);
			mav.addObject("link2", link2);
		} else {
			String msg="<p>장바구니 항목 삭제 성공</p>";
			String img="<img src='../images/sound.png'>";
			String link2="<input type='button' value='목록으로' onclick='location.href=\"/list.do\"'>";
			mav.addObject("msg", msg);
			mav.addObject("img", img);
			mav.addObject("link2", link2);
		}
		*/

	      if (cnt == 0) {
	    	  String msg = "<script type=\"text/javascript\">\r\n"
	    	      		+ "    	alert('장바구니삭제실패.');\r\n"
	    	      		+ "    	location.href='list.do'"
	    	      		+ ";"
	    	      		+ "    </script>";
	    	      mav.addObject("msg", msg);
	      }else {
	    	  String msg = "<script type=\"text/javascript\">"
	    	      		+ "    	alert('장바구니가 삭제됐어요.');"
	    	      		+ "    	location.href='list.do'"
	    	      		+ ";"
	    	      		+ "    </script>";
	    	      mav.addObject("msg", msg);
	      }

		return mav;
	}// deleteProc end

}
