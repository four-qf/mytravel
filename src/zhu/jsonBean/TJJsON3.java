package zhu.jsonBean;

public class TJJsON3 {
	private Long id;
	private String name;
	private String percent;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPercent() {
		return percent;
	}
	public void setPercent(String percent) {
		this.percent = percent;
	}
	public TJJsON3(String name, String percent) {
		super();
		this.name = name;
		this.percent = percent;
	}
	public TJJsON3(){}
}
