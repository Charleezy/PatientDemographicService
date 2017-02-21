/*
 * Copyright 2012-2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package hello;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ApplicationTest {

    private MockMvc mockMvc;
    
    @Mock
    private DemographicService demoService;
    
    @InjectMocks
    private DemographicsController demographicsController;
    
    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(demographicsController).build();
    }
    
    @Test
    public void addDemographic() throws Exception{
    	mockMvc.perform(post("/addDemographic").param("documentID","1").param("firstName", "Charlie").param("lastName", "Guan").param("dob", "mar/24/1991").param("address", "toronto street")).andExpect(status().isCreated());
    	Mockito.verify(demoService).addDemographic(Mockito.anyLong(), Mockito.any(DemographicInfo.class));
    }
    
    @Test
	public void shouldUpdateDemographic() throws Exception {

		mockMvc.perform(post("/updateDocument").param("documentID","1").param("firstName", "Charlie").param("lastName", "Guan").param("dob", "mar/24/1991").param("address", "toronto street")).andExpect(
						status().isOk());
		Mockito.verify(demoService).updateDemographicInfo(1L, Mockito.any(DemographicInfo.class));
	}
    
    @Test
	public void getDocumentsByDemographic() throws Exception {
    	mockMvc.perform(get("/getDocumentsByDemographic").param("demographicID", "1")).andExpect(
						status().isOk()).andExpect(content().string(containsString("patient documents: ")));

		Mockito.verify(demoService).getDocumentsByDemographic(1L);
    }
    
    @Test
    public void queryPatientsByFirstName() throws Exception{
    	mockMvc.perform(get("/queryPatients").param("parameter", "firstName").param("value", "charlie")).andExpect(
				status().isOk()).andExpect(content().string(containsString("{'patient document':{'issuer':'','id':''}}")));
    	Mockito.verify(demoService).getDocumentsByParameter("firstName", "charlie");
    }
}
