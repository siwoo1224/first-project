package kr.co.samjo.product.sookso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import kr.co.samjo.cart.cartDAO;
import kr.co.samjo.cart.cartDTO;
import net.utility.UploadSaveManager;
import net.utility.Utility;


@Controller
public class SooksoCont {

	SooksoDAO dao =null;
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy. MM. dd. a HH:mm:ss");
	
	public SooksoCont() {
		dao = new SooksoDAO();//DB연결 객체 생성
		System.out.println("-----SooksoCont() 객체 생성됨");
	}//end

	
	@RequestMapping(value = "admin/sookso/create.do", method = RequestMethod.GET)
	public String createFrom() {
		return "sookso/createForm";
	}//createForm() end

	@RequestMapping(value = "/admin/sookso/create.do", method = RequestMethod.POST)
	public ModelAndView create(@ModelAttribute SooksoDTO dto, HttpServletRequest req) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("sookso/msgView");
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
		dto.setS_img(poster);// 리네임된 파일명을 dto객체 담기
		
		int cnt = dao.create(dto);
		if (cnt == 0) {
			String msg = "<p>숙소 등록 실패</p>";
			String link1 = "<input type='button' value='다시시도' onclick='javascript:history.back()'>";
			String link2 = "<input type='button' value='목록으로' onclick=\"location.href='/admin/sookso/List.do'\">";
			mav.addObject("msg", msg);
			mav.addObject("link1", link1);
			mav.addObject("link2", link2);
		} else {
			String msg = "<p>숙소 등록 성공</p>";
			String img = "<img src='../images/sound.png'>";
			String link2 = "<input type='button' value='목록으로' onclick=\"location.href='/admin/sookso/List.do'\">";
			mav.addObject("msg", msg);
			mav.addObject("img", img);
			mav.addObject("link2", link2);
		} // if end

