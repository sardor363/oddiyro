package Converter.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseItem{
	private String CcyNmEN;
	private String CcyNmUZC;
	private String Diff;
	private String Rate;
	private String Ccy;
	private String CcyNmRU;
	private int Id;
	private String CcyNmUZ;
	private String Code;
	private String Nominal;
	private String Date;
}
