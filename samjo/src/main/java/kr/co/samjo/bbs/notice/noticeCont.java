package kr.co.samjo.bbs.notice;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import net.utility.UploadSaveManager;
import net.utility.Utility;

@Controller
public class noticeCont {

	noticeDAO dao = null;

	public noticeCont() {
		dao = new noticeDAO();// DB연결 객체 생성
		System.out.println("-----noticeCont객체 생성됨");
	}// end
//bbsIns

	@RequestMapping(value = "/admin/notice/create.do", method = RequestMethod.GET)
	public ModelAndView bbsIns() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("notice/bbsIns");
		return mav;
	}// bbsIns() end

// bbsInsProc
	@RequestMapping(value = "/admin/notice/create.do", method = RequestMethod.POST)
	public ModelAndView bbsIns(@ModelAttribute noticeDTO dto) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("notice/msgView");

		int cnt = dao.create(dto);
		if (cnt == 0) {
			String msg = "<p>공지사항 등록 실패</p>";
			String img = "<img src='../images/fail.png'>";
			String link1 = "<input type='button' value='다시시도' onclick='javascript:history.back()'>";
			String link2 = "<input type='button' value='공지사항 목록' onclick='location.href=\"List.do\"'>";
			mav.addObject("msg", msg);
			mav.addObject("img", img);
			mav.addObject("link1", link1);
			mav.addObject("link2", link2);
		} else {
			String msg = "<p>공지사항 등록 성공</p>";
			String img = "<img src='../images/sound.png'>";
			String link1 = "<input type='button' value='계속등록' onclick='location.href=\"create.do\"'>";
			String link2 = "<input type='button' value='공지사항 목록' onclick='location.href=\"List.do\"'>";
			mav.addObject("msg", msg);
			mav.addObject("img", img);
			mav.addObject("link1", link1);
			mav.addObject("link2", link2);
		} // if end
		return mav;
	}// bbsInsProc() end

	//List	
	@RequestMapping("notice/List.do")
	public ModelAndView bbsList(HttpServletRequest req) {
		
		//입력된 검색어 확인(검색어가 있으면 검색어 존재, 검색어가 없으면 빈문자열 "")
        String word = Utility.checkNull(req.getParameter("word"));
        String col = Utility.checkNull(req.getParameter("col"));
        
		ModelAndView mav = new ModelAndView();
		mav.setViewName("notice/bbsList");

		int totalRowCount = dao.totalRowCount(); // 총 글갯수

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
			list = dao.list(startRow, endRow, col, word);
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





//Read
	@RequestMapping("notice/read.do")
	public ModelAndView bbsRead(int board_no) {
		ModelAndView mav = new ModelAndView();
		noticeDTO dto = dao.read(board_no);
		mav.setViewName("notice/bbsRead");
		mav.addObject("dto", dto);
		return mav;
	}// read() end

//Delete	
	@RequestMapping(value = "/admin/notice/delete.do", method = RequestMethod.GET)
	public ModelAndView bbsDelete(int board_no) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("notice/bbsDelete");
		noticeDTO dto = dao.read(board_no);// 수정하고자 하는 행 가져오기
		mav.addObject("dto", dto);
		return mav;
	}// deleteForm() end

//DeleteProc	
	@RequestMapping(value = "/admin/notice/delete.do", method = RequestMethod.POST)
	public ModelAndView bbsDeleteProc(int board_no, HttpServletRequest req) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("notice/msgView");

		// 삭제하고자 하는 글정보 가져오기(storage폴더에서 삭제할 파일명을 보관하기 위해)
		noticeDTO oldDTO = dao.read(board_no);

		int cnt = dao.delete(board_no);
		if (cnt == 0) {
			String msg = "<p>공지사항 삭제 실패!!</p>";
			String img = "<img src='../images/fail.png'>";
			String link1 = "<input type='button' value='다시시도' onclick='javascript:history.back()'>";
			String link2 = "<input type='button' value='공지사항 목록' onclick=\"location.href='List.do'\">";
			mav.addObject("msg", msg);
			mav.addObject("img", img);
			mav.addObject("link1", link1);
			mav.addObject("link2", link2);
		} else {
			String msg = "<p>공지사항이 삭제되었습니다</p>";
			String img = "<img src='../images/sound.png'>";
			String link2 = "<input type='button' value='공지사항 목록' onclick=\"location.href='List.do'\">";
			mav.addObject("msg", msg);
			mav.addObject("img", img);
			mav.addObject("link2", link2);
		} // if end
		return mav;
	}// deleteProc() end

//Update	
	@RequestMapping(value = "/admin/notice/update.do", method = RequestMethod.GET)
	public ModelAndView bbsUpdate(int board_no) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("notice/bbsUpdate");
		noticeDTO dto = dao.read(board_no);// 수정하고자 하는 행 가져오기
		mav.addObject("dto", dto);
		return mav;
	}// updateForm() end

//UpdateProc
	@RequestMapping(value = "/admin/notice/update.do", method = RequestMethod.POST)
	public ModelAndView updateProc(@ModelAttribute noticeDTO dto) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("notice/msgView");

		int cnt = dao.update(dto);
		
		if (cnt == 0) {
			String msg = "<p>공지사항 수정 실패!!</p>";
			String img = "<img src='../images/fail.png'>";
			String link1 = "<input type='button' value='다시시도' onclick='javascript:history.back()'>";
			String link2 = "<input type='button' value='공지사항 목록' onclick=\"location.href='List.do'\">";
			mav.addObject("msg", msg);
			mav.addObject("img", img);
			mav.addObject("link1", link1);
			mav.addObject("link2", link2);
		} else {
			String msg = "<p>공지사항이 수정되었습니다</p>";
			String img = "<img src='../images/sound.png'>";
			String link2 = "<input type='button' value='공지사항 목록' onclick=\"location.href='List.do'\">";
			mav.addObject("msg", msg);
			mav.addObject("img", img);
			mav.addObject("link2", link2);
		} // if end

		return mav;

	}// UpdateProc() end
}
