package kr.co.samjo.admin;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import kr.co.samjo.product.rental.rentalDAO;
import kr.co.samjo.product.rental.rentalDTO;
import kr.co.samjo.product.rentalcar.rentalcarDTO;
import kr.co.samjo.product.sookso.SooksoDTO;
import net.utility.Utility;

@Controller
public class adminCont {

	adminDAO dao = null;
	rentalDAO udao = null;

	public adminCont() {
		dao = new adminDAO();// DB연결 객체 생성
		udao = new rentalDAO();
		System.out.println("-----adminCont() 객체 생성됨");
	}// end

	// 결과확인 http://localhost:9095/admin/index.do

	// 요청명령어 등록하고, 실행의 주체는 메소드(함수)
	@RequestMapping("admin/index.do")
	public ModelAndView index() {
		ModelAndView mav = new ModelAndView();
		// applicaion.properties환경설정 파일 참조
		// /WEB-INF/views/index.jsp
		mav.setViewName("admin/index");
		return mav;
	}// index() end

	// 관광지 목록
	@RequestMapping("/admin/tour/List.do")
	public ModelAndView Tourlist(HttpServletRequest req) {// (HttpServletRequest req, String word)
		// 검색버튼을 누르면 word가 존재하지만
		// index.jsp에서 관광지 버튼을 클릭하면 word가 존재하지 않음 그래서 에러 발생됨
		// 그래서 String word 삭제함

		// 입력된 검색어 확인(검색어가 있으면 검색어 존재, 검색어가 없으면 빈문자열 "")
		String word = Utility.checkNull(req.getParameter("word"));
		// System.out.println(word);

		ModelAndView mav = new ModelAndView();
		mav.setViewName("admin/tourist");

		int totalRowCount = dao.TourtotalRowCount(); // 총 글갯수

		// 페이징
		int numPerPage = 10; // 한 페이지당 레코드 갯수
		int pagePerBlock = 10; // 페이지 리스트

		String pageNum = req.getParameter("pageNum");
		if (pageNum == null) {
			pageNum = "1";
		}

		int currentPage = Integer.parseInt(pageNum);
		int startRow = (currentPage - 1) * numPerPage + 1;
		int endRow = currentPage * numPerPage;

		// 페이지 수
		double totcnt = (double) totalRowCount / numPerPage;
		int totalPage = (int) Math.ceil(totcnt);

		double d_page = (double) currentPage / pagePerBlock;
		int Pages = (int) Math.ceil(d_page) - 1;
		int startPage = Pages * pagePerBlock;
		int endPage = startPage + pagePerBlock + 1;

		List list = null;
		if (totalRowCount > 0) {
			list = dao.Tourlist(startRow, endRow, word);
		} else {
			list = Collections.EMPTY_LIST;
		} // if end

		int number = 0;
		number = totalRowCount - (currentPage - 1) * numPerPage;

		mav.addObject("number", number);
		mav.addObject("pageNum", currentPage);
		mav.addObject("startRow", startRow);
		mav.addObject("endRow", endRow);
		mav.addObject("count", totalRowCount);
		mav.addObject("pageSize", pagePerBlock);
		mav.addObject("totalPage", totalPage);
		mav.addObject("startPage", startPage);
		mav.addObject("endPage", endPage);
		mav.addObject("list", list);
		return mav;
	}// Tourlist() end

