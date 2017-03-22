package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;

@Entity
@Access(AccessType.PROPERTY)
public class Finder extends DomainEntity {
	
	// Attributes ------------------------------------------------------------
	private String title, description, originAddress,destinationAddress;

	private String keyword;

	private Integer numberOfFinderResults;


	// Relationships ---------------------------------------------------------
	
	// Constructors -----------------------------------------------------------
	public Finder() {
		super();		
	}
//	@NotBlank

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getOriginAddress() {
		return originAddress;
	}

	public void setOriginAddress(String originAddress) {
		this.originAddress = originAddress;
	}

	public String getDestinationAddress() {
		return destinationAddress;
	}

	public void setDestinationAddress(String destinationAddress) {
		this.destinationAddress = destinationAddress;
	}
	
	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}


	public Integer getNumberOfFinderResults() {
		return numberOfFinderResults;
	}

	public void setNumberOfFinderResults(Integer numberOfFinderResults) {
		this.numberOfFinderResults = numberOfFinderResults;
	}
}