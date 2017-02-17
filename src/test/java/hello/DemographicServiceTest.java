package hello;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import org.junit.Assert;

public class DemographicServiceTest {
	@Mock
	DemographicRepository demographicRepository;
	
	@Mock
	IdentificationDocumentRepository identificationRepository;
	
	@InjectMocks
	DemographicService demographicService;
	
	@Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }
	
	@Test
	public void shouldAddDemographic(){
		DemographicInfo mockIdentificationDemographic = new DemographicInfo("Charlie", "Guan","mar/24/1991", "toronto street");
		Mockito.when(demographicRepository.save(mockIdentificationDemographic)).thenReturn(mockIdentificationDemographic);
		demographicService.addDemographic(mockIdentificationDemographic);
		Mockito.verify(demographicRepository).save(mockIdentificationDemographic);
	}
	
	@Test
	public void shouldLinkDemographicToIdentificationDocument(){
		DemographicInfo mockDemographic = new DemographicInfo("Charlie", "Guan","mar/24/1991", "toronto street");
		mockDemographic.setDemographicID(1L);
		Mockito.when(demographicRepository.findOne(1L)).thenReturn(mockDemographic);
		IdentificationDocument mockIdentificationDocument = new IdentificationDocument("Government of Canada", "Qazxc123");
		mockIdentificationDocument.setIdentificationDocumentID(1L);
		mockIdentificationDocument.setHeadIdentificationDocumentID(2);
		Mockito.when(identificationRepository.findOne(1L)).thenReturn(mockIdentificationDocument);
		
		demographicService.linkDemographicInfo(mockDemographic.getDemographicID(), mockIdentificationDocument.getIdentificationDocumentID());
		mockDemographic.setIdentificationDocument(2L);
		
		Mockito.verify(demographicRepository).save(mockDemographic);
	}
}
