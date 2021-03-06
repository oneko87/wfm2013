package wfm.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.model.SelectItem;
import javax.inject.Named;

@Named
@SessionScoped
public class MembershipTypes implements Serializable{

	
	private static final long serialVersionUID = 1L;

	public MembershipTypes() {
		
	}

	
	public List<SelectItem> getTypes() {
	    List<SelectItem> retVal = new ArrayList<SelectItem>();

	    retVal.add(new SelectItem("Bronze"));
	    retVal.add(new SelectItem("Silver"));
	    retVal.add(new SelectItem("Gold"));

	    return retVal;
	}
}
