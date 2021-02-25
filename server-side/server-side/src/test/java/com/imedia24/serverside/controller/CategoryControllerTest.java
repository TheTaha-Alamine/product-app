package com.imedia24.serverside.controller;

import java.util.Arrays;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.imedia24.serverside.entity.Category;
import com.imedia24.serverside.service.CategoryService;

@RunWith(SpringRunner.class)
@WebMvcTest(value = CategoryController.class)
public class CategoryControllerTest {

	private static final String URL = "/api/categories";
	
	@Autowired
	private MockMvc mvc;

	@MockBean
	private CategoryService categoryService;
	
	@Test
	public void getAllCategoriesTest() throws Exception{
		Category c1 = new Category();
		c1.setId(1L);
		c1.setName("c1");
		Category c2 = new Category();
		c2.setId(2L);
		c2.setName("c2");
		Category c3 = new Category();
		c3.setId(3L);
		c3.setName("c3");
		Category c4 = new Category();
		c4.setId(4L);
		c4.setName("c4");
		
		
		Mockito.when(categoryService.getAllCategories(Mockito.any())).thenReturn(new PageImpl(Arrays.asList(c1, c2,c3,c4)));
		
		mvc.perform(MockMvcRequestBuilders.get(URL)).andDo(MockMvcResultHandlers.print())
			.andExpect(MockMvcResultMatchers.status().isOk())
		
			.andExpect(MockMvcResultMatchers.jsonPath("$.content", Matchers.hasSize(4)));
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
	
}
