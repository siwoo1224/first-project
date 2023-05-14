package kr.co.samjo.board2;

import org.springframework.web.multipart.MultipartFile;

public class boardDTO {//전송객체
    //멤버변수 field
    private int bbs_idx;
    private String bbs_img;
    private String bbs_id;
    private String bbs_title;
    private String bbs_content;
    private int bbs_count;
    private String bbs_date;
    
    public boardDTO() {}


    //--------------------------------------------------------
    //첨부된 파일을 저장하기 위해서(createForm.jsp참조)
    private MultipartFile posterMF;
    
   public void setPosterMF(MultipartFile posterMF) {
      this.posterMF = posterMF;
   }  
   
    public MultipartFile getPosterMF() {
        return posterMF;
    }

//--------------------------------------------------------


   public int getBbs_idx() {
      return bbs_idx;
   }

   public void setBbs_idx(int bbs_idx) {
      this.bbs_idx = bbs_idx;
   }

   public String getBbs_img() {
      return bbs_img;
   }

   public void setBbs_img(String bbs_img) {
      this.bbs_img = bbs_img;
   }

   public String getBbs_id() {
      return bbs_id;
   }

   public void setBbs_id(String bbs_id) {
      this.bbs_id = bbs_id;
   }

   public String getBbs_title() {
      return bbs_title;
   }

   public void setBbs_title(String bbs_title) {
      this.bbs_title = bbs_title;
   }

   public String getBbs_content() {
      return bbs_content;
   }

   public void setBbs_content(String bbs_content) {
      this.bbs_content = bbs_content;
   }

   public int getBbs_count() {
      return bbs_count;
   }

   public void setBbs_count(int bbs_count) {
      this.bbs_count = bbs_count;
   }

   public String getBbs_date() {
      return bbs_date;
   }

   public void setBbs_date(String bbs_date) {
      this.bbs_date = bbs_date;
   }

   @Override
   public String toString() {
      return "boardDTO [bbs_idx=" + bbs_idx + ", bbs_img=" + bbs_img + ", bbs_id=" + bbs_id + ", bbs_title="
            + bbs_title + ", bbs_content=" + bbs_content + ", bbs_count=" + bbs_count + ", bbs_date=" + bbs_date
            + ", posterMF=" + posterMF + "]";
   }

}