		return mav;
	}// createProc() end
	
	@RequestMapping(value = "admin/sookso/roomcreate.do", method = RequestMethod.GET)
	public ModelAndView createForm(String s_cn) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("sookso/createForm2");
		SooksoDTO dto = dao.read(s_cn);
		mav.addObject("dto", dto);
		return mav;
	}// createForm() end

	@RequestMapping(value = "/admin/sookso/roomcreate.do", method = RequestMethod.POST)
	public ModelAndView create2(@ModelAttribute SooksoDTO dto, HttpServletRequest req) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("sookso/msgView");
		
		String basePath = req.getRealPath("/storage");

		// 1)<input type='file' name='posterMF' size='50'>
		MultipartFile posterMF = dto.getPosterMF2(); // 파일 가져오기
		// storage폴더에 파일을 저장하고, 리네임된 파일명 반환
		String poster = UploadSaveManager.saveFileSpring30(posterMF, basePath);
		dto.setRoom_img(poster);// 리네임된 파일명을 dto객체 담기
		
		int cnt = dao.create2(dto);
		if (cnt == 0) {
			String msg = "<p>방 등록 실패</p>";
			String link1 = "<input type='button' value='다시시도' onclick='javascript:history.back()'>";
			String link2 = "<input type='button' value='목록으로' onclick=\"location.href='/admin/sookso/List.do'\">";
			mav.addObject("msg", msg);
			mav.addObject("link1", link1);
			mav.addObject("link2", link2);
		} else {
			String msg = "<p>방 등록 성공</p>";
			String img = "<img src='../images/sound.png'>";
			String link2 = "<input type='button' value='목록으로' onclick=\"location.href='/admin/sookso/List.do'\">";
			mav.addObject("msg", msg);
			mav.addObject("img", img);
			mav.addObject("link2", link2);
		} // if end

		return mav;
	}// createProc() end
	
	@RequestMapping("/sookso/List.do")
    public ModelAndView list(HttpServletRequest req) {
		String word = Utility.checkNull(req.getParameter("word"));
        ModelAndView mav=new ModelAndView();
        mav.setViewName("sookso/List");
       
        int totalRowCount=dao.totalRowCount(); //총 글갯수
       
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
              list=dao.list(startRow, endRow, word);          
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
    }//list() end
	
	
	@RequestMapping("/sookso/List/read.do")
	public ModelAndView read(String s_cn, String room_cn, HttpServletRequest req) {
		ModelAndView mav = new ModelAndView();
		SooksoDTO dto = dao.read(s_cn);
		mav.setViewName("sookso/read");
		mav.addObject("dto", dto);
		
		int totalRowCount=dao.totalRowCount(); //총 글갯수
	       
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
              list=dao.list2(startRow, endRow, s_cn);
        } else {           
              list=Collections.EMPTY_LIST;           
        }//if end
        
        List list2=null;     
        if(totalRowCount>0){           
              list2=dao.reviewList(startRow, endRow, s_cn);
        } else {           
              list2=Collections.EMPTY_LIST;           
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
        mav.addObject("list2", list2);
		
        return mav;
    }//read() end

	
    

	@RequestMapping(value = "/admin/sookso/update.do", method = RequestMethod.GET)
	public ModelAndView updateForm(String s_cn) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("sookso/updateForm");
		SooksoDTO dto = dao.read(s_cn);// 수정하고자 하는 행 가져오기
		mav.addObject("dto", dto);
		return mav;
	}// updateForm() end

	@RequestMapping(value = "/admin/sookso/update.do", method = RequestMethod.POST)
	public ModelAndView update(@ModelAttribute SooksoDTO dto, HttpServletRequest req) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("sookso/msgView");

		String basePath = req.getRealPath("/storage");
		SooksoDTO oldDTO = dao.read(dto.getS_cn()); // 기존에 저장된 정보 가져오기
		// ---------------------------------------------------------------------
		// 파일을 수정할 것인지?

		// 1)
		MultipartFile posterMF = dto.getPosterMF();
		if (posterMF.getSize() > 0) { // 새로운 포스터 파일이 첨부되서 전송되었는지?
			// 기존 파일 삭제
			UploadSaveManager.deleteFile(basePath, oldDTO.getS_img());
			// 신규 파일 저장
			String poster = UploadSaveManager.saveFileSpring30(posterMF, basePath);
			dto.setS_img(poster); // 새롭게 첨부된 신규 파일명
			
		} else {
			// 포스터 파일을 수정하지 않는 경우
			dto.setS_img(oldDTO.getS_img()); // 기존에 저장된 파일명
		} // if end

			// ---------------------------------------------------------------------

		int cnt = dao.update(dto);
		if (cnt == 0) {
			String msg = "<p>숙소 수정 실패!!</p>";
			String link1 = "<input type='button' value='다시시도' onclick='javascript:history.back()'>";
			String link2 = "<input type='button' value='목록으로' onclick=\\\"location.href='/admin/sookso/List.do'\\\">";
			mav.addObject("msg", msg);
			mav.addObject("link1", link1);
			mav.addObject("link2", link2);
		} else {
			String msg = "<p>숙소가 수정 되었습니다</p>";
			String link2 = "<input type='button' value='목록으로' onclick=\\\"location.href='/admin/sookso/List.do'\\\">";
			mav.addObject("msg", msg);
			mav.addObject("link2", link2);
		} // if end

		return mav;
	}// updateProc() end
	
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	@RequestMapping(value = "/admin/sookso/roomupdate.do", method = RequestMethod.GET)
	public ModelAndView updateForm2(String s_cn) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("sookso/updateForm2");
		SooksoDTO dto = dao.read(s_cn);// 수정하고자 하는 행 가져오기 
		mav.addObject("dto", dto);
		return mav;
	}// updateForm() end

	@RequestMapping(value = "/admin/sookso/roomupdate.do", method = RequestMethod.POST)
	public ModelAndView update2(@ModelAttribute SooksoDTO dto, HttpServletRequest req) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("tour/msgView");

		String basePath = req.getRealPath("/storage");
		SooksoDTO oldDTO = dao.read(dto.getS_cn()); // 기존에 저장된 정보 가져오기
		// ---------------------------------------------------------------------
		// 파일을 수정할 것인지?

		// 1)
		MultipartFile posterMF2 = dto.getPosterMF2();
		if (posterMF2.getSize() > 0) { // 새로운 포스터 파일이 첨부되서 전송되었는지?
			// 기존 파일 삭제
			UploadSaveManager.deleteFile(basePath, oldDTO.getRoom_img());
			// 신규 파일 저장
			String poster = UploadSaveManager.saveFileSpring30(posterMF2, basePath);
			dto.setRoom_img(poster); // 새롭게 첨부된 신규 파일명
			
		} else {
			// 포스터 파일을 수정하지 않는 경우
			dto.setRoom_img(oldDTO.getRoom_img()); // 기존에 저장된 파일명
		} // if end

			// ---------------------------------------------------------------------

		int cnt = dao.update2(dto);
		if (cnt == 0) {
			String msg = "<p>방 수정 실패!!</p>";
			String link1 = "<input type='button' value='다시시도' onclick='javascript:history.back()'>";
			String link2 = "<input type='button' value='방목록' onclick=\\\"location.href='/admin/sookso/List.do'\\\">";
			mav.addObject("msg", msg);
			mav.addObject("link1", link1);
			mav.addObject("link2", link2);
		} else {
			String msg = "<p>방 수정 되었습니다</p>";
			String link2 = "<input type='button' value='방목록' onclick=\\\"location.href='/admin/sookso/List.do'\\\">";
			mav.addObject("msg", msg);
			mav.addObject("link2", link2);
		} // if end

		return mav;
	}// updateProc() end
	/////////////////////////////////////////////////////////////////////////////////////////////
	@RequestMapping(value = "/admin/sookso/delete.do", method = RequestMethod.GET)
	public ModelAndView deleteForm(String s_cn) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("sookso/deleteForm");
		SooksoDTO dto = dao.read(s_cn);// 수정하고자 하는 행 가져오기
		mav.addObject("dto", dto);
		return mav;
	}// deleteForm() end

	@RequestMapping(value = "/admin/sookso/delete.do", method = RequestMethod.POST)
	public ModelAndView deleteProc(String s_cn, HttpServletRequest req) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("sookso/msgView");

		// 삭제하고자 하는 글정보 가져오기(storage폴더에서 삭제할 파일명을 보관하기 위해)
		SooksoDTO oldDTO = dao.read(s_cn);

		int cnt = dao.delete(s_cn);
		if (cnt == 0) {
			String msg = "<p>숙소 삭제 실패!!</p>";
			String link1 = "<input type='button' value='다시시도' onclick='javascript:history.back()'>";
			String link2 = "<input type='button' value='목록으로' onclick=\"location.href='List.do'\">";
			mav.addObject("msg", msg);
			mav.addObject("link1", link1);
			mav.addObject("link2", link2);
		} else {
			String msg = "<p>숙소가 삭제되었습니다</p>";
			String link2 = "<input type='button' value='목록으로' onclick=\"location.href='List.do'\">";
			mav.addObject("msg", msg);
			mav.addObject("link2", link2);
			// 첨부했던 파일 삭제
			String basePath = req.getRealPath("/storage");
			UploadSaveManager.deleteFile(basePath, oldDTO.getS_img());
		} // if end
		return mav;
	}// deleteProc() end
	
	////////////////////////////////////////////////////////////////////////////////////////////
	@RequestMapping(value = "/admin/sookso/roomdelete.do", method = RequestMethod.GET)
	public ModelAndView deleteForm2(String s_cn) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("sookso/deleteForm2");
		SooksoDTO dto = dao.read(s_cn);// 삭제하고자 하는 행 가져오기
		mav.addObject("dto", dto);
		return mav;
	}// deleteForm() end

	@RequestMapping(value = "/admin/sookso/roomdelete.do", method = RequestMethod.POST)
	public ModelAndView deleteProc2(String s_cn,String room_cn, HttpServletRequest req) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("sookso/msgView");

		// 삭제하고자 하는 글정보 가져오기(storage폴더에서 삭제할 파일명을 보관하기 위해)
		SooksoDTO oldDTO = dao.read(s_cn);

		int cnt = dao.delete2(s_cn, room_cn);
		if (cnt == 0) {
			String msg = "<p>방 삭제 실패!!</p>";
			String link1 = "<input type='button' value='다시시도' onclick='javascript:history.back()'>";
			String link2 = "<input type='button' value='방 목록' onclick=\\\"location.href='/admin/sookso/List.do'\\\">";
			mav.addObject("msg", msg);
			mav.addObject("link1", link1);
			mav.addObject("link2", link2);
		} else {
			String msg = "<p>방이 삭제되었습니다</p>";
			String link2 = "<input type='button' value='방 목록' onclick=\\\"location.href='/admin/sookso/List.do'\\\">";
			mav.addObject("msg", msg);
			mav.addObject("link2", link2);
			// 첨부했던 파일 삭제
			String basePath = req.getRealPath("/storage");
			UploadSaveManager.deleteFile(basePath, oldDTO.getRoom_img());
		} // if end
		return mav;
	}// deleteProc() end
	
	
	//////////////////////////////////////////////////////////////
	
	//장바구니 등록
		@RequestMapping("cart/addCart.do")
		public ModelAndView addCart(HttpServletRequest request, HttpServletResponse response) throws ParseException {
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
			if (cnt == 0) {
				String msg = "<p>장바구니 등록 실패</p>";
				String img = "<img src='../images/fail.png'>";
				String link1 = "<input type='button' value='다시시도' onclick='javascript:history.back()'>";
				String link2 = "<input type='button' value='장바구니목록' onclick='location.href=\"list.do\"'>";
				mav.addObject("msg", msg);
				mav.addObject("img", img);
				mav.addObject("link1", link1);
				mav.addObject("link2", link2);
			} else {
				String msg = "<p>장바구니 등록 성공</p>";
				String img = "<img src='../images/sound.png'>";
				String link2 = "<input type='button' value='장바구니목록' onclick='location.href=\"list.do\"'>";
				mav.addObject("msg", msg);
				mav.addObject("img", img);
				mav.addObject("link2", link2);
			}
			return mav;
		}

}
