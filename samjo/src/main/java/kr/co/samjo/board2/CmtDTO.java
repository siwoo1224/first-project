package kr.co.samjo.board2;

public class CmtDTO {

   public CmtDTO() {}
   
   private int cmt_idx;         //댓글인덱스
    private String cmt_id;         //회원아이디
    private String cmt_content;      //댓글내용
    private int cmt_bbs_idx;      //게시글번호
    private int cmt_ref;         //그룹번호
    private int cmt_re_setp;      //들여쓰기
    private int cmt_level;         //
    private String cmt_date;      //
    
   public int getCmt_idx() {
      return cmt_idx;
   }
   public void setCmt_idx(int cmt_idx) {
      this.cmt_idx = cmt_idx;
   }
   public String getCmt_id() {
      return cmt_id;
   }
   public void setCmt_id(String cmt_id) {
      this.cmt_id = cmt_id;
   }
   public String getCmt_content() {
      return cmt_content;
   }
   public void setCmt_content(String cmt_content) {
      this.cmt_content = cmt_content;
   }
   public int getCmt_bbs_idx() {
      return cmt_bbs_idx;
   }
   public void setCmt_bbs_idx(int cmt_bbs_idx) {
      this.cmt_bbs_idx = cmt_bbs_idx;
   }
   public int getCmt_ref() {
      return cmt_ref;
   }
   public void setCmt_ref(int cmt_ref) {
      this.cmt_ref = cmt_ref;
   }
   public int getCmt_re_setp() {
      return cmt_re_setp;
   }
   public void setCmt_re_setp(int cmt_re_setp) {
      this.cmt_re_setp = cmt_re_setp;
   }
   public int getCmt_level() {
      return cmt_level;
   }
   public void setCmt_level(int cmt_level) {
      this.cmt_level = cmt_level;
   }
   public String getCmt_date() {
      return cmt_date;
   }
   public void setCmt_date(String cmt_date) {
      this.cmt_date = cmt_date;
   }
    
   
    
}