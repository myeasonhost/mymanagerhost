package com.eason.transfer.openapi.core.api;

import org.apache.cxf.jaxrs.ext.multipart.MultipartBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

@Path("/api")
public interface OpenApiVisitRouter {

	@POST
	@Path("/router")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	String router(MultivaluedMap<String, String> paramMap, @Context HttpServletRequest request, @Context HttpServletResponse response);

	@POST
	@Path("/router")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	String routerWithFile(MultipartBody body, @Context HttpServletRequest request, @Context HttpServletResponse response);

}