	// 문화행사 목록
	@RequestMapping("/admin/festival/List.do")
	public ModelAndView Feslist(HttpServletRequest req) {

		// 입력된 검색어 확인(검색어가 있으면 검색어 존재, 검색어가 없으면 빈문자열 "")
		String word = Utility.checkNull(req.getParameter("word"));

		ModelAndView mav = new ModelAndView();
		mav.setViewName("admin/festivalList");

		int totalRowCount = dao.FestotalRowCount(); // 총 글갯수

		// 페이징
		int numPerPage = 10; // 한 페이지당 레코드 갯수
		int pagePerBlock = 10; // 페이지 리스트

		String pageNum = req.getParameter("pageNum");
		if (pageNum == null) {
			pageNum = "1";
		}

		int currentPage = Integer.parseInt(pageNum);
		int startRow = (currentPage - 1) * numPerPage + 1;
		int endRow = currentPage * numPerPage;

		// 페이지 수
		double totcnt = (double) totalRowCount / numPerPage;
		int totalPage = (int) Math.ceil(totcnt);

		double d_page = (double) currentPage / pagePerBlock;
		int Pages = (int) Math.ceil(d_page) - 1;
		int startPage = Pages * pagePerBlock;
		int endPage = startPage + pagePerBlock + 1;

		List list = null;
		if (totalRowCount > 0) {
			list = dao.Feslist(startRow, endRow, word);
		} else {
			list = Collections.EMPTY_LIST;
		} // if end

		int number = 0;
		number = totalRowCount - (currentPage - 1) * numPerPage;

		mav.addObject("number", number);
		mav.addObject("pageNum", currentPage);
		mav.addObject("startRow", startRow);
		mav.addObject("endRow", endRow);
		mav.addObject("count", totalRowCount);
		mav.addObject("pageSize", pagePerBlock);
		mav.addObject("totalPage", totalPage);
		mav.addObject("startPage", startPage);
		mav.addObject("endPage", endPage);
		mav.addObject("list", list);
		return mav;
	}// Feslist() end

	// 공지사항 목록
	@RequestMapping("/admin/notice/List.do")
	public ModelAndView bbsList(HttpServletRequest req) {

		// 입력된 검색어 확인(검색어가 있으면 검색어 존재, 검색어가 없으면 빈문자열 "")
		String word = Utility.checkNull(req.getParameter("word"));
		String col = Utility.checkNull(req.getParameter("col"));

		ModelAndView mav = new ModelAndView();
		mav.setViewName("admin/noticeList");

		int totalRowCount = dao.NoticetotalRowCount(); // 총 글갯수

		// 페이징
		int numPerPage = 9; // 한 페이지당 레코드 갯수
		int pagePerBlock = 10; // 페이지 리스트

		String pageNum = req.getParameter("pageNum");
		if (pageNum == null) {
			pageNum = "1";
		}

		int currentPage = Integer.parseInt(pageNum);
		int startRow = (currentPage - 1) * numPerPage + 1;
		int endRow = currentPage * numPerPage;

		// 페이지 수
		double totcnt = (double) totalRowCount / numPerPage;
		int totalPage = (int) Math.ceil(totcnt);

		double d_page = (double) currentPage / pagePerBlock;
		int Pages = (int) Math.ceil(d_page) - 1;
		int startPage = Pages * pagePerBlock;
		int endPage = startPage + pagePerBlock + 1;

		List list = null;
		if (totalRowCount > 0) {
			list = dao.Noticelist(startRow, endRow, col, word);
		} else {
			list = Collections.EMPTY_LIST;
		} // if end

		int number = 0;
		number = totalRowCount - (currentPage - 1) * numPerPage;

		mav.addObject("number", number);
		mav.addObject("pageNum", currentPage);
		mav.addObject("startRow", startRow);
		mav.addObject("endRow", endRow);
		mav.addObject("count", totalRowCount);
		mav.addObject("pageSize", pagePerBlock);
		mav.addObject("totalPage", totalPage);
		mav.addObject("startPage", startPage);
		mav.addObject("endPage", endPage);
		mav.addObject("list", list);
		return mav;
	}// Noticelist() end

