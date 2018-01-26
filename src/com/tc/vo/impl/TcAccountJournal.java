package com.tc.vo.impl;

import java.io.Serializable;
import java.sql.Date;

import com.tc.core.frame.vo.AbstractVO;

public class TcAccountJournal extends AbstractVO {
	
	private Long id;
	private Date deliverDate;
	private String name;
	
	public Long getId(){
		return this.id;
	}

}
