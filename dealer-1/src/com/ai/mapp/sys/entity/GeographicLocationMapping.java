package com.ai.mapp.sys.entity;

import java.util.Date;
import java.util.List;
import com.ailk.butterfly.mapp.core.model.IBody;


public class GeographicLocationMapping extends IBody 
{
	private List<GeographicLocation> geographicLocations;
	
	public GeographicLocationMapping() {
		super();
	}
	
	public GeographicLocationMapping(
			List<GeographicLocation> geographicLocations) {
		super();
		this.geographicLocations = geographicLocations;
	}

	public List<GeographicLocation> getGeographicLocations() {
		return geographicLocations;
	}

	public void setGeographicLocations(List<GeographicLocation> geographicLocations) {
		this.geographicLocations = geographicLocations;
	}

	public static class GeographicLocation extends IBody {

		private String id;
		private String geographicLocationMasterId;
		private Date startDate;
		private Date endDate;
		private String type;
		private String status;
		private String locationType;
		private String locationDescription;
		private String locationName;
	
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getGeographicLocationMasterId() {
			return geographicLocationMasterId;
		}
		public void setGeographicLocationMasterId(String geographicLocationMasterId) {
			this.geographicLocationMasterId = geographicLocationMasterId;
		}
		public Date getStartDate() {
			return startDate;
		}
		public void setStartDate(Date startDate) {
			this.startDate = startDate;
		}
		public Date getEndDate() {
			return endDate;
		}
		public void setEndDate(Date endDate) {
			this.endDate = endDate;
		}
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}
		public String getLocationType() {
			return locationType;
		}
		public void setLocationType(String locationType) {
			this.locationType = locationType;
		}
		public String getLocationDescription() {
			return locationDescription;
		}
		public void setLocationDescription(String locationDescription) {
			this.locationDescription = locationDescription;
		}
		public String getLocationName() {
			return locationName;
		}
		public void setLocationName(String locationName) {
			this.locationName = locationName;
		}
	
	}
}