	@RequestMapping("/admin/board/List.do")
	public ModelAndView Boardlist(HttpServletRequest req) {

		// 입력된 검색어 확인(검색어가 있으면 검색어 존재, 검색어가 없으면 빈문자열 "")
		String word = Utility.checkNull(req.getParameter("word"));
		String col = Utility.checkNull(req.getParameter("col"));

		ModelAndView mav = new ModelAndView();
		mav.setViewName("admin/bbsList");

		int totalRowCount = dao.BoardtotalRowCount(); // 총 글갯수

		// 페이징
		int numPerPage = 10; // 한 페이지당 레코드 갯수
		int pagePerBlock = 10; // 페이지 리스트

		String pageNum = req.getParameter("pageNum");
		if (pageNum == null) {
			pageNum = "1";
		}

		int currentPage = Integer.parseInt(pageNum);
		int startRow = (currentPage - 1) * numPerPage + 1;
		int endRow = currentPage * numPerPage;

		// 페이지 수
		double totcnt = (double) totalRowCount / numPerPage;
		int totalPage = (int) Math.ceil(totcnt);

		double d_page = (double) currentPage / pagePerBlock;
		int Pages = (int) Math.ceil(d_page) - 1;
		int startPage = Pages * pagePerBlock;
		int endPage = startPage + pagePerBlock + 1;

		List list = null;
		if (totalRowCount > 0) {
			list = dao.Boardlist(startRow, endRow, col, word);
		} else {
			list = Collections.EMPTY_LIST;
		} // if end

		int number = 0;
		number = totalRowCount - (currentPage - 1) * numPerPage;

		mav.addObject("number", number);
		mav.addObject("pageNum", currentPage);
		mav.addObject("startRow", startRow);
		mav.addObject("endRow", endRow);
		mav.addObject("count", totalRowCount);
		mav.addObject("pageSize", pagePerBlock);
		mav.addObject("totalPage", totalPage);
		mav.addObject("startPage", startPage);
		mav.addObject("endPage", endPage);
		mav.addObject("list", list);
		return mav;
	}// list() end

	// 숙소 목록
	@RequestMapping("/admin/sookso/List.do")
	public ModelAndView Sooksolist(HttpServletRequest req) {
		String word = Utility.checkNull(req.getParameter("word"));
		ModelAndView mav = new ModelAndView();
		mav.setViewName("admin/sooksoList");

		int totalRowCount = dao.SooksototalRowCount(); // 총 글갯수

		// 페이징
		int numPerPage = 9; // 한 페이지당 레코드 갯수
		int pagePerBlock = 10; // 페이지 리스트

		String pageNum = req.getParameter("pageNum");
		if (pageNum == null) {
			pageNum = "1";
		}

		int currentPage = Integer.parseInt(pageNum);
		int startRow = (currentPage - 1) * numPerPage + 1;
		int endRow = currentPage * numPerPage;

		// 페이지 수
		double totcnt = (double) totalRowCount / numPerPage;
		int totalPage = (int) Math.ceil(totcnt);

		double d_page = (double) currentPage / pagePerBlock;
		int Pages = (int) Math.ceil(d_page) - 1;
		int startPage = Pages * pagePerBlock;
		int endPage = startPage + pagePerBlock + 1;

		List list = null;
		if (totalRowCount > 0) {
			list = dao.Sooksolist(startRow, endRow, word);
		} else {
			list = Collections.EMPTY_LIST;
		} // if end

		int number = 0;
		number = totalRowCount - (currentPage - 1) * numPerPage;

		mav.addObject("number", number);
		mav.addObject("pageNum", currentPage);
		mav.addObject("startRow", startRow);
		mav.addObject("endRow", endRow);
		mav.addObject("count", totalRowCount);
		mav.addObject("pageSize", pagePerBlock);
		mav.addObject("totalPage", totalPage);
		mav.addObject("startPage", startPage);
		mav.addObject("endPage", endPage);
		mav.addObject("list", list);
		return mav;
	}// Sooksolist() end

