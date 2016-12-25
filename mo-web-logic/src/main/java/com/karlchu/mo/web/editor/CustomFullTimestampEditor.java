package com.karlchu.mo.web.editor;

import java.beans.PropertyEditorSupport;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CustomFullTimestampEditor extends PropertyEditorSupport {
	private String dateFormat = "dd-MM-yyyy";
	public CustomFullTimestampEditor(String dateFormat) {
		this.dateFormat = dateFormat;
	}
	public CustomFullTimestampEditor(){}
	public void setAsText(String text) throws IllegalArgumentException {
		if (text != null && text.trim().length() > 1)  {
			SimpleDateFormat format = new SimpleDateFormat(dateFormat);
			try {
				Date d = (Date) format.parse(text);
				setValue(new Timestamp((d.getTime())));
			} catch (Exception e) {
				// NO NEED TO PRINT OUT ERROR HERE
				//log.error("Invalid date format [" + dateFormat + "]" + e.getMessage());
			}
		}

	}
}
