package zhu.jsonBean;

public class TJJsON2 {
	private Long id;
	private Integer num=0;
	private String month;
	public TJJsON2(String month) {
		this.month = month;
	}
	public TJJsON2(String string, int i) {
		month = string;
		num = i;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	
}