	// 숙소 상세보기 및 방 목록
	@RequestMapping("/admin/sookso/Listread.do")
	public ModelAndView read(String s_cn, String room_cn, HttpServletRequest req) {
		ModelAndView mav = new ModelAndView();
		SooksoDTO dto = dao.Sooksoread(s_cn);
		mav.setViewName("admin/sooksoRead");
		mav.addObject("dto", dto);

		int totalRowCount = dao.SooksototalRowCount(); // 총 글갯수

		// 페이징
		int numPerPage = 9; // 한 페이지당 레코드 갯수
		int pagePerBlock = 10; // 페이지 리스트

		String pageNum = req.getParameter("pageNum");
		if (pageNum == null) {
			pageNum = "1";
		}

		int currentPage = Integer.parseInt(pageNum);
		int startRow = (currentPage - 1) * numPerPage + 1;
		int endRow = currentPage * numPerPage;

		// 페이지 수
		double totcnt = (double) totalRowCount / numPerPage;
		int totalPage = (int) Math.ceil(totcnt);

		double d_page = (double) currentPage / pagePerBlock;
		int Pages = (int) Math.ceil(d_page) - 1;
		int startPage = Pages * pagePerBlock;
		int endPage = startPage + pagePerBlock + 1;

		List list = null;
		if (totalRowCount > 0) {
			list = dao.Roomlist(startRow, endRow, s_cn);
		} else {
			list = Collections.EMPTY_LIST;
		} // if end

		int number = 0;
		number = totalRowCount - (currentPage - 1) * numPerPage;

		mav.addObject("number", number);
		mav.addObject("pageNum", currentPage);
		mav.addObject("startRow", startRow);
		mav.addObject("endRow", endRow);
		mav.addObject("count", totalRowCount);
		mav.addObject("pageSize", pagePerBlock);
		mav.addObject("totalPage", totalPage);
		mav.addObject("startPage", startPage);
		mav.addObject("endPage", endPage);
		mav.addObject("list", list);

		return mav;
	}// read() end

	// 맛집 목록
	@RequestMapping("/admin/maszip/List.do")
	public ModelAndView Masziplist(HttpServletRequest req) {// (HttpServletRequest req, String word)
		// 검색버튼을 누르면 word가 존재하지만
		// index.jsp에서 관광지 버튼을 클릭하면 word가 존재하지 않음 그래서 에러 발생됨
		// 그래서 String word 삭제함

		// 입력된 검색어 확인(검색어가 있으면 검색어 존재, 검색어가 없으면 빈문자열 "")
		String word = Utility.checkNull(req.getParameter("word"));
		// System.out.println(word);

		ModelAndView mav = new ModelAndView();
		mav.setViewName("admin/maszipList");

		int totalRowCount = dao.MasziptotalRowCount(); // 총 글갯수

		// 페이징
		int numPerPage = 10; // 한 페이지당 레코드 갯수
		int pagePerBlock = 10; // 페이지 리스트

		String pageNum = req.getParameter("pageNum");
		if (pageNum == null) {
			pageNum = "1";
		}

		int currentPage = Integer.parseInt(pageNum);
		int startRow = (currentPage - 1) * numPerPage + 1;
		int endRow = currentPage * numPerPage;

		// 페이지 수
		double totcnt = (double) totalRowCount / numPerPage;
		int totalPage = (int) Math.ceil(totcnt);

		double d_page = (double) currentPage / pagePerBlock;
		int Pages = (int) Math.ceil(d_page) - 1;
		int startPage = Pages * pagePerBlock;
		int endPage = startPage + pagePerBlock + 1;

		List list = null;
		if (totalRowCount > 0) {
			list = dao.Masziplist(startRow, endRow, word);
		} else {
			list = Collections.EMPTY_LIST;
		} // if end

		int number = 0;
		number = totalRowCount - (currentPage - 1) * numPerPage;

		mav.addObject("number", number);
		mav.addObject("pageNum", currentPage);
		mav.addObject("startRow", startRow);
		mav.addObject("endRow", endRow);
		mav.addObject("count", totalRowCount);
		mav.addObject("pageSize", pagePerBlock);
		mav.addObject("totalPage", totalPage);
		mav.addObject("startPage", startPage);
		mav.addObject("endPage", endPage);
		mav.addObject("list", list);
		return mav;
	}// Masziplist() end

	// packagetourList

	@RequestMapping("admin/packagetour/List.do")
	public ModelAndView list3(HttpServletRequest req) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("admin/packagetourList");

		// 입력된 검색어 확인(검색어가 있으면 검색어 존재, 검색어가 없으면 빈문자열 "")
		String word = Utility.checkNull(req.getParameter("word"));

