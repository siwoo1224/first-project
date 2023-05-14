package kr.co.samjo.product.packagetour;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import kr.co.samjo.tour.TourDTO;
import net.utility.UploadSaveManager;
import net.utility.Utility;

@Controller
public class packagetourCont {
	packagetourDAO dao = null;
	
	public packagetourCont() {
		dao = new packagetourDAO();
		System.out.println("-----packagetourCont객체 생성됨");
	}
	
	
//Ins	
	@RequestMapping(value = "admin/packagetourIns.do", method = RequestMethod.GET)
	public ModelAndView Ins() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("packagetour/Ins");
		return mav;
	}//Ins() end
	

	
//InsProc
		@RequestMapping(value = "admin/packagetourIns.do", method = RequestMethod.POST)
		public ModelAndView bbsIns(@ModelAttribute packagetourDTO dto, HttpServletRequest req) {
			ModelAndView mav = new ModelAndView();
			mav.setViewName("packagetour/msgView");
			
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
			dto.setPack_img(poster);// 리네임된 파일명을 dto객체 담기

			int cnt = dao.create(dto);
			if (cnt == 0) {
				String msg = "<p>패키지여행 등록 실패</p>";
				String img = "<img src='../images/fail.png'>";
				String link1 = "<input type='button' value='다시시도' onclick='javascript:history.back()'>";
				String link2 = "<input type='button' value='패키지여행 목록' onclick='location.href=\"packagetour/List.do\"'>";
				mav.addObject("msg", msg);
				mav.addObject("img", img);
				mav.addObject("link1", link1);
				mav.addObject("link2", link2);
			} else {
				String msg = "<p>패키지여행 등록 성공</p>";
				String img = "<img src='../images/sound.png'>";
				String link1 = "<input type='button' value='계속등록' onclick='location.href=\"packagetourIns.do\"'>";
				String link2 = "<input type='button' value='패키지여행 목록' onclick='location.href=\"packagetour/List.do\"'>";
				mav.addObject("msg", msg);
				mav.addObject("img", img);
				mav.addObject("link1", link1);
				mav.addObject("link2", link2);
			} // if end
			return mav;
		}//InsProc() end
		
		
		
