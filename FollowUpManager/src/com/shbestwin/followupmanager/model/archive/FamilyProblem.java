package com.shbestwin.followupmanager.model.archive;

public class FamilyProblem {
	
	private int id_;//序号
	private String jd;// 阶段
	private String fsr;// 发生人
	private String fsrq;// 发生日期
	private String zywt;// 主要问题
	private String wtpg;// 问题评估
	private String cljjg;// 处理及结果
	private String zgzl;//主观资料
	private String kgzl;//客观资料
	private String qt;//其他
	private String gljh;//管理计划
	private String jlys;//记录医生
	private String jlrq;//记录日期
	private String bz;//备注
	
	public FamilyProblem(){
		
	}

	public FamilyProblem(int id_, String jd, String fsr, String fsrq,
			String zywt, String wtpg, String cljjg, String zgzl, String kgzl,
			String qt, String gljh, String jlys, String jlrq, String bz) {
		super();
		this.id_ = id_;
		this.jd = jd;
		this.fsr = fsr;
		this.fsrq = fsrq;
		this.zywt = zywt;
		this.wtpg = wtpg;
		this.cljjg = cljjg;
		this.zgzl = zgzl;
		this.kgzl = kgzl;
		this.qt = qt;
		this.gljh = gljh;
		this.jlys = jlys;
		this.jlrq = jlrq;
		this.bz = bz;
	}

	@Override
	public String toString() {
		return "FamilyProblem [id_=" + id_ + "id_, jd=" + jd + "jd, fsr=" + fsr
				+ "fsr, fsrq=" + fsrq + "fsrq, zywt=" + zywt + "zywt, wtpg=" + wtpg
				+ "wtpg, cljjg=" + cljjg + "cljjg, zgzl=" + zgzl + "zgzl, kgzl=" + kgzl
				+ "kgzl, qt=" + qt + "qt, gljh=" + gljh + "gljh, jlys=" + jlys
				+ "jlys, jlrq=" + jlrq + "jlrq, bz=" + bz + "bz,]";
	}

	public int getId_() {
		return id_;
	}

	public void setId_(int id_) {
		this.id_ = id_;
	}

	public String getJd() {
		return jd;
	}

	public void setJd(String jd) {
		this.jd = jd;
	}

	public String getFsr() {
		return fsr;
	}

	public void setFsr(String fsr) {
		this.fsr = fsr;
	}

	public String getFsrq() {
		return fsrq;
	}

	public void setFsrq(String fsrq) {
		this.fsrq = fsrq;
	}

	public String getZywt() {
		return zywt;
	}

	public void setZywt(String zywt) {
		this.zywt = zywt;
	}

	public String getWtpg() {
		return wtpg;
	}

	public void setWtpg(String wtpg) {
		this.wtpg = wtpg;
	}

	public String getCljjg() {
		return cljjg;
	}

	public void setCljjg(String cljjg) {
		this.cljjg = cljjg;
	}

	public String getZgzl() {
		return zgzl;
	}

	public void setZgzl(String zgzl) {
		this.zgzl = zgzl;
	}

	public String getKgzl() {
		return kgzl;
	}

	public void setKgzl(String kgzl) {
		this.kgzl = kgzl;
	}

	public String getQt() {
		return qt;
	}

	public void setQt(String qt) {
		this.qt = qt;
	}

	public String getGljh() {
		return gljh;
	}

	public void setGljh(String gljh) {
		this.gljh = gljh;
	}

	public String getJlys() {
		return jlys;
	}

	public void setJlys(String jlys) {
		this.jlys = jlys;
	}

	public String getJlrq() {
		return jlrq;
	}

	public void setJlrq(String jlrq) {
		this.jlrq = jlrq;
	}

	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}

}
