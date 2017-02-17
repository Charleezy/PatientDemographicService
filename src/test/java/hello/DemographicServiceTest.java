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
	
//	@Test
//	public void shouldAddDocument(){
//		DemographicInfo mockIdentificationDocument = new DemographicInfo("Government of Canada", "QAZXC123");
//		Mockito.when(identificationDocumentRepository.save(mockIdentificationDocument)).thenReturn(mockIdentificationDocument);
//		identificationDocumentService.addDocument(mockIdentificationDocument);
//		Mockito.verify(identificationDocumentRepository).save(mockIdentificationDocument);
//	}
}