		int totalRowCount = dao.PackagetotalRowCount(); // 총 글갯수

		// 페이징
		int numPerPage = 10; // 한 페이지당 레코드 갯수
		int pagePerBlock = 10; // 페이지 리스트

		String pageNum = req.getParameter("pageNum");
		if (pageNum == null) {
			pageNum = "1";
		}

		int currentPage = Integer.parseInt(pageNum);
		int startRow = (currentPage - 1) * numPerPage + 1;
		int endRow = currentPage * numPerPage;

		// 페이지 수
		double totcnt = (double) totalRowCount / numPerPage;
		int totalPage = (int) Math.ceil(totcnt);

		double d_page = (double) currentPage / pagePerBlock;
		int Pages = (int) Math.ceil(d_page) - 1;
		int startPage = Pages * pagePerBlock;
		int endPage = startPage + pagePerBlock + 1;

		List list = null;
		if (totalRowCount > 0) {
			list = dao.Packagelist(startRow, endRow, word);
		} else {
			list = Collections.EMPTY_LIST;
		} // if end

		int number = 0;
		number = totalRowCount - (currentPage - 1) * numPerPage;

		mav.addObject("number", number);
		mav.addObject("pageNum", currentPage);
		mav.addObject("startRow", startRow);
		mav.addObject("endRow", endRow);
		mav.addObject("count", totalRowCount);
		mav.addObject("pageSize", pagePerBlock);
		mav.addObject("totalPage", totalPage);
		mav.addObject("startPage", startPage);
		mav.addObject("endPage", endPage);
		mav.addObject("list", list);
		System.out.println(list);
		return mav;
	}// list() end
	
	
	// 회원 목록
		@RequestMapping("/admin/member/List.do")
		public ModelAndView Memberlist(HttpServletRequest req) {// (HttpServletRequest req, String word)
			// 검색버튼을 누르면 word가 존재하지만
			// index.jsp에서 관광지 버튼을 클릭하면 word가 존재하지 않음 그래서 에러 발생됨
			// 그래서 String word 삭제함

			// 입력된 검색어 확인(검색어가 있으면 검색어 존재, 검색어가 없으면 빈문자열 "")
			String word = Utility.checkNull(req.getParameter("word"));
			// System.out.println(word);

			ModelAndView mav = new ModelAndView();
			mav.setViewName("admin/memberList");

			int totalRowCount = dao.MembertotalRowCount(); // 총 글갯수

			// 페이징
			int numPerPage = 10; // 한 페이지당 레코드 갯수
			int pagePerBlock = 10; // 페이지 리스트

			String pageNum = req.getParameter("pageNum");
			if (pageNum == null) {
				pageNum = "1";
			}

			int currentPage = Integer.parseInt(pageNum);
			int startRow = (currentPage - 1) * numPerPage + 1;
			int endRow = currentPage * numPerPage;

			// 페이지 수
			double totcnt = (double) totalRowCount / numPerPage;
			int totalPage = (int) Math.ceil(totcnt);

			double d_page = (double) currentPage / pagePerBlock;
			int Pages = (int) Math.ceil(d_page) - 1;
			int startPage = Pages * pagePerBlock;
			int endPage = startPage + pagePerBlock + 1;

			List list = null;
			if (totalRowCount > 0) {
				list = dao.Memberlist(startRow, endRow, word);
			} else {
				list = Collections.EMPTY_LIST;
			} // if end

			int number = 0;
			number = totalRowCount - (currentPage - 1) * numPerPage;

			mav.addObject("number", number);
			mav.addObject("pageNum", currentPage);
			mav.addObject("startRow", startRow);
			mav.addObject("endRow", endRow);
			mav.addObject("count", totalRowCount);
			mav.addObject("pageSize", pagePerBlock);
			mav.addObject("totalPage", totalPage);
			mav.addObject("startPage", startPage);
			mav.addObject("endPage", endPage);
			mav.addObject("list", list);
			return mav;
		}// Tourlist() end
		
		//렌트카 업체 목록
		@RequestMapping("/admin/rental/List.do")
		public ModelAndView Upchelist(HttpServletRequest req) {
			ModelAndView mav = new ModelAndView();
			mav.setViewName("admin/rentalcarList");

			// 입력된 검색어 확인(검색어가 있으면 검색어 존재, 검색어가 없으면 빈문자열 "")
			String word = Utility.checkNull(req.getParameter("word"));

			int totalRowCount = dao.UpchetotalRowCount(); // 총 글갯수

			// 페이징
			int numPerPage = 10; // 한 페이지당 레코드 갯수
			int pagePerBlock = 10; // 페이지 리스트

			String pageNum = req.getParameter("pageNum");
			if (pageNum == null) {
				pageNum = "1";
			}

			int currentPage = Integer.parseInt(pageNum);
			int startRow = (currentPage - 1) * numPerPage + 1;
			int endRow = currentPage * numPerPage;

			// 페이지 수
			double totcnt = (double) totalRowCount / numPerPage;
			int totalPage = (int) Math.ceil(totcnt);

			double d_page = (double) currentPage / pagePerBlock;
			int Pages = (int) Math.ceil(d_page) - 1;
			int startPage = Pages * pagePerBlock;
			int endPage = startPage + pagePerBlock + 1;

			List list = null;
			if (totalRowCount > 0) {
				list = dao.Upchelist(startRow, endRow);
			} else {
				list = Collections.EMPTY_LIST;
			} // if end

			int number = 0;
			number = totalRowCount - (currentPage - 1) * numPerPage;

			mav.addObject("number", number);
			mav.addObject("pageNum", currentPage);
			mav.addObject("startRow", startRow);
			mav.addObject("endRow", endRow);
			mav.addObject("count", totalRowCount);
			mav.addObject("pageSize", pagePerBlock);
			mav.addObject("totalPage", totalPage);
			mav.addObject("startPage", startPage);
			mav.addObject("endPage", endPage);
			mav.addObject("list", list);
			System.out.println(list);
			return mav;
		}// list() end
		
		// 렌트카 상세보기
		@RequestMapping("/admin/rentalcarRead.do")
		public ModelAndView adminrentalcarRead(HttpServletRequest req) {
			ModelAndView mav = new ModelAndView();
			rentalDTO udto = udao.read(req.getParameter("u_code"));
			mav.addObject("udto",udto);
			mav.setViewName("admin/rentalcarRead");

			int totalRowCount = dao.rentalcartotalRowCount(); // 총 글갯수

			// 페이징
			int numPerPage = 9; // 한 페이지당 레코드 갯수
			int pagePerBlock = 10; // 페이지 리스트

			String pageNum = req.getParameter("pageNum");
			if (pageNum == null) {
				pageNum = "1";
			}

			int currentPage = Integer.parseInt(pageNum);
			int startRow = (currentPage - 1) * numPerPage + 1;
			int endRow = currentPage * numPerPage;

			// 페이지 수
			double totcnt = (double) totalRowCount / numPerPage;
			int totalPage = (int) Math.ceil(totcnt);

			double d_page = (double) currentPage / pagePerBlock;
			int Pages = (int) Math.ceil(d_page) - 1;
			int startPage = Pages * pagePerBlock;
			int endPage = startPage + pagePerBlock + 1;

			List list = null;
			if (totalRowCount > 0) {
				list = dao.rentalcarlist(startRow, endRow);
				
			} else {
				list = Collections.EMPTY_LIST;
			} // if end

			int number = 0;
			number = totalRowCount - (currentPage - 1) * numPerPage;

			mav.addObject("number", number);
			mav.addObject("pageNum", currentPage);
			mav.addObject("startRow", startRow);
			mav.addObject("endRow", endRow);
			mav.addObject("count", totalRowCount);
			mav.addObject("pageSize", pagePerBlock);
			mav.addObject("totalPage", totalPage);
			mav.addObject("startPage", startPage);
			mav.addObject("endPage", endPage);
			mav.addObject("list", list);

			return mav;
		}// rentalcarRead() end

}
