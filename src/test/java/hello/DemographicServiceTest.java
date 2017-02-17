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
	
//	@Test
//	public void shouldAddDemographic(){
//		DemographicInfo mockIdentificationDemographic = new DemographicInfo("Charlie", "Guan","mar/24/1991", "toronto street");
//		Mockito.when(demographicRepository.save(mockIdentificationDemographic)).thenReturn(mockIdentificationDemographic);
//		demographicService.addDemographic(mockIdentificationDemographic);
//		Mockito.verify(demographicRepository).save(mockIdentificationDemographic);
//	}
}
