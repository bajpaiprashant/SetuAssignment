package com.setu.api.core;


import java.util.List;

import com.jayway.jsonpath.ReadContext;

public class JsonUtil {
	private static final String ADMIN_USERNAME = null;
	private static final String ADMIN_PASSWORD = null;
	private static final String CLIENT_ID = null;
	private static final String CLIENT_SECRET = null;

	public static String readValueFromJSON(ReadContext resJsonContext, String jsonQuery) {
		List<String> output=resJsonContext.read(jsonQuery);
		if(output.size()>1) {
			return String.valueOf(output.toString());
		}else if(output.size()==1) {
			return String.valueOf(output.get(0));
		}else {
			return null;
			
		}
	}
	
/*	public void tokenRefresh() throws Exception {
		//@formatter:off

		// Login
		final MvcResult loginResult = mockMvc()
			.perform(post("/oauth/token")
				.param("username", ADMIN_USERNAME)
				.param("password", ADMIN_PASSWORD)
				.param("grant_type", "password")
				.header("Authorization", "Basic " + encodeBase64(CLIENT_ID, CLIENT_SECRET)))
			.andExpect(status().isOk())
			.andReturn();
		final ReadContext ctx = JsonPath.parse(loginResult.getResponse().getContentAsString());
		final String refreshToken = ctx.read("$.refresh_token");

		// Refresh token
		mockMvc()
			.perform(post("/oauth/token")
				.param("refresh_token", refreshToken)
				.param("grant_type", "refresh_token")
				.header("Authorization", "Basic " + encodeBase64(CLIENT_ID, CLIENT_SECRET)))
			.andExpect(status().isOk())
			.andDo(docHandler().document(
				requestHeaders(
					headerWithName("Authorization").description("Basic auth credentials for the client")
				),
				requestParameters(
					parameterWithName("grant_type").description("Type of the token to create"),
					parameterWithName("refresh_token").description("Refresh token from the last token")
				),
				responseFieldsForTokenModel()
			));
		//@formatter:on
	}
*/
}
