package com.karlchu.mo.web.editor;

import java.beans.PropertyEditorSupport;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CustomTimestampEditor extends PropertyEditorSupport {
	private String dateFormat = "dd-MM-yyyy";
	public CustomTimestampEditor(String dateFormat) {
		this.dateFormat = dateFormat;
	}
	public CustomTimestampEditor(){}
	public void setAsText(String text) throws IllegalArgumentException {
		if (text != null && text.trim().length() > 1)  {
            if (text.trim().length() > 10){
                text = text.trim().split(" ")[0];
                String[] arrText = text.split("-");
                text = arrText[2] + "-" + arrText[1] + "-" + arrText[0];
            }
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
