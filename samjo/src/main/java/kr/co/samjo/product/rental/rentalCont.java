package kr.co.samjo.product.rental;

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

@Controller
public class rentalCont {
	rentalDAO dao = null;

	public rentalCont() {
		dao = new rentalDAO();
		System.out.println("-----rentalCont객체 생성됨");
	}

// Ins
	@RequestMapping(value = "admin/rentalIns.do", method = RequestMethod.GET)
	public ModelAndView Ins() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("rentalcar/rentalIns");
		return mav;
	}// Ins() end

// InsProc
	@RequestMapping(value = "admin/rentalIns.do", method = RequestMethod.POST)
	public ModelAndView Ins(@ModelAttribute rentalDTO dto, HttpServletRequest req) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("rentalcar/msgView");

		// ----------------------------------------------------------------------------
		// 첨부된 파일 처리
		// ->실제 파일은 /storage폴더에 저장
		// ->저장된 파일 관련 정보는 media테이블에 저장

		// 파일 저장 폴더의 실제 물리적인 경로 가져오기
		String basePath = req.getRealPath("/storage");

		// 1)<input type='file' name='posterMF' size='50'>
		MultipartFile posterMF = dto.getPosterMF(); // 파일 가져오기
		// storage폴더에 파일을 저장하고, 리네임된 파일명 반환
		String poster = UploadSaveManager.saveFileSpring30(posterMF, basePath);
		dto.setU_img(poster);// 리네임된 파일명을 dto객체 담기

		int cnt = dao.create(dto);
		if (cnt == 0) {
			String msg = "<p>렌트카 업체 등록 실패</p>";
			String img = "<img src='../images/fail.png'>";
			String link1 = "<input type='button' value='다시시도' onclick='javascript:history.back()'>";
			String link2 = "<input type='button' value='렌트카 업체 목록' onclick=\"location.href='/../rentalcar/upcheList.do'\">";
			mav.addObject("msg", msg);
			mav.addObject("img", img);
			mav.addObject("link1", link1);
			mav.addObject("link2", link2);
		} else {
			String msg = "<p>렌트카 업체 등록 성공</p>";
			String img = "<img src='../images/sound.png'>";
			String link1 = "<input type='button' value='계속등록' onclick=\"location.href='/../rentalcar/upcheList.do'\">";
			String link2 = "<input type='button' value='렌트카 업체 목록' onclick=\"location.href='/../rentalcar/upcheList.do'\">";
			mav.addObject("msg", msg);
			mav.addObject("img", img);
			mav.addObject("link1", link1);
			mav.addObject("link2", link2);
		} // if end
		return mav;
	}// InsProc() end

//upcheList
	@RequestMapping("rentalcar/upcheList.do")
	public ModelAndView List(HttpServletRequest req) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("rentalcar/upcheList");

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
			list = dao.list(startRow, endRow);
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

//Delete	
	@RequestMapping(value = "admin/rentalDelete.do", method = RequestMethod.GET)
	public ModelAndView Delete(String u_code) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("rentalcar/rentalDelete");
		rentalDTO dto = dao.read(u_code);// 수정하고자 하는 행 가져오기
		mav.addObject("dto", dto);
		return mav;
	}// deleteForm() end

	// DeleteProc
	@RequestMapping(value = "admin/rentalDelete.do", method = RequestMethod.POST)
	public ModelAndView DeleteProc(String u_code, HttpServletRequest req) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("rentalcar/msgView");

		System.out.println(u_code);
		// 삭제하고자 하는 글정보 가져오기(storage폴더에서 삭제할 파일명을 보관하기 위해)
		rentalDTO oldDTO = dao.read(u_code);

		int cnt = dao.delete(u_code);
		
		if (cnt == 0) {
			String msg = "<p>렌트카 업체 삭제 실패!!</p>";
			String img = "<img src='../images/fail.png'>";
			String link1 = "<input type='button' value='다시시도' onclick='javascript:history.back()'>";
			String link2 = "<input type='button' value='렌트카 업체 목록' onclick=\"location.href='/admin/rentalcarList.do'\">";
			mav.addObject("msg", msg);
			mav.addObject("img", img);
			mav.addObject("link1", link1);
			mav.addObject("link2", link2);
		} else {
			String msg = "<p>렌트카 업체가 삭제되었습니다</p>";
			String img = "<img src='../images/sound.png'>";
			String link2 = "<input type='button' value='렌트카 업체 목록' onclick=\"location.href='/admin/rentalcarList.do'\">";
			mav.addObject("msg", msg);
			mav.addObject("img", img);
			mav.addObject("link2", link2);
		} // if end
		return mav;
	}// deleteProc() end

// Update
	@RequestMapping(value = "admin/rentalUpdate.do", method = RequestMethod.GET)
	public ModelAndView Update(String u_code) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("rentalcar/rentalUpdate");
		rentalDTO dto = dao.read(u_code);// 수정하고자 하는 행 가져오기
		mav.addObject("dto", dto);
		return mav;
	}// updateForm() end

// UpdateProc
	@RequestMapping(value = "admin/rentalUpdate.do", method = RequestMethod.POST)
	public ModelAndView updateProc(@ModelAttribute rentalDTO dto, HttpServletRequest req) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("rentalcar/msgView");
		

		// ----------------------------------------------------------------------------
		// 첨부된 파일 처리
		// ->실제 파일은 /storage폴더에 저장
		// ->저장된 파일 관련 정보는 media테이블에 저장

		// 파일 저장 폴더의 실제 물리적인 경로 가져오기
		String basePath = req.getRealPath("/storage");

		// 1)<input type='file' name='posterMF' size='50'>
		MultipartFile posterMF = dto.getPosterMF(); // 파일 가져오기
		// storage폴더에 파일을 저장하고, 리네임된 파일명 반환
		String poster = UploadSaveManager.saveFileSpring30(posterMF, basePath);
		dto.setU_img(poster);// 리네임된 파일명을 dto객체 담기

		int cnt = dao.update(dto);

		if (cnt == 0) {
			String msg = "<p>렌트카 업체 수정 실패!!</p>";
			String img = "<img src='../images/fail.png'>";
			String link1 = "<input type='button' value='다시시도' onclick='javascript:history.back()'>";
			String link2 = "<input type='button' value='렌트카 업체 목록' onclick=\"location.href='/../rentalcar/upcheList.do'\">";
			mav.addObject("msg", msg);
			mav.addObject("img", img);
			mav.addObject("link1", link1);
			mav.addObject("link2", link2);
		} else {
			String msg = "<p>렌트카 업체가 수정되었습니다</p>";
			String img = "<img src='../images/sound.png'>";
			String link2 = "<input type='button' value='렌트카 업체 목록' onclick=\"location.href='/../rentalcar/upcheList.do'\">";
			mav.addObject("msg", msg);
			mav.addObject("img", img);
			mav.addObject("link2", link2);
		} // if end

		return mav;

	}// UpdateProc() end
}
