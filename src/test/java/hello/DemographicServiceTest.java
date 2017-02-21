package hello;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
		DemographicInfo mockDemographic = new DemographicInfo("Charlie", "Guan","mar/24/1991", "toronto street");
		Mockito.when(demographicRepository.save(mockDemographic)).thenReturn(mockDemographic);
		IdentificationDocument mockIdentificationDocument = new IdentificationDocument("Government of Canada", "Qazxc123");
		mockIdentificationDocument.setHeadIdentificationDocumentID(1);
		Mockito.when(identificationRepository.findOne(1L)).thenReturn(mockIdentificationDocument);
		demographicService.addDemographic(1, mockDemographic);
		Mockito.verify(demographicRepository).save(mockDemographic);
	}
	
	@Test
	public void shouldUpdateDemographic() throws Exception{
		DemographicInfo mockDemographic = new DemographicInfo("Charlie", "Guan","mar/24/1991", "toronto street");
		mockDemographic.setDemographicID(1L);
		Mockito.when(demographicRepository.findOne(1L)).thenReturn(mockDemographic);
		IdentificationDocument mockIdentificationDocument = new IdentificationDocument("Government of Canada", "Qazxc123");
		mockIdentificationDocument.setIdentificationDocumentID(1L);
		mockIdentificationDocument.setHeadIdentificationDocumentID(2);
		Mockito.when(identificationRepository.findOne(1L)).thenReturn(mockIdentificationDocument);
		
		demographicService.updateDemographicInfo(mockIdentificationDocument.getIdentificationDocumentID(), mockDemographic);
		mockDemographic.setIdentificationDocument(2L);
		
		Mockito.verify(demographicRepository).save(mockDemographic);
	}
	
	@Test
	public void shouldGetDocumentsByDemographic(){
		DemographicInfo mockDemographic = new DemographicInfo("Charlie", "Guan","mar/24/1991", "toronto street");
		mockDemographic.setDemographicID(1L);
		mockDemographic.setIdentificationDocument(1L);
		Mockito.when(demographicRepository.findOne(1L)).thenReturn(mockDemographic);
		
		IdentificationDocument mockIdentificationDocument1 = new IdentificationDocument("Government of Canada", "Qazxc123");
		mockIdentificationDocument1.setIdentificationDocumentID(1);
		mockIdentificationDocument1.setHeadIdentificationDocumentID(1);
		mockIdentificationDocument1.setNextLinkedIdentificationDocumentID(2);
		Mockito.when(identificationRepository.findOne(1L)).thenReturn(mockIdentificationDocument1);
		
		IdentificationDocument mockIdentificationDocument2 = new IdentificationDocument("Government of Canada", "Qazxc123");
		mockIdentificationDocument2.setIdentificationDocumentID(2);
		mockIdentificationDocument2.setHeadIdentificationDocumentID(1);
		Mockito.when(identificationRepository.findOne(2L)).thenReturn(mockIdentificationDocument2);
		
		List<IdentificationDocument> expectedIdentificationDocumentList = new ArrayList<IdentificationDocument>();
		expectedIdentificationDocumentList.add(mockIdentificationDocument1);
		expectedIdentificationDocumentList.add(mockIdentificationDocument2);
		
		List<IdentificationDocument> actualIdentificationDocumentList = demographicService.getDocumentsByDemographic(1L);
		
		Assert.assertEquals(expectedIdentificationDocumentList, actualIdentificationDocumentList);
		Mockito.verify(demographicRepository).findOne(1L);
		Mockito.verify(identificationRepository).findOne(1L);
		Mockito.verify(identificationRepository).findOne(2L);
	}
	
	@Test
	public void shouldQueryPatientByFirstName() throws Exception{
		Map<IdentificationDocument, DemographicInfo> expectedResult = new HashMap<IdentificationDocument, DemographicInfo>();
		DemographicInfo mockDI = new DemographicInfo("charlie", "guan", "mar/24/19991","toronto street");
		mockDI.setIdentificationDocument(1L);
		List<DemographicInfo> mockDIList = new ArrayList<DemographicInfo>();
		mockDIList.add(mockDI);
		IdentificationDocument mockID = new IdentificationDocument("government of canada", "Qazxc123");
		expectedResult.put(mockID, mockDI);
		Mockito.when(demographicRepository.findByFirstName("charlie")).thenReturn(mockDIList);
		Mockito.when(identificationRepository.findOne(1L)).thenReturn(mockID);
		
		Map<IdentificationDocument, DemographicInfo> result = demographicService.getDocumentsByParameter("firstName", "charlie");
		
		Mockito.verify(demographicRepository).findByFirstName("charlie");
		Assert.assertEquals(result.get(mockID), mockDI);
	}
}
