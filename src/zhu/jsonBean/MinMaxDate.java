package zhu.jsonBean;

import java.util.Date;

public class MinMaxDate {
	private Date Min;
	private Date Max;
	
	public MinMaxDate(){}
	public MinMaxDate(Date min, Date max) {
		super();
		Min = min;
		Max = max;
	}
	public Date getMin() {
		return Min;
	}
	public void setMin(Date min) {
		Min = min;
	}
	public Date getMax() {
		return Max;
	}
	public void setMax(Date max) {
		Max = max;
	}
}
