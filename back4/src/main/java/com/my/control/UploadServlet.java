package com.my.control;

import java.io.File;
import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
@WebServlet("/upload")
@MultipartConfig
public class UploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String saveDirectoryName = "c:\\files";
		File saveDirectory = new File(saveDirectoryName);
		try {   			
			if(!saveDirectory.exists()) {
				System.out.println("업로드 실제경로("+ saveDirectoryName +")생성");
				saveDirectory.mkdirs();
			}
			System.out.println("saveDirectory.getAbsolutePath()=" + saveDirectory.getAbsolutePath());
			Collection<Part> parts = request.getParts();

			for(Part part: parts) {
				String paramName = part.getName();
				System.out.println("part.getName()=" + paramName +", part.getSubmittedFileName()="+ part.getSubmittedFileName()+", part.getSize()=" + part.getSize());
				if("foodFile".equals(paramName) || "drinkFiles".equals(paramName)||"recipe".equals(paramName)) {

					String originFileName = part.getSubmittedFileName();
					if(!originFileName.equals("")) {
					//String fileName =  originFileName;
					//Universal Unique Identifier
					//String fileName = UUID.randomUUID() + "_" + originFileName;
					String fileName = "temp2_ " + originFileName;
					part.write( saveDirectory.getAbsolutePath()+"\\"+fileName); 
				}
				
			   }
			}
				System.out.println("-----request.getParameter(\"greeting\")----");
				System.out.println(request.getParameter("greeting"));
				System.out.println("-----request.getParameter(\"recipes\")----");
				System.out.println(request.getParameter("recipes"));

			} catch (Exception e) {
				e.printStackTrace();
			}
	}
}