//List	
		@RequestMapping("packagetour/List.do")
		public ModelAndView bbsList(HttpServletRequest req) {
			String word = Utility.checkNull(req.getParameter("word"));
			ModelAndView mav = new ModelAndView();
			mav.setViewName("packagetour/List");

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
			
		
		
		
//Read	
		@RequestMapping("/packagetour/List/Read.do")
		public ModelAndView read(String pack_no, HttpServletRequest req) {
			ModelAndView mav = new ModelAndView();
			packagetourDTO dto = dao.read(pack_no);
			mav.setViewName("packagetour/Read");
			mav.addObject("dto", dto);
			
			int totalRowCount=dao.reviewtotalRowCount(pack_no); //총 글갯수
		       
	        //페이징
	        int numPerPage   = 9;    // 한 페이지당 레코드 갯수
	        int pagePerBlock = 10;   // 페이지 리스트
	       
	        String pageNum=req.getParameter("pageNum");
	        if(pageNum==null){
	              pageNum="1";
	        }
	       
	        int currentPage=Integer.parseInt(pageNum);
	        int startRow   =(currentPage-1)*numPerPage+1;
	        int endRow     =currentPage*numPerPage;
	       
	        //페이지 수
	        double totcnt = (double)totalRowCount/numPerPage;
	        int totalPage = (int)Math.ceil(totcnt);
	         
	        double d_page = (double)currentPage/pagePerBlock;
	        int Pages     = (int)Math.ceil(d_page)-1;
	        int startPage = Pages*pagePerBlock;
	        int endPage   = startPage+pagePerBlock+1;
	       
	       
	        List list=null;     
	        if(totalRowCount>0){           
	              list=dao.reviewList(pack_no, startRow, endRow);          
	        } else {           
	              list=Collections.EMPTY_LIST;           
	        }//if end
	         
	        int number=0;
	        number=totalRowCount-(currentPage-1)*numPerPage;
	         
	        mav.addObject("number",    number);
	        mav.addObject("pageNum",   currentPage);
	        mav.addObject("startRow",  startRow);
	        mav.addObject("endRow",    endRow);
	        mav.addObject("count",     totalRowCount);
	        mav.addObject("pageSize",  pagePerBlock);
	        mav.addObject("totalPage", totalPage);
	        mav.addObject("startPage", startPage);
	        mav.addObject("endPage",   endPage);
	        mav.addObject("list", list);
			return mav;
		}// read() end
		
		
		
//Delete	
		@RequestMapping(value = "/admin/packagetourDelete.do", method = RequestMethod.GET)
		public ModelAndView Delete(String pack_no) {
			ModelAndView mav = new ModelAndView();
			mav.setViewName("packagetour/Delete");
			packagetourDTO dto = dao.read(pack_no);// 수정하고자 하는 행 가져오기
			mav.addObject("dto", dto);
			return mav;
		}// deleteForm() end
		
		
		
//DeleteProc	
		@RequestMapping(value = "/admin/packagetourDelete.do", method = RequestMethod.POST)
		public ModelAndView bbsDeleteProc(String pack_no, HttpServletRequest req) {
			ModelAndView mav = new ModelAndView();
			mav.setViewName("packagetour/msgView");

			// 삭제하고자 하는 글정보 가져오기(storage폴더에서 삭제할 파일명을 보관하기 위해)
			packagetourDTO oldDTO = dao.read(pack_no);

			int cnt = dao.delete(pack_no);
			if (cnt == 0) {
				String msg = "<p>패키지여행 삭제 실패!!</p>";
				String img = "<img src='../images/fail.png'>";
				String link1 = "<input type='button' value='다시시도' onclick='javascript:history.back()'>";
				String link2 = "<input type='button' value='패키지여행 목록' onclick=\"location.href='/admin/packagetour/List.do'\">";
				mav.addObject("msg", msg);
				mav.addObject("img", img);
				mav.addObject("link1", link1);
				mav.addObject("link2", link2);
			} else {
				String msg = "<p>패키지여행이 삭제되었습니다</p>";
				String img = "<img src='../images/sound.png'>";
				String link2 = "<input type='button' value='패키지여행 목록' onclick=\"location.href='/admin/packagetour/List.do'\">";
				mav.addObject("msg", msg);
				mav.addObject("img", img);
				mav.addObject("link2", link2);
				// 첨부했던 파일 삭제
				String basePath = req.getRealPath("/storage");
				UploadSaveManager.deleteFile(basePath, oldDTO.getPack_img());
			} // if end
			return mav;
		}// deleteProc() end

		
		
//Update	
		@RequestMapping(value = "/admin/packagetourUpdate.do", method = RequestMethod.GET)
		public ModelAndView bbsUpdate(String pack_no) {
			ModelAndView mav = new ModelAndView();
			mav.setViewName("packagetour/Update");
			packagetourDTO dto = dao.read(pack_no);// 수정하고자 하는 행 가져오기
			mav.addObject("dto", dto);
			return mav;
		}// updateForm() end

		
		
//UpdateProc
		@RequestMapping(value = "/admin/packagetourUpdate.do", method = RequestMethod.POST)
		public ModelAndView updateProc(@ModelAttribute packagetourDTO dto, HttpServletRequest req) {
			ModelAndView mav = new ModelAndView();
			mav.setViewName("packagetour/msgView");

			String basePath = req.getRealPath("/storage");
			packagetourDTO oldDTO = dao.read(dto.getPack_no()); // 기존에 저장된 정보
			
			// 파일을 수정할 것인지?

			// 1)
			MultipartFile posterMF = dto.getPosterMF();
			if (posterMF.getSize() > 0) { // 새로운 포스터 파일이 첨부되서 전송되었는지?
				// 기존 파일 삭제
				UploadSaveManager.deleteFile(basePath, oldDTO.getPack_img());
				// 신규 파일 저장
				String poster = UploadSaveManager.saveFileSpring30(posterMF, basePath);
				dto.setPack_img(poster); // 새롭게 첨부된 신규 파일명
				
			} else {
				// 포스터 파일을 수정하지 않는 경우
				dto.setPack_img(oldDTO.getPack_img()); // 기존에 저장된 파일명
			} // if end
			
			int cnt = dao.update(dto);
			
			if (cnt == 0) {
				String msg = "<p>패키지 여행 수정 실패!!</p>";
				String img = "<img src='../images/fail.png'>";
				String link1 = "<input type='button' value='다시시도' onclick='javascript:history.back()'>";
				String link2 = "<input type='button' value='패키지여행 목록' onclick=\"location.href='/admin/packagetour/List.do'\">";
				mav.addObject("msg", msg);
				mav.addObject("img", img);
				mav.addObject("link1", link1);
				mav.addObject("link2", link2);
			} else {
				String msg = "<p>패키지 여행이 수정되었습니다</p>";
				String img = "<img src='../images/sound.png'>";
				String link2 = "<input type='button' value='패키지여행 목록' onclick=\"location.href='/admin/packagetour/List.do'\">";
				mav.addObject("msg", msg);
				mav.addObject("img", img);
				mav.addObject("link2", link2);
			} // if end

			return mav;

		}// UpdateProc() end
		
		
}